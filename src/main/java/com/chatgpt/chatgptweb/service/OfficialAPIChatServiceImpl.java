/**
 * @(#)OfficialAPIChatServiceImpl.java, 2023/12/17.
 *     <p/>
 *     Copyright 2023 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;

import com.alibaba.fastjson2.JSON;
import com.chatgpt.chatgptweb.meta.APIAccount;
import com.chatgpt.chatgptweb.meta.ChatCompletionDTO;
import com.chatgpt.chatgptweb.meta.ChatCompletionParam;
import com.chatgpt.chatgptweb.meta.ChatProcessDTO;
import com.chatgpt.chatgptweb.meta.ChatProcessParam;
import com.chatgpt.chatgptweb.meta.Message;
import com.github.benmanes.caffeine.cache.Cache;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
@Slf4j
@Service
public class OfficialAPIChatServiceImpl implements ChatSerivce {

    @Resource
    private Cache<String, List<Message>> historyMessageCache;

    @Resource
    private ChatAPIAccountManager chatAPIAccountManager;

    @Override
    public Flux<String> chatWithGPT(ChatProcessParam param) {

        ChatCompletionParam chatCompletionParam = convert(param);

        String parentMessageId = param.getOptions().getParentMessageId();
        if (StringUtils.isEmpty(parentMessageId)) {
            parentMessageId = "";
        }
        List<Message> historyMessageList = historyMessageCache.get(parentMessageId, key -> new ArrayList<>());
        // 加载上下文
        if (param.getOptions() != null) {
            if (CollectionUtils.isNotEmpty(historyMessageList)) {
                List<Message> newMessageList = new ArrayList<>();
                newMessageList.add(chatCompletionParam.getMessages().get(0));
                historyMessageList.add(chatCompletionParam.getMessages().get(1));
                newMessageList.addAll(historyMessageList);
                chatCompletionParam.setMessages(newMessageList);
            } else {
                historyMessageList.addAll(chatCompletionParam.getMessages());
            }
            historyMessageCache.invalidate(parentMessageId);
        }

        AtomicBoolean first = new AtomicBoolean(true);
        StringBuilder totalAnswerStr = new StringBuilder();

        log.info("[op:chatProcess] chatCompletionParam={}", JSON.toJSONString(chatCompletionParam));
        AtomicReference<String> finalParentMessageId = new AtomicReference<>(parentMessageId);
        return WebClientFactory.getInstance()
            .post()
            .uri("https://api.openai-proxy.com/v1/chat/completions")
            .header("Authorization", "Bearer " + getApiAccount().getAccessToken())
            .header("Content-Type", "application/json")
            .body(BodyInserters.fromValue(JSON.toJSONString(chatCompletionParam)))
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(String.class)
            .map(data -> {
                // log.info("[op:chatProcess] data={}", data);
                if (!"[DONE]".equals(data)) {
                    ChatCompletionDTO chatCompletionDTO = JSON.parseObject(data, ChatCompletionDTO.class);
                    ChatProcessDTO chatProcessDTO = convert(chatCompletionDTO);
                    finalParentMessageId.set(chatCompletionDTO.getId());
                    String replyStr = chatCompletionDTO.getChoices().get(0).getDelta().getContent();
                    if (StringUtils.isNotEmpty(replyStr)) {
                        totalAnswerStr.append(replyStr);
                    }
                    chatProcessDTO.setText(totalAnswerStr.toString());
                    String responseStr = JSON.toJSONString(chatProcessDTO);
                    if (first.get()) {
                        first.set(false);
                        return responseStr;
                    } else {
                        return "\n" + responseStr;
                    }
                }
                return "";
            }).doFinally(signalType -> {
                log.info("[op:chatProcess] chatgpt回复结束，对话历史放入缓存");
                removeNotCareHistoryMessage(historyMessageList);
                historyMessageList.add(new Message("assistant", totalAnswerStr.toString()));
                if (StringUtils.isNotEmpty(finalParentMessageId.get()) && CollectionUtils.isNotEmpty(historyMessageList)) {
                    historyMessageCache.put(finalParentMessageId.get(), historyMessageList);
                    log.info("[op:chatProcess] 已放入当前对话历史记录 id={} historyMessageList={}", finalParentMessageId,
                        JSON.toJSONString(historyMessageList));
                }
            });
    }

    @Override
    public APIAccount getApiAccount() {
        return chatAPIAccountManager.getApiAccountByPolling(APIAccount.SOURCE_OFFICIAL_API);
    }

    private ChatCompletionParam convert(ChatProcessParam param) {
        List<Message> messages = new ArrayList<>(2);
        messages.add(new Message("system", param.getSystemMessage()));
        messages.add(new Message("user", param.getPrompt()));
        return ChatCompletionParam.builder()
            .model("gpt-3.5-turbo")
            .messages(messages)
            .temperature(param.getTemperature())
            .top_p(param.getTop_p())
//            .maxTokens(5000)
            .presence_penalty(1f)
            .stream(true)
            .build();
    }

    private ChatProcessDTO convert(ChatCompletionDTO chatCompletionDTO) {
        return ChatProcessDTO.builder()
            .id(chatCompletionDTO.getId())
            .parentMessageId(chatCompletionDTO.getId())
            .delta(chatCompletionDTO.getChoices().get(0).getDelta().getContent())
            .role(chatCompletionDTO.getChoices().get(0).getDelta().getRole())
            .text(chatCompletionDTO.getChoices().get(0).getDelta().getContent())
            .detail(chatCompletionDTO)
            .build();
    }

    private void removeNotCareHistoryMessage(List<Message> historyMessageList) {
        if (CollectionUtils.isEmpty(historyMessageList)) {
            return;
        }
        historyMessageList.removeIf(message -> "system".equals(message.getRole()));
    }
}

/**
 * @(#)FakeWebAPIChatServiceImpl.java, 2023/12/17.
 *     <p/>
 *     Copyright 2023 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.alibaba.fastjson2.JSON;
import com.chatgpt.chatgptweb.meta.APIAccount;
import com.chatgpt.chatgptweb.meta.ChatCompletionDTO;
import com.chatgpt.chatgptweb.meta.ChatProcessDTO;
import com.chatgpt.chatgptweb.meta.ChatProcessParam;
import com.chatgpt.chatgptweb.meta.Choice;
import com.chatgpt.chatgptweb.meta.Delta;
import com.chatgpt.chatgptweb.meta.WebAPIChatCompletionDTO;
import com.chatgpt.chatgptweb.meta.WebAPIChatCompletionParam;
import com.github.benmanes.caffeine.cache.Cache;
import com.google.common.collect.Lists;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
@Slf4j
@Service
public class FakeWebAPIChatServiceImpl implements ChatSerivce {

    @Resource
    private Cache<String, String> conversationIdCache;

    @Override
    public Flux<String> chatWithGPT(ChatProcessParam param) {

        WebAPIChatCompletionParam param1 = convert(param);

        log.info("[op:chatWithGPT] param1={}", JSON.toJSONString(param1));

        AtomicBoolean first = new AtomicBoolean(true);
        AtomicBoolean conversationIdNotSaved = new AtomicBoolean(true);

        APIAccount apiAccount = getApiAccount();

        WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                HttpClient.create()
                    .proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP)
                        .host("127.0.0.1")
                        .port(7890)) //代理
            )).build();

        return webClient
            .post()
            .uri("https://chat.openai.com/backend-api/conversation")
            .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
            .header("Authorization", "Bearer " + getApiAccount().getAccessToken())
            .header("Cookie", apiAccount.getCookie())
            .header("Accept-Encoding", "gzip, deflate, br")
            .header("Accept-Language", "en-US")
            .header("Sec-Ch-Ua", "\"Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24\"\n")
            .header("Sec-Ch-Ua-Mobile", "?0")
            .header("Sec-Ch-Ua-Platform", "\"macOS\"")
            .header("Sec-Fetch-Dest", "empty")
            .header("Sec-Fetch-Mode", "cors")
            .header("Sec-Fetch-Site", "same-origin")
            .header("Content-Type", "application/json; charset=UTF-8")
            .body(BodyInserters.fromValue(JSON.toJSONString(param1)))
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(String.class)
            .map(data -> {
                log.info("[op:chatWithGPT] data={}", data);
                if (!"[DONE]".equals(data)) {
                    WebAPIChatCompletionDTO chatCompletionDTO = JSON.parseObject(data, WebAPIChatCompletionDTO.class);
                    if (Boolean.TRUE.equals(chatCompletionDTO.getIsCompletion())) {
                        return "";
                    }
                    ChatProcessDTO chatProcessDTO = convert(chatCompletionDTO);

                    // 保存conversationId
                    if (conversationIdNotSaved.get() &&
                        StringUtils.isNotEmpty(chatCompletionDTO.getConversationId()) &&
                        StringUtils.isNotEmpty(chatProcessDTO.getParentMessageId())) {
                        log.info("[op:chatWithGPT] 保存上下文 conversationId={} parentMessageId={}",
                            chatCompletionDTO.getConversationId(), chatProcessDTO.getParentMessageId());
                        conversationIdCache.put(chatProcessDTO.getParentMessageId(), chatCompletionDTO.getConversationId());
                        conversationIdNotSaved.set(false);
                    }

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
                log.info("[op:chatProcess] chatgpt回复结束");
            });
    }

    @Override
    public APIAccount getApiAccount() {
        return null;
    }

    private WebAPIChatCompletionParam convert(ChatProcessParam param) {
        WebAPIChatCompletionParam param1 = new WebAPIChatCompletionParam();
        param1.setAction("next");
        if (param.getOptions() != null && StringUtils.isNotEmpty(param.getOptions().getParentMessageId())) {
            param1.setParentMessageId(param.getOptions().getParentMessageId());
            param1.setSuggestions(Lists.newArrayList());
            String conversationId = conversationIdCache.getIfPresent(param.getOptions().getParentMessageId());
            param1.setConversationId(conversationId);
        } else {
            param1.setParentMessageId(UUID.randomUUID().toString());
            // suggestions 目前不清楚用途
            param1.setSuggestions(Lists.newArrayList("Give me 3 ideas about how to plan good New Years resolutions. Give me some that are personal, family, and professionally-oriented.",
                "Tell me a random fun fact about the Roman Empire",
                "Come up with 5 concepts for a retro-style arcade game.",
                "Make a content strategy for a newsletter featuring free local weekend events."));
        }
        param1.setModel("text-davinci-002-render-sha");
        param1.setMessages(Lists.newArrayList(
            WebAPIChatCompletionParam.Message.builder()
                .id(UUID.randomUUID().toString())
                .author(WebAPIChatCompletionParam.Author.builder()
                    .role("user")
                    .build())
                .content(WebAPIChatCompletionParam.Content.builder()
                    .contentType("text")
                    .parts(Lists.newArrayList(param.getPrompt()))
                    .build())
                .metadata(new WebAPIChatCompletionParam.Metadata())
                .build()));
        param1.setTimezoneOffsetMin(-480L);
        param1.setHistoryAndTrainingDisabled(false);
        param1.setArkoseToken(null);
        param1.setConversationMode(WebAPIChatCompletionParam.ConversationMode.builder()
            .kind("primary_assistant")
            .build());
        param1.setForceParagen(false);
        param1.setForceRateLimit(false);
        return param1;
    }

    private ChatProcessDTO convert(WebAPIChatCompletionDTO webAPIChatCompletionDTO) {
        String id = webAPIChatCompletionDTO.getMessage() == null ? webAPIChatCompletionDTO.getMessageId() :
            webAPIChatCompletionDTO.getMessage().getId();
        String delta;
        if (webAPIChatCompletionDTO.getMessage() != null && webAPIChatCompletionDTO.getMessage().getContent() != null && CollectionUtils.isNotEmpty(webAPIChatCompletionDTO.getMessage().getContent().getParts())) {
            delta = webAPIChatCompletionDTO.getMessage().getContent().getParts().get(0);
        } else {
            delta = "";
        }

        // 过滤其他角色的消息
        if (webAPIChatCompletionDTO.getMessage() != null &&
            webAPIChatCompletionDTO.getMessage().getAuthor() != null &&
            !"assistant".equals(webAPIChatCompletionDTO.getMessage().getAuthor().getRole())) {
            delta = "";
        }

        ChatCompletionDTO chatCompletionDTO = ChatCompletionDTO.builder()
            .id(webAPIChatCompletionDTO.getMessageId())
            .object(null)
            .created(null)
            .model(webAPIChatCompletionDTO.getMessage() != null ?
                webAPIChatCompletionDTO.getMessage().getMetadata().getModelSlug() :
                null)
            .system_fingerprint(null)
            .choices(Lists.newArrayList(Choice.builder()
                .delta(Delta.builder()
                    .content(delta)
                    .role("assistant")
                    .build())
                .index(0)
                .build()
            ))
            .build();

        return ChatProcessDTO.builder()
            .id(id)
            .parentMessageId(id)
            .delta(delta)
            .role("assistant")
            .text(delta)
            .detail(chatCompletionDTO)
            .build();
    }
}

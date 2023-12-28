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

        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                HttpClient.create()
                    .proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP)
                        .host("127.0.0.1")
                        .port(7890)) //代理
            )).build()
            .post()
            .uri("https://chat.openai.com/backend-api/conversation")
            .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
            .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1UaEVOVUpHTkVNMVFURTRNMEZCTWpkQ05UZzVNRFUxUlRVd1FVSkRNRU13UmtGRVFrRXpSZyJ9.eyJodHRwczovL2FwaS5vcGVuYWkuY29tL3Byb2ZpbGUiOnsiZW1haWwiOiJqb2hudGFuZ2x5QGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwiaHR0cHM6Ly9hcGkub3BlbmFpLmNvbS9hdXRoIjp7InBvaWQiOiJvcmctbTR5a21wYXBKUHFBSjdHZ3ZOYUVxSmlsIiwidXNlcl9pZCI6InVzZXItQTZnbmhTZVRtSFRaR1RqYzBCMFYzMjZ4In0sImlzcyI6Imh0dHBzOi8vYXV0aDAub3BlbmFpLmNvbS8iLCJzdWIiOiJnb29nbGUtb2F1dGgyfDEwMjAyNTQ4OTkwOTkyNDEyNjExMyIsImF1ZCI6WyJodHRwczovL2FwaS5vcGVuYWkuY29tL3YxIiwiaHR0cHM6Ly9vcGVuYWkub3BlbmFpLmF1dGgwYXBwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE3MDMzODgzOTcsImV4cCI6MTcwNDI1MjM5NywiYXpwIjoiVGRKSWNiZTE2V29USHROOTVueXl3aDVFNHlPbzZJdEciLCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIG1vZGVsLnJlYWQgbW9kZWwucmVxdWVzdCBvcmdhbml6YXRpb24ucmVhZCBvcmdhbml6YXRpb24ud3JpdGUgb2ZmbGluZV9hY2Nlc3MifQ.UzK1SqXytcB90DNLvPq5l0p2mC1YwyKCrI6974_5fwVJrJmhgDB4lYmfT8Ob0GMU1-U9F1pGZ4Yn6SrrTu-kmb1cPzvvMEyT4eNcFaeda29lvxp6f9BFyJUIVGMhiPaxx2SMFBkLxfH51kI_TGsk60yyI31eFDWHugktp6FvHAEFhUmEhBFmH7dbD_DcJkkUUmYcSFh7LRxDZxD1767EMoxkvTeeWmBJiMl13HA2YklLpKSYGBlXDG2y0AM58ftdNrofbxuDrZYos0U7Qrh0i3ZhSiyrj4v75VzIZxQ3LNYHrwyciosgZhQ6rCuSMPMfSivzFQPPE0E9dwhQOF7JGg")
//            .header("Cookie", "intercom-device-id-dgkjq2bp=c0ff9ab3-2749-4e64-bebb-2f28f90a2fd8; " +
//                "ajs_anonymous_id=047e0149-7fd7-4224-a230-f160a658e0c2; " +
//                "cf_clearance=DrP6g8eaoeRznQ8f2ZVB8CIquE0MvG0J0ARMAceiddg-1702604110-0-1-fc1d5f96.298e1761.ab1c0076-0" +
//                ".2.1702604110; ajs_user_id=user-A6gnhSeTmHTZGTjc0B0V326x; __Host-next-auth" +
//                ".csrf-token=93f975f752209de8c8bdee51e8022cd5d1b67025b85b919e22490f4174428e11" +
//                "%7C65436b950faa8edfd7429c3d6d40cb9bbc47e7abdb0a8cc2eebea06e9c31692d; " +
//                "_cfuvid=CybVBI7TNMOCZZXe474oDtyb2d4bR1jVRKsWKOwJZ5k-1702981865717-0-604800000; " +
//                "__cf_bm=MgQJTNJd8zyYS9yFWb7snncJkJMIELy0rCFVJ8HIs7I-1702983712-1-AetHOJGUJdEafTgFR/4k" +
//                "/AWY2abuiFGmkIvCGKJVhCBOpt+Z/X6YcGXsjoh5Ld0DbnT9UxI6fgNqOXNQx6imYSo=; " +
//                "__cflb=0H28vVfF4aAyg2hkHFH9CkdHRXPsfCUezr1YL7ETJDK; __Secure-next-auth" +
//                ".callback-url=https%3A%2F%2Fchat.openai.com; __Secure-next-auth" +
//                ".session-token=eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..n3OZcmJdfMk2nFhl" +
//                ".KgBtjpQPwLwJji-P8cWk3NLQcONKsCuZl8FPKhKP0oehESNul42oDvRNpLlSWLl6JzXUmkqPYcAYOpGKXwj" +
//                "-krTpkhxAUiTFiGU96_KG3Yql4YTw1LrDv_wq1xCCLl82mcxg6kzOWbvJ7RClmUHfP" +
//                "-DtuKiH4kqbpdOi_GuxFbGnvKbxKli9Tx3y3pBnvr8pVufIYuNBHSG9n31ZPY9xFXch5J7DgxjIXalKmopNM2ffqAdZerKN4r0rtOpOw_LzpxGTIC5VYx2DAnpqoPVQkHJiog8-tehbBKmUoahvn4IiTU2jvHPtSSO2FvxbaJO7qpgZlOyyIKql87kG_kbujJTMfgCPJk7RlKnZ4jzUmiX74myG2U4WmqGIylBI4pzGLO8TFnlqSK2ij2Qdf77l3SkaXXK2Ad5sHZT2RRm_0ii1gAnjtPlTAzvTVBP2TxaAfOPplJcCoeXp9shZc-N6RjIKnhGXayHlyD519mxk04T_2DaeKCLZmx4yLO6HL5pK2ySRiyu5NyBkVEFdHwrGyuM55xFAqBmap-xOA8w_bTi7l_G-SI7e4T8qX-I7COnRZlidQQsI1C1_8uQSnpJ9iGrBjmhRx6mHAxx1G272FMMTKnH3LE7eO5d3pixNOG7Ekvb476YuX43jm4GTtuws55SCCUj_xrlecxoLStZvQ3ySBZTJU1iQdwvTyhRJzxiT8N0SyU5tWk0y2VxSD1VCgQbqDMdCfTNl25Cj7WHG5Xuq6qYdpb91zcNgQe757QmVw3xrLH-781jF-UadgJLMa3nG8W93aBSuCa1A4vnt8JAroQOHXMywm6mGg-WmtIFzuy2vKS8-izKgYwcjptvL3vnzwRZV1uYB6ZezoeYlsw9Zoh5zPllu7LvUEQubL_fP6IqMLKj0nck0ULk-1WUeVrJpqI6K-3XEFa1SvWhAcv1Iiq7Vk-R-F29dT-m91HLcqTXXRgxw4rx_vAM59MhZvsrgT4RiShcpWpjC7ImsdkwiA9XuuDM8Oo4HWuYsETg8GpEwSY1QiHZ3aerMEjL0X_N4sPbWYL7sL8DvEu9JgAAymylxK_zHJZ5-t1VquH8oiWoSfjfcg3D6y8kIPWmPv6AFh_WdM1JjwFcj3-qm-CsMF0FQZUIDkXoARIqNKCv3v0E7VnO20IFz3VQYkr1GCVB2JdatLLRkdURpghQvaZB3PvSC2bji6_EpShnhTs2Ukgc7LjTzufTgf-5M2L4tFrjI9zAGiAlxF8TVHuBnPUpyeQchnpA9eiO2sEFA5OlNj8uOy86ySLpJc6j_qy-JlJ5TsHnQBKLXHK71f8v0qkKh_7Q5_cx4zw4CyiMZ2q_-ZQjfGGGtH1sL64pcu-8wrnAN7MNmUQe_yLvyP0Zhhg6EZ_ikBDEBQZl0Zh34uiaOfiVr3FM7s4kdwXdDI75PVjqzwNCIyi8Vg60dfVoDl7gVl-RCqbJLMxELKVqKh8_SpPbV5POTrdo9g1fxggJD7NfHc9rrYnFiLP234IW9rrVnN19kj6SKhNX_Lqi8C1Z0V0avYHs5hRw2HwsR3UZwtfAUa0Al7we8A0Y57VYzZ3LfSpHxZvhQHryN7LiRl6WHq-143uA5YUukwYQBXjIzLMcmQ-ApzB84gSax2bfWdifjU9ypIfgNz1U_N9VOQqYpPK1B0R7yk1nM7lm6M6Qne_i-86eX6ziN0Ukh-3z4EyRgasY1XOKD0p6Ci4vjVCouUfKDMXNKyu3iDfuVbZVYAuGg15VMazfWunZUvwMMFRI7dZEZ-6fVw5fZwgUmhxGK0HOTN3Xq9U6ZMFMVjZwN8cLzieJggTSnxc8F5kv5u45Kiul_Ymj65sDKXhJkyPTVwufSaU1OANpB_71UkmyvMH3ccRfDJ6Bc46jPWjjiQnsbpGlHQZEItkkKtLPn7HbCKr0ZsWZwnLBqR8aIouTU4D7DuhWIBVr4AL25Yg3apg0Yx_9DcgrZbGge9gpv6I6T1x1QXhujfDdos46cEdKXIrLMKjeReWrJW57UzKpnk1UIJrqtrw2jozv_ZfZ08gyq8N-U9Rajsj2quMwX1QIPkqtRlCzDJ4rovpftwLOtigf6Olzyg8ce3EdTTN_WwC82SIfHvVeXVwJWXWasaQJtouVp8vKkXIeYVZPdN-6iqkuTpJ5_lAn6AA7cLUJpSCK8Y7H4igK-A0oMRupOCuBjskBdTcGZuVssj6dnoiv27p8TOBZMTTGYqlN5vtLu5zlEah9fZgChqrFQsfAImEz6t_XRQpGA0tgoGkhrTOob1XCSw_E3HLY0oIKnG9juYrB8W-2GrjoXfTEDd-4D1o-pvlcKuzVCnRDx19ttAezikyroeIG8LbXnbjmErcNEGjLD7tihrijip7omFMHrBar_I1-lY6DMVlSdlkRKOg7R0YwubLR-wmY3A4dAezOCJZNxc20_JwFkCpaEfycHEbTSSSsWUX1M39i_7p8XhvJtqwvCnqh6qG_EtTR6uGkFofYCsPemASdROjZV-0iSqaRTWNunxrb9bzsWrr5s3rs3DV9S4XYs_Yujz4jIkCMsE2QnXD4QFFH4dWM7qCdjB-ug6QKjDVnNhMdHMTn-jV2NlELXfJ012fQtbRoPyKSM52SDR5iGBJgw-SNfo2cmDQB7RLg7abCsThqFm2hoMfTqR9eKGPZOrkF-RGPfH9bbrVoD3ZhoSRikAjnLUz_PK5zlwY-DWSOcMAbRixpgZcMJzaEMKcZhKfNj3HWfRTLAqT1IDpkFMsPOWUhDNMtYlMEFdECusq1fylVnuH0PUrZ-PP4.wD6CjlYiYXXSZH99w7rEGg; cf_clearance=Hb1T0c2d5MYNVghDTldsMwUCis6r8cn50sqrpg1aq4c-1702983713-0-1-fc1d5f96.15d1b4bf.ab1c0076-0.2.1702983713; intercom-session-dgkjq2bp=SjlyNUVTOWoxaTdXSEpKMXgycjN3SmhvTUV3K1lHMVhub1hXbHM2MW4vdW5nc1lPbGdvbjR1MCtNbzFtM25Kci0tRFYvOWlNTzVOVzR6RHdUT3V3YWZBQT09--f348bad5020456c9577a8ec158c5463a17e798c8; _uasid=\"Z0FBQUFBQmxnWGdsdEEtSV93eFN4bnRpbkYyb1U5dktvNEx6eE1JLU1VWXVmQzItRG4wdzBScFc1cVpBYXFlVF9FNUZJT1BLZVhhV2x4RzlESnNyMGtMMVdSMlRiODM5SHZhb0JmaTM3Z3dUQTNIN1ZFRnk2ZXBYdkhuckxvUFF6eWZneG1oT1dkbEFUd1pDUnMxVmJiR21Pd25vcGhES3Z2V3hhT2o2aE94LV9wckk2Zm91cXBTYldQU2NibkU1cEFrNTc0RVZfZWNxNkp6cTY1cWRFWnd4cUp2R1ZMTlRaMmZKYnA2UWNuUE1Dc2tmNzUtT3A2cGFLbEc3S3hlVmFMUGd0NlBvLWoydklLM0F3M0dWZ3pjZXRpekFWLXFGUkJ3VjhfaXdlcU5FWUxVTmhrUlpHSjFfMVgxM0ktUlJjRWFzRGxrcVEzRFM0VzI2a0dtNFQyTTlMZUpEZTZwRTRRPT0=\"; _umsid=\"Z0FBQUFBQmxnWGdscFZLdGhwRDRFMmtGM0dLRjJxbl9WVjFQck8tQXg2QlhuVFN0NGQySHJuQnpkcmUzdnA1MFlLOWFacHM1ZEZEWXBiTGpaR0hzcmVDdjFjdFJYaTFaSm1RWjBBVk9WemZwcUFOel9meVJmOHgyT2h1V0VaRlJSWC0zTDNqcVNpRnlkQWRkUmVnUTByUG5xQ1BuRnktMXByREFkSFZmcGZ5Rl9MbHlNdlYtcEhocUZHNHliWmpfMEJuYlgwUWtYY1ZFbkNyMUhiRkZRQWNVelREQ3p1MklWTWR6allER3Fhd2xpN3p1QjdQSUdwTT0=\"; _dd_s=rum=0&expire=1702984629391")
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

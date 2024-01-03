/**
 * @(#)ChatGPTController.java, 2023/12/13.
 *     <p/>
 *     Copyright 2023 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson2.JSON;
import com.chatgpt.chatgptweb.meta.ChatProcessParam;
import com.chatgpt.chatgptweb.service.FakeWebAPIChatServiceImpl;
import com.chatgpt.chatgptweb.service.OfficialAPIChatServiceImpl;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
@Slf4j
@RestController
public class ChatGPTController {

    @Resource
    private FakeWebAPIChatServiceImpl fakeWebAPIChatService;

    @Resource
    private OfficialAPIChatServiceImpl officialAPIChatService;

    @PostMapping("/api/session")
    public Mono<String> session() {
        return Mono.just("{\"status\":\"Success\",\"message\":\"\",\"data\":{\"auth\":false," +
            "\"model\":\"ChatGPTAPI\"}}");
    }

    @PostMapping("/api/chat-process")
    public Flux<String> chatProcess(ServerWebExchange serverWebExchange, @RequestBody ChatProcessParam param) {
        log.info("[op:chatProcess] param={}", JSON.toJSONString(param));
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return officialAPIChatService.chatWithGPT(param);
    }

}

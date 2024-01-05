/**
 * @(#)WebFluxFilter.java, 2024/1/3.
 *     <p/>
 *     Copyright 2024 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.config;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
@Slf4j
@Component
public class IpFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        InetSocketAddress inetSocketAddress = exchange.getRequest().getRemoteAddress();
        if (inetSocketAddress != null && inetSocketAddress.getAddress() != null) {
            log.info("[op:filter] ip={}", inetSocketAddress.getAddress().getHostAddress());
            return chain.filter(exchange);
        } else {
            log.warn("[op:filter] ip地址未获取到");
            return Mono.empty();
        }
    }
}

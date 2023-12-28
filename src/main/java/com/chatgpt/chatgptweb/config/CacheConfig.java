/**
 * @(#)CacheConfig.java, 2023/12/14.
 *     <p/>
 *     Copyright 2023 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chatgpt.chatgptweb.meta.Message;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
@Configuration
public class CacheConfig {

    @Bean
    public Cache<String, List<Message>> historyMessageCache() {
        return Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(30, TimeUnit.DAYS)
            .build();
    }

    @Bean
    public Cache<String, String> conversationIdCache() {
        return Caffeine.newBuilder()
            .maximumSize(100000)
            .expireAfterWrite(30, TimeUnit.DAYS)
            .build();
    }
}

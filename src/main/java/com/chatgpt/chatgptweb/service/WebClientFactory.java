/**
 * @(#)WebClientFactory.java, 2023/12/17.
 *     <p/>
 *     Copyright 2023 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.service;

import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
public class WebClientFactory {

    private static final WebClient instance = WebClient.create();

    private WebClientFactory() {
    }

    public static WebClient getInstance() {
        return instance;
    }
}

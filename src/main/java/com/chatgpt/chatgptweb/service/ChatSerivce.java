/**
 * @(#)ChatGPTSerivce.java, 2023/12/17.
 *     <p/>
 *     Copyright 2023 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.service;

import reactor.core.publisher.Flux;

import com.chatgpt.chatgptweb.meta.APIAccount;
import com.chatgpt.chatgptweb.meta.ChatProcessParam;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
public interface ChatSerivce {

    Flux<String> chatWithGPT(ChatProcessParam param);

    APIAccount getApiAccount();
}
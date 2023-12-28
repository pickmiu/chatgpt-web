/**
 * @(#)ChatCompletionParam.java, 2023/12/13.
 *     <p/>
 *     Copyright 2023 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.meta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.alibaba.fastjson2.annotation.JSONField;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatCompletionParam {

    private String model;

    private List<Message> messages;

    private Float temperature;

    private Float top_p;

//    @JSONField(name = "max_tokens")
//    private Integer maxTokens;

    private Float presence_penalty;

    private Boolean stream;


}

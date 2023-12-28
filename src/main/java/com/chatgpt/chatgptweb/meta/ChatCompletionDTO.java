/**
 * @(#)ChatCompletionDTO.java, 2023/12/14.
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

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatCompletionDTO {
    private String id;
    private String object;
    private Long created;
    private String model;
    private String system_fingerprint;
    private List<Choice> choices;
}

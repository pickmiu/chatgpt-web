/**
 * @(#)ChatProcessDTO.java, 2023/12/14.
 *     <p/>
 *     Copyright 2023 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.meta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatProcessDTO {
    private String role;
    private String id;
    private String parentMessageId;

    /**
     * 全部内容
     */
    private String text;

    /**
     * 本次内容
     */
    private String delta;
    private ChatCompletionDTO detail;
}

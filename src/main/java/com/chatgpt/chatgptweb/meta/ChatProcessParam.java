/**
 * @(#)ChatProcessParam.java, 2023/12/13.
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
public class ChatProcessParam {
    private String prompt;
    private Options options;
    private String systemMessage;
    private Float temperature;
    private Float top_p;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Options {
        private String parentMessageId;
    }
}

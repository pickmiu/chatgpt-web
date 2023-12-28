/**
 * @(#)WebAPIChatCompletionDTO.java, 2023/12/18.
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
public class WebAPIChatCompletionDTO {

    private Message message;

    @JSONField(name = "conversation_id")
    private String conversationId;

    @JSONField(name = "message_id")
    private String messageId;

    @JSONField(name = "is_completion")
    private Boolean isCompletion;
    private String error;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Message {
        private String id;
        private Author author;
//        private String create_time;
//        private String update_time;
        private Content content;
        private String status;
//        private Boolean end_turn;
        private Float weight;
        private Metadata metadata;
        private String recipient;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Author {
        private String role;
        private String name;
        private Metadata metadata;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Content {
        @JSONField(name = "content_type")
        private String contentType;
        private List<String> parts;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Metadata {

        @JSONField(name = "finish_details")
        private FinishDetails finishDetails;
//        private String inline_gizmo_id;

        @JSONField(name = "is_complete")
        private Boolean isComplete;

        @JSONField(name = "message_type")
        private String messageType;

        @JSONField(name = "model_slug")
        private String modelSlug;

        @JSONField(name = "parent_id")
        private String parentId;

        // private String timestamp_;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FinishDetails {
        private String type;

        @JSONField(name = "stop_tokens")
        private List<Integer> stopTokens;
    }
}

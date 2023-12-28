/**
 * @(#)ChatCompletionWebParam.java, 2023/12/17.
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
public class WebAPIChatCompletionParam {

    //    {
    //        "action": "next",
    //        "messages": [
    //        {
    //            "id": "aaa21447-733d-4c70-b99c-b9a532bc4de1",
    //            "author": {
    //            "role": "user"
    //        },
    //            "content": {
    //            "content_type": "text",
    //                "parts": [
    //            "解释下软件工程"
    //                ]
    //        },
    //            "metadata": {}
    //        }
    //    ],
    //        "parent_message_id": "aaa1a47a-fd03-403e-a68f-39fe702cb7d3",
    //        "model": "text-davinci-002-render-sha",
    //        "timezone_offset_min": -480,
    //        "suggestions": [
    //        "Give me 3 ideas about how to plan good New Years resolutions. Give me some that are personal, family, and professionally-oriented.",
    //            "Tell me a random fun fact about the Roman Empire",
    //            "Come up with 5 concepts for a retro-style arcade game.",
    //            "Make a content strategy for a newsletter featuring free local weekend events."
    //    ],
    //        "history_and_training_disabled": false,
    //        "arkose_token": null,
    //        "conversation_mode": {
    //        "kind": "primary_assistant"
    //    },
    //        "force_paragen": false,
    //        "force_rate_limit": false
    //    }

    private String action;
    @JSONField(name = "conversation_id")
    private String conversationId;

    @JSONField(name = "parent_message_id")
    private String parentMessageId;
    private String model;
    private List<Message> messages;

    @JSONField(name = "timezone_offset_min")
    private Long timezoneOffsetMin;

    @JSONField(name = "history_and_training_disabled")
    private Boolean historyAndTrainingDisabled;

    @JSONField(name = "arkose_token")
    private String arkoseToken;
    private List<String> suggestions;

    @JSONField(name = "conversation_mode")
    private ConversationMode conversationMode;

    @JSONField(name = "force_paragen")
    private Boolean forceParagen;

    @JSONField(name = "force_rate_limit")
    private Boolean forceRateLimit;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ConversationMode {
        private String kind;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Message {
        private String id;
        private Author author;
        private Content content;

        private Metadata metadata;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Author {
        private String role;
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

    public static class Metadata {
    }
}

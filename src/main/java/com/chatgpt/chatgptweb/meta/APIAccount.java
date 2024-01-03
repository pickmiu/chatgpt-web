/**
 * @(#)APIAccount.java, 2023/12/28.
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
public class APIAccount {

    public static final String SOURCE_OFFICIAL_API = "officialapi";
    public static final String SOURCE_WEB_API = "webapi";

    /**
     * 来源
     * officialapi
     * webapi
     */
    private String source;

    /**
     * if source is webapi, platform is required
     * microsoft
     * google
     */
    private String platform;

    private String username;

    private String password;

    private String accessToken;

    private String cookie;
}

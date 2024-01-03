/**
 * @(#)AdminController.java, 2023/12/28.
 *     <p/>
 *     Copyright 2023 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Queue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatgpt.chatgptweb.meta.APIAccount;
import com.chatgpt.chatgptweb.service.ChatAPIAccountManager;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private ChatAPIAccountManager chatAPIAccountManager;

    private boolean check(String appKey) {
        return "gnkjas1232njkfhds;langiua123svCjnkmkdamnglknd".equals(appKey);
    }

    @GetMapping("/getAllAccount")
    public Map<String, Queue<APIAccount>> getAllAccount(
        @RequestParam("appKey") String appKey) {
        if (!check(appKey)) {
            return null;
        }
        return chatAPIAccountManager.getAllApiAccount();
    }

    @PostMapping("/addAccount")
    public Boolean addAccount(@RequestBody APIAccount apiAccount,
        @RequestParam("appKey") String appKey) {
        if (!check(appKey)) {
            return false;
        }
        return chatAPIAccountManager.addApiAccount(apiAccount);
    }

    @PostMapping("/deleteAccount")
    public Boolean deleteAccount(@RequestBody APIAccount apiAccount,
        @RequestParam("appKey") String appKey) {
        if (!check(appKey)) {
            return false;
        }
        return chatAPIAccountManager.deleteAPIAccount(apiAccount);
    }

    @PostMapping("/updateAccount")
    public Boolean updateAccount(@RequestBody APIAccount apiAccount,
        @RequestParam("appKey") String appKey) {
        if (!check(appKey)) {
            return false;
        }
        return chatAPIAccountManager.updateAPIAccount(apiAccount);
    }
}

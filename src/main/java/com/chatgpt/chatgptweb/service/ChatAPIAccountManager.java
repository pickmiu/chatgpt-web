/**
 * @(#)ChatAuthenticationService.java, 2023/12/28.
 *     <p/>
 *     Copyright 2023 Netease, Inc. All rights reserved. NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *     terms.
 */
package com.chatgpt.chatgptweb.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.chatgpt.chatgptweb.meta.APIAccount;

/**
 * @author 唐李一 (tangliyi@corp.netease.com)
 */
@Slf4j
@Component
public class ChatAPIAccountManager {

    private Map<String, Queue<APIAccount>> apiAccountMap = new HashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @PostConstruct
    public void init() {
        // 初始化账户
    }

    public APIAccount getApiAccountByPolling(String source) {
        lock.writeLock().lock();
        try {
            Queue<APIAccount> apiAccountQueue = apiAccountMap.get(source);
            if (apiAccountQueue == null || apiAccountQueue.isEmpty()) {
                log.warn("[op:getApiAccountByPolling] source={} 认证信息为空", source);
                return null;
            }
            APIAccount apiAccount = apiAccountQueue.poll();
            apiAccountQueue.offer(apiAccount);
            return apiAccount;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean addApiAccount(APIAccount apiAccount) {
        lock.writeLock().lock();
        try {
            Queue<APIAccount> apiAccountQueue = apiAccountMap.get(apiAccount.getSource());
            if (apiAccountQueue != null) {
                apiAccountQueue.offer(apiAccount);
                return true;
            } else {
                return false;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Map<String, Queue<APIAccount>> getAllApiAccount() {
        lock.readLock().lock();
        try {
            return apiAccountMap;
        } finally {
            lock.readLock().unlock();
        }
    }

    public boolean updateAPIAccount(APIAccount apiAccount) {
        lock.writeLock().lock();
        try {
            Queue<APIAccount> apiAccountQueue = apiAccountMap.get(apiAccount.getSource());
            if (CollectionUtils.isEmpty(apiAccountQueue)) {
                apiAccountQueue.offer(apiAccount);
            }
            int size = apiAccountQueue.size();
            for (int i = 0; i < size; i++) {
                APIAccount account = apiAccountQueue.poll();
                if (account.getUsername().equals(apiAccount.getUsername())) {
                    apiAccountQueue.offer(apiAccount);
                    return true;
                } else {
                    apiAccountQueue.offer(account);
                }
            }
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean deleteAPIAccount(APIAccount apiAccount) {
        lock.writeLock().lock();
        try {
            Queue<APIAccount> apiAccountQueue = apiAccountMap.get(apiAccount.getSource());
            if (CollectionUtils.isEmpty(apiAccountQueue)) {
                return false;
            }
            int size = apiAccountQueue.size();
            for (int i = 0; i < size; i++) {
                APIAccount account = apiAccountQueue.poll();
                if (account.getUsername().equals(apiAccount.getUsername())) {
                    return true;
                } else {
                    apiAccountQueue.offer(account);
                }
            }
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }
}

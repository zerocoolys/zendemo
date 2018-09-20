package com.zendesk.demo.service;

import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionStore {

    Map<String, Long> sessionMap = new ConcurrentHashMap<>();


    public void store(long id, String token) {

        sessionMap.put(token, id);
    }

    public void delete(String token) {
        if (Strings.isNullOrEmpty(token)) {
            return;
        }
        sessionMap.remove(token);
    }

    public Long getUserId(String token) {
        return sessionMap.get(token);
    }
}

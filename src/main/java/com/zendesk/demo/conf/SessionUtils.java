package com.zendesk.demo.conf;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {


    public static long getUserId(HttpServletRequest request) {
        Object userId = request.getSession().getAttribute("userId");

        if (userId == null) {
            return -1;
        } else {
            return Long.parseLong(userId.toString());
        }

    }
}

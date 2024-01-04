package com.poploli.onlinefilmreviewsystem.util;

import jakarta.servlet.http.HttpSession;
import com.poploli.onlinefilmreviewsystem.exception.SessionNotFoundException;

public class SessionUtil {

    public static void checkSession(HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            throw new SessionNotFoundException("会话不存在或用户未登录");
        }
    }
}

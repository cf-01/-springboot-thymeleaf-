package com.demo.interceptor;

import com.demo.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); // 获取session对象，如果没有则返回null
        if (session == null || session.getAttribute("user") == null) {
            // 用户未登录，重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        } else {
            // 用户已登录,放行
            return true;
        }
    }
}

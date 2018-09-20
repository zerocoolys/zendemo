package com.zendesk.demo.conf;

import com.alibaba.fastjson.JSON;
import com.zendesk.demo.commons.WebResponse;
import com.zendesk.demo.service.SessionStore;
import com.zendesk.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter implements Filter {

    @Autowired
    private SessionStore sessionStore;

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            String url = ((HttpServletRequest) servletRequest).getRequestURI();

            if (!"/login".equals(url) && !"/register".equals(url)) {
                Object obj = ((HttpServletRequest) servletRequest).getSession().getAttribute("token");
                if (obj == null) {
                    writeMessage(JSON.toJSONString(new WebResponse().setCode(-1).setMsg("need login")), (HttpServletResponse) servletResponse);

                    return;
                } else {
                    String token = obj.toString();

                    Long userId = sessionStore.getUserId(token);
                    if (userId == null) {
                        writeMessage(JSON.toJSONString(new WebResponse().setCode(-1).setMsg("session not exists")), (HttpServletResponse) servletResponse);
                        return;
                    } else {
                        ((HttpServletRequest) servletRequest).getSession().setAttribute("userId", userId);
                    }
                }
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    private void writeMessage(String msg, HttpServletResponse servletResponse) throws IOException {
        servletResponse.getWriter().write(msg);
        servletResponse.getWriter().flush();
    }
}

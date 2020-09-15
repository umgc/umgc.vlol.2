package com.vlol.config;

import com.vlol.controller.Utils;
import com.vlol.service.LoginAttemptService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

public class FailedLoginAttemptFilter extends GenericFilterBean {

    private LoginAttemptService loginAttemptService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // GenericFilterBean doesn't have its own context, so instead of using @Autowired
        //  we have to grab the LoginAttemptService from the root app context manually
        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        if (loginAttemptService == null) {
            loginAttemptService = webApplicationContext.getBean(LoginAttemptService.class);
        }

        HttpServletRequest rq = (HttpServletRequest)request;
        String ip = Utils.getClientIP(rq);
        if(loginAttemptService.isBlocked(ip) && rq.getMethod().equals("POST") && rq.getRequestURI().equals("/login")) {
            // User is attempting to log in while their IP is blocked,
            //  so display an error message and skip authentication
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            RedirectStrategy rs = new DefaultRedirectStrategy();
            rs.sendRedirect(rq, (HttpServletResponse)response, "/login?blocked=true");
        }
        else {
            chain.doFilter(request, response);
        }
    }
    
}

/**
 * Extension and customization of Spring Boot's built-in WebSecurityConfigurerAdapter class.
 *
 * Java Runtime Environment (JRE) version used: 11.0.7
 * Java Development Kit (JDK) version used: 11.0.7
 *
 * Styling guide: Google Java Style Guide
 *     (https://google.github.io/styleguide/javaguide.html) and
 *     Code Conventions for the Java Programming Language (Oracle: Deprecated)
 *     (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category  vlol
 * @package config
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.config;

import com.vlol.controller.Utils;
import com.vlol.model.User;
import com.vlol.service.LoginAttemptService;
import com.vlol.service.UserService;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
        
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private UserService userService;

    @Autowired
    private LoginAttemptService loginAttemptService;

    public LoginSuccessHandler(UserService userService) {
        super();
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        handle(request, response, authentication);
    }

    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        User user = userService.userLogin(authentication.getName());
        if (response.isCommitted()) {
            return;
        }

//        String ip = Utils.getClientIP(request);
        loginAttemptService.loginSucceeded(request);

        if(!user.getIsVerified())
            redirectStrategy.sendRedirect(request, response, "/verify-email?error=true");
        else
            redirectStrategy.sendRedirect(request, response, "/menu");
    }

}
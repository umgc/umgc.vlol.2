/**
 * Extension and customization of Spring Boot's built-in AuthenticationFailureHandler class.
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
import com.vlol.service.LoginAttemptService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private LoginAttemptService loginAttemptService;

    public LoginFailureHandler() {
        super();
    }
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ae) throws IOException, ServletException {
        handle(request, response, ae);
    }

    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException ae) throws IOException {
        if (response.isCommitted()) {
            return;
        }
        loginAttemptService.loginFailed(request);
        if (loginAttemptService.isAccountLocked(request)) {
            redirectStrategy.sendRedirect(request, response, "/login?locked=true");
        }
        else {
            redirectStrategy.sendRedirect(request, response, "/login?error=true");
        }
//        String ip = Utils.getClientIP(request);
//        loginAttemptService.loginFailed(ip);
//        if (loginAttemptService.isBlocked(ip)) {
//            redirectStrategy.sendRedirect(request, response, "/login?blocked=true");
//        }
//        else {
//            redirectStrategy.sendRedirect(request, response, "/login?error=true");
//        }
    }
}

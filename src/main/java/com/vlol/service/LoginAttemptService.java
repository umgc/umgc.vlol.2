/**
 * Failed Login Attempt Service Class.
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
 * @package service
 * @license https://opensource.org/licenses/MIT The MIT License
 */

package com.vlol.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vlol.model.User;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Service
public class LoginAttemptService {

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    private final int MAX_ATTEMPT;  
//    private final int BLOCK_DURATION_MINS;
//    private LoadingCache<String,Integer> attemptsCache;

    public LoginAttemptService(Environment env) {
        super();

        this.MAX_ATTEMPT = Integer.parseInt(env.getProperty("server.vlol.failedlogin.maxattempts"));
//        this.BLOCK_DURATION_MINS = Integer.parseInt(env.getProperty("server.vlol.failedlogin.blockdurationmins"));
//
//        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(this.BLOCK_DURATION_MINS, TimeUnit.MINUTES).build(new CacheLoader<String,Integer>() {
//            @Override
//            public Integer load(final String ip) {
//                return 0;
//            }
//        });
    }

    public void loginSucceeded(HttpServletRequest request) {
        // Get user
        // Reset login_attempt
        // Write current date/time to last_login_date
        // Save user
        User user = userFromRequest(request);
        if (user != null) {
            user.setLoginAttempt(0);
            user.setLastLoginDate(nowAsDate());
            userService.updateUser(user);
        }
        else {
            // TODO: Throw exception?
        }
    }

    public void loginFailed(HttpServletRequest request) {
//        int attempts = 0;
//        try {
//            attempts = attemptsCache.get(ip);
//        } catch (final ExecutionException e) {
//            attempts = 0;
//        }
//        attempts++;
//        attemptsCache.put(ip, attempts);

        // Get user
        // increment login_attempt
        // write login attempt timestamp
        // Check if user is blocked
        // save user
        User user = userFromRequest(request);
        if (user != null) {
            user.incrementLoginAttept();
            user.setLastLoginAttempt(nowAsDate());

            if (user.getLoginAttempt() >= MAX_ATTEMPT) {
                user.setIsLocked(true);
                // TODO: Dispatch email to account holder
            }
            userService.updateUser(user);
        }
    }

    public boolean isAccountLocked(HttpServletRequest request) {
        User user = userFromRequest(request);
        if (user != null) {
            return user.getIsLocked();
        }
        return false;
    }
    
    public boolean isBlocked(HttpServletRequest request) {
//        try {
//            return attemptsCache.get(ip) >= MAX_ATTEMPT;
//        } catch (final ExecutionException e) {
//            return false;
//        }
        // Get user
        // compare login_attempt to max_attempt
        // get difference between timestamp and now
        // compare difference to block_duration_mins
        // return true or false
//        User user = userFromRequest(request);
//        long diffInMs = Math.abs(nowAsDate().getTime() - user.getLastLoginAttempt().getTime());
//        long blockDurationInMs = TimeUnit.MILLISECONDS.convert(BLOCK_DURATION_MINS, TimeUnit.MINUTES);
//
//        if (diffInMs < blockDurationInMs) {
//            return true;
//        }
//
        return false;
    }

    private User userFromRequest(HttpServletRequest request) {
        String email = request.getParameter("email");
        if (email != null && !email.isBlank()) {
            return userService.findUserByEmail(email);
        }
        return null;
    }

    private Date nowAsDate() {
        return Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
    }
}

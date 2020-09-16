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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Service
public class LoginAttemptService {

    @Autowired
    private Environment env;

    private final int MAX_ATTEMPT;  
    private final int BLOCK_DURATION_MINS;
    private LoadingCache<String,Integer> attemptsCache;

    public LoginAttemptService(Environment env) {
        super();

        this.MAX_ATTEMPT = Integer.parseInt(env.getProperty("server.vlol.failedlogin.maxattempts"));
        this.BLOCK_DURATION_MINS = Integer.parseInt(env.getProperty("server.vlol.failedlogin.blockdurationmins"));

        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(this.BLOCK_DURATION_MINS, TimeUnit.MINUTES).build(new CacheLoader<String,Integer>() {
            @Override
            public Integer load(final String ip) {
                return 0;
            }
        });
    }

    public void loginSucceeded(final String ip) {
        attemptsCache.invalidate(ip);
    }

    public void loginFailed(final String ip) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(ip);
        } catch (final ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(ip, attempts);
    }
    
    public boolean isBlocked(final String ip) {
        try {
            return attemptsCache.get(ip) >= MAX_ATTEMPT;
        } catch (final ExecutionException e) {
            return false;
        }
    }
}

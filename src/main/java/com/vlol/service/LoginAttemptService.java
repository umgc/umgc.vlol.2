/**
 * Failed Login Attempt Service Class.
 *
 * <p>Java Runtime Environment (JRE) version used: 11.0.7 Java Development Kit (JDK) version used:
 * 11.0.7
 *
 * <p>Styling guide: Google Java Style Guide (https://google.github.io/styleguide/javaguide.html)
 * and Code Conventions for the Java Programming Language (Oracle: Deprecated)
 * (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category vlol
 * @package service
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.service;

import com.vlol.Mailer;
import com.vlol.model.User;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

  @Autowired private Environment env;

  @Autowired private UserService userService;

  private final int MAX_ATTEMPT;
  //    private final int BLOCK_DURATION_MINS;
  //    private LoadingCache<String,Integer> attemptsCache;

  public LoginAttemptService(Environment env) {
    super();

    this.MAX_ATTEMPT = Integer.parseInt(env.getProperty("server.vlol.failedlogin.maxattempts"));
  }

  public void loginSucceeded(HttpServletRequest request) {
    User user = userFromRequest(request);
    if (user != null) {
      user.setLoginAttempt(0);
      user.setLastLoginDate(nowAsDate());
      userService.updateUser(user);
    } else {
      throw new RuntimeException("System logged in null user; this should never happen.");
    }
  }

  public void loginFailed(HttpServletRequest request) {
    User user = userFromRequest(request);
    if (user != null) {
      Date now = nowAsDate();
      int attemptExpiryDays =
          Integer.parseInt(env.getProperty("server.vlol.failedlogin.attemptexpirydays"));
      Date lastAttempt = user.getLastLoginAttempt();
      if (lastAttempt != null
          && (now.getTime() - lastAttempt.getTime() >= TimeUnit.DAYS.toMillis(attemptExpiryDays))) {
        // Failed login attempt expiry period has passed, so reset the counter
        user.setLoginAttempt(0);
      }
      user.incrementLoginAttempt();
      user.setLastLoginAttempt(now);

      if (user.getLoginAttempt() >= MAX_ATTEMPT) {
        user.setIsLocked(true);
        try {
          new Mailer(env).unlockAccount(user);
        } catch (Exception e) {
          // Always return success
        }
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

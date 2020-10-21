/**
 * Extension and customization of Spring Boot's built-in WebSecurityConfigurerAdapter class.
 *
 * <p>Java Runtime Environment (JRE) version used: 11.0.7 Java Development Kit (JDK) version used:
 * 11.0.7
 *
 * <p>Styling guide: Google Java Style Guide (https://google.github.io/styleguide/javaguide.html)
 * and Code Conventions for the Java Programming Language (Oracle: Deprecated)
 * (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category vlol
 * @package config
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.config;

import com.vlol.service.APIKeysService;
import com.vlol.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/** Extension and customization of Spring Boot's built-in WebSecurityConfigurerAdapter class. */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private DataSource dataSource;

  @Autowired UserService userService;

  @Autowired APIKeysService apiKeysService;

  public class APIKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private String principalRequestHeader;

    public APIKeyAuthFilter(String principalRequestHeader) {
      this.principalRequestHeader = principalRequestHeader;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
      return request.getHeader(principalRequestHeader);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
      return "N/A";
    }
  }

  @Configuration
  @Order(1)
  public class APISecurityAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
      APIKeyAuthFilter filter = new APIKeyAuthFilter("x-api-key");
      filter.setAuthenticationManager(
          new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication)
                throws AuthenticationException {
              String principal = (String) authentication.getPrincipal();
              if (apiKeysService.getAPIKey(principal) == null) {
                throw new BadCredentialsException(
                    "The API key was not found or not the expected value.");
              }
              authentication.setAuthenticated(true);
              return authentication;
            }
          });
      httpSecurity
          .antMatcher("/api/**")
          .csrf()
          .disable()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .addFilter(filter)
          .authorizeRequests()
          .anyRequest()
          .authenticated();
    }
  }

  @Configuration
  @Order(2)
  public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

    private final String USERS_QUERY =
        "select email, password, is_account_verified from appuser where email=?";
    private final String ROLES_QUERY =
        "select u.email, r.role_title from appuser u inner join approle r on (u.role_id=r.role_id) where u.email=?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication()
          .usersByUsernameQuery(USERS_QUERY)
          .authoritiesByUsernameQuery(ROLES_QUERY)
          .dataSource(dataSource);
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
      return authenticationManager();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
      return new LoginSuccessHandler(userService);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
      return new LoginFailureHandler();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
      httpSecurity.httpBasic();
      // For H2 console support only
      httpSecurity.headers().frameOptions().disable();
      httpSecurity
          .authorizeRequests()
          .antMatchers("/")
          .permitAll()
          .antMatchers("/index")
          .permitAll()
          .antMatchers("/about")
          .permitAll()
          .antMatchers("/contact")
          .permitAll()
          .antMatchers("/error")
          .permitAll()
          .antMatchers("/login")
          .permitAll()
          .antMatchers("/forgot-password")
          .permitAll()
          .antMatchers("/reset-password")
          .permitAll()
          .antMatchers("/verify-email")
          .permitAll()
          .antMatchers("/registration")
          .permitAll()
          .antMatchers("/qr-capture")
          .permitAll()
          // .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("actuator")
          .requestMatchers(EndpointRequest.toAnyEndpoint())
          .hasAuthority("admin")
          .antMatchers("/admin/**")
          .hasAnyAuthority("provider", "admin")
          .anyRequest()
          .authenticated()
          .and()
          .csrf()
          .disable()
          .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/menu")
          .usernameParameter("email")
          .passwordParameter("password")
          .successHandler(authenticationSuccessHandler())
          .failureHandler(authenticationFailureHandler())
          .permitAll()
          .and()
          .logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .logoutSuccessUrl("/")
          .and()
          .rememberMe()
          .tokenRepository(persistentTokenRepository())
          .tokenValiditySeconds(60 * 60)
          .and()
          .exceptionHandling()
          .accessDeniedPage("/access_denied")
          .and()
          .addFilterBefore(new LoginAttemptFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/img/**", "/DataTables/**");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
      JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
      db.setDataSource(dataSource);
      return db;
    }
  }
}

/**
 * Persistent Login Class.
 *
 * <p>Java Runtime Environment (JRE) version used: 11.0.7 Java Development Kit (JDK) version used:
 * 11.0.7
 *
 * <p>Styling guide: Google Java Style Guide (https://google.github.io/styleguide/javaguide.html)
 * and Code Conventions for the Java Programming Language (Oracle: Deprecated)
 * (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category vlol
 * @package model
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogins implements Serializable {

  @Id
  @Column(name = "series")
  private String series;

  @Column(name = "email", length = 320, unique = true)
  @NotBlank(message = "Email is required.")
  // Check if text is valid per RFC 3986.
  @Email(message = "Invalid email address.")
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(min = 5, max = 320, message = "Input exceeds size limits.")
  private String email;

  @Column(name = "token", nullable = false)
  private String token;

  @Column(name = "last_used", nullable = false)
  @NotNull(message = "Last used is required.")
  @Past(message = "Date must be in the past.")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date lastUsed;

  public String getSeries() {
    return series;
  }

  public void setSeries(String series) {
    this.series = series;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Date getLastUsed() {
    return lastUsed;
  }

  public void setLastUsed(Date lastUsed) {
    this.lastUsed = lastUsed;
  }
}

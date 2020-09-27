/**
 * Authorized User Class.
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
 * @package model
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "authorized_user")
public class AuthorizedUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorized_user_id")
    @Min(value = 1, message = "Value must be greater than 1.")
    @JsonIgnore
    private Long authorizedUserId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
//    @JsonBackReference
    private User user;
    
    @Column(name = "authorized_email", length = 320)
    @NotBlank(message = "Email is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Email(message = "Invalid email address.")
    // Check if length is valid per RFC 3986.
    @Size(min = 5, max = 320, message = "Input exceeds size limits.")
    private String authorizedEmail;
    
    @Column(name = "authorized_name", length = 128)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 128, message = "Input exceeds size limits.")
    private String authorizedName;
    

    public Long getAuthorizedUserId() {
        return authorizedUserId;
    }

    public void setAuthorizedUserId(Long authorizedUserId) {
        this.authorizedUserId = authorizedUserId;
    }

    public String getAuthorizedEmail() {
        return authorizedEmail;
    }

    public void setAuthorizedEmail(String authorizedEmail) {
        this.authorizedEmail = authorizedEmail;
    }
    
    public String getAuthorizedName() {
        return authorizedName;
    }

    public void setAuthorizedName(String authorizedName) {
        this.authorizedName = authorizedName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Override the toString() method so condition names will be shown in the
     * form. And equals() and hashCode() must be overridden so Spring MVC and
     * Thymeleaf will show the check marks correctly when the form is in edit
     * mode. Thanks to
     * https://www.codejava.net/frameworks/spring-boot/spring-thymeleaf-form-multi-checkboxes-mapping-with-collection-example
     */
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.authorizedEmail;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AuthorizedUser other = (AuthorizedUser) obj;
        if (authorizedUserId == null) {
            if (other.authorizedUserId != null) {
                return false;
            }
        } else if (!authorizedUserId.equals(other.authorizedUserId)) {
            return false;
        }
        return true;
    }
}

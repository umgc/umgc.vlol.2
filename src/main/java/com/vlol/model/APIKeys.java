/**
 * API Keys Class.
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

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "api_keys")
public class APIKeys implements Serializable {

    @Id
    @Column(name = "api_key")
    private String apiKey;
    
    
    @Column(name = "user_ref", length = 256)
    @NotBlank(message = "Email is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    private String userRef;
    
    @Column(name = "create_date")
    @Past(message = "Date must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date createDate = new Date();
    

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }
    
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        return this.apiKey;
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
        APIKeys other = (APIKeys) obj;
        if (apiKey == null) {
            if (other.apiKey != null) {
                return false;
            }
        } else if (!apiKey.equals(other.apiKey)) {
            return false;
        }
        return true;
    }
}

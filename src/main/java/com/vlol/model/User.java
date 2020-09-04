/**
 * User Class.
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
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "appuser")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Min(value = 1, message = "Value must be greater than 1.")
    private Long userID;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @Valid
    private Role role;

    @Column(name = "first_name", length = 50)
    @NotBlank(message = "First name is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 50, message = "Input exceeds size limits.")
    private String firstName;

    @Column(name = "last_name", length = 100)
    @NotBlank(message = "Last name is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 100, message = "Input exceeds size limits.")
    private String lastName;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "user_allergy",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id"))
    private Set<Allergy> allergies = new HashSet<>();


//    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//    @JoinTable(name = "authorized_user",
//            joinColumns = @JoinColumn(name = "authorized_email"),
//            inverseJoinColumns = @JoinColumn(name = "email"))
//    private Set<User> authorizedUsers = new HashSet<>();

//    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    @JoinTable(name = "authorized_user",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "authorized_user_id"))
//    private Set<AuthorizedUser> authorizedEmails = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "user_illness",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "illness_id"))
    private Set<Condition> conditions = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<AuthorizedUser> authorizedEmails = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserMedication> medications = new HashSet<>();

    @Column(name = "email", length = 320, unique = true)
    @NotBlank(message = "Email is required.")
    // Check if text is valid per RFC 3986.
    @Email(message = "Invalid email address.")
    // Check if length is valid per RFC 3986.
    @Size(min = 5, max = 320, message = "Input exceeds size limits.")
    private String email;

    @Column(name = "password", length = 72)
    @NotBlank(message = "Password is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    // bcrypt maximum password length is 71 characters + 1 byte null terminator
    @Size(min = 8, max = 72, message = "Input exceeds size limits.")
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name = "security_question", length = 100)
    @NotBlank(message = "Security question is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 100, message = "Input exceeds size limits.")
    private String securityQuestion;

    @Column(name = "security_answer", length = 72)
    @NotBlank(message = "Security answer is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    // bcrypt maximum input length is 71 characters + 1 byte null terminator
    @Size(max = 72, message = "Input exceeds size limits.")
    private String securityAnswer;

    @Column(name = "date_created")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:00")
    @NotNull(message = "Date account created is required.")
    @PastOrPresent(message = "Date account created cannot be in the future.")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_login_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:00")
    @NotNull(message = "Last login date is required.")
    @PastOrPresent(message = "Last login date cannot be in the future.")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    @Column(name = "admin_comments", length = 300)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 300, message = "Input exceeds size limits.")
    private String adminComments;

    @Column(name = "is_active")
    @NotNull(message = "Value cannot be null.")
    private Boolean isActive;

    @Column(name = "is_locked")
    @NotNull(message = "Value cannot be null.")
    private Boolean isLocked;
    
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user", optional=true)
    private UserInfo userInfo;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the validated first name of the user.
     *
     * @return the first name attribute.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name attribute if valid.
     *
     * @param firstName the first name value submitted by the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
//    public Set<User> getAuthorizedUsers() {
//        return authorizedUsers;
//    }
//
//    public void setAuthorizedUsers(Set<User> authorizedUsers) {
//        this.authorizedUsers = authorizedUsers;
//    }

    public Set<AuthorizedUser> getAuthorizedEmails() {
        return authorizedEmails;
    }

    public void setAuthorizedEmails(Set<AuthorizedUser> authorizedEmails) {
        this.authorizedEmails = authorizedEmails;
    }


    public Set<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<Allergy> allergies) {
        this.allergies = allergies;
    }

    public Set<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(Set<Condition> conditions) {
        this.conditions = conditions;
    }

    public Set<UserMedication> getMedications() {
        return medications;
    }

    public void setMedications(Set<UserMedication> medications) {
        this.medications = medications;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getAdminComments() {
        return adminComments;
    }

    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }
}

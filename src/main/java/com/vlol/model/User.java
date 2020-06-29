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
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    private Long userID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
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

    @Column(name = "dob")
    // @NotBlank(message = "DOB is required.")
    @Past(message = "Date must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date DOB;

    @Column(name = "ssn", length = 9, unique = true)
    @NotBlank(message = "SSN is required.")
    // Check if SSN is valid per the SSA.
    @Pattern(regexp = "^((?!000)(?!666)(?:[0-6]\\d{2}|7[0-2][0-9]|73[0-3]|7[5-6][0-9]|77[0-2]))((?!00)\\d{2})((?!0000)\\d{4})$", message = "Invalid SSN.")
    @Size(min = 9, max = 9, message = "Input exceeds size limits.")
    private String SSN;

    @Column(name = "street_address", length = 100)
    @NotBlank(message = "Street address is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 100, message = "Input exceeds size limits.")
    private String streetAddress;

    @Column(name = "city", length = 50)
    @NotBlank(message = "City name is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 50, message = "Input exceeds size limits.")
    private String city;

    @Column(name = "us_state", length = 2)
    @NotBlank(message = "State abbreviation is required.")
    // Check if US state code is valid.
    @Pattern(regexp = "^(?-i:A[LKSZRAEP]|C[AOT]|D[EC]|F[LM]|G[AU]|HI|I[ADLN]|K[SY]|LA|M[ADEHINOPST]|N[CDEHJMVY]|O[HKR]|P[ARW]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])$", message = "Invalid state code.")
    @Size(max = 2, message = "Input exceeds size limits.")
    private String state;

    @Column(name = "zipcode", length = 5)
    @NotBlank(message = "ZIP Code is required.")
    // Check if US ZIP Code is valid.
    @Pattern(regexp = "^\\d{5}$", message = "Input contains illegal characters.")
    @Size(min = 5, max = 5, message = "Input exceeds size limits.")
    private String zipCode;

    @Column(name = "phone", length = 10)
    @NotBlank(message = "Phone number is required.")
    // Check if phone number is valid.
    @Pattern(regexp = "^[2-9]\\d{2}\\d{3}\\d{4}$", message = "Invalid phone number.")
    @Size(min = 10, max = 10, message = "Input exceeds size limits.")
    private String phone;

    @Column(name = "ins_company", length = 50)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 50, message = "Input exceeds size limits.")
    private String insCompany;

    @Column(name = "ins_policy_no", length = 50)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 50, message = "Input exceeds size limits.")
    private String insPolicyNo;

    @Column(name = "adv_directive")
    private Boolean advDirective;

    @Column(name = "adv_dir_type", length = 50)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 50, message = "Input exceeds size limits.")
    private String advDirType;

    @Column(name = "poc_name", length = 100)
    @NotBlank(message = "A Point of Contact is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 100, message = "Input exceeds size limits.")
    private String pocName;

    @Column(name = "poc_phone", length = 10)
    @NotBlank(message = "A Point of Contact phone number is required.")
    // Check if phone number is valid.
    @Pattern(regexp = "^[2-9]\\d{2}\\d{3}\\d{4}$", message = "Invalid phone number.")
    @Size(min = 10, max = 10, message = "Input exceeds size limits.")
    private String pocPhone;

    @Column(name = "user_agent_id")
    private Long userAgentID;
    
    @Column(name = "doctor_name", length = 100)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 100, message = "Input exceeds size limits.")
    private String doctorName;

    @Column(name = "doctor_phone", length = 10)
    // Check if phone number is valid.
    @Pattern(regexp = "^[2-9]\\d{2}\\d{3}\\d{4}$", message = "Invalid phone number.")
    @Size(min = 10, max = 10, message = "Input exceeds size limits.")
    private String doctorPhone;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_allergy",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id"))
    private Set<Allergy> allergies = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_illness",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "illness_id"))
    private Set<Condition> conditions = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_medication",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private Set<Medication> medications = new HashSet<>();

    @Column(name = "user_comments", length = 300)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 300, message = "Input exceeds size limits.")
    private String userComments;

    @Column(name = "email", length = 320, unique = true)
    @NotBlank(message = "Email is required.")
    // Check if text is valid per RFC 3986.
    @Email(message = "Invalid email address.")
    // Check if length is valid per RFC 3986.
    @Size(min = 5, max = 320, message = "Input exceeds size limits.")
    private String email;

    @Column(name = "username", length = 320, unique = true)
    @NotBlank(message = "Username is required.")
    // Check if text is valid per RFC 3986.
    @Email(message = "Invalid email address.")
    // Check if length is valid per RFC 3986.
    @Size(min = 5, max = 320, message = "Input exceeds size limits.")
    private String username;

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
    // @NotBlank(message = "Date account created is required.")
    @PastOrPresent(message = "Date account created cannot be in the future.")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_login_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:00")
    // @NotBlank(message = "Last login date is required.")
    @PastOrPresent(message = "Last login date cannot be in the future.")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    @Column(name = "admin_comments", length = 300)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 300, message = "Input exceeds size limits.")
    private String adminComments;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @OneToMany(mappedBy = "user")
    Set<UserMedList> userMedList;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInsCompany() {
        return insCompany;
    }

    public void setInsCompany(String insCompany) {
        this.insCompany = insCompany;
    }

    public String getInsPolicyNo() {
        return insPolicyNo;
    }

    public void setInsPolicyNo(String insPolicyNo) {
        this.insPolicyNo = insPolicyNo;
    }

    public Boolean getAdvDirective() {
        return advDirective;
    }

    public void setAdvDirective(Boolean advDirective) {
        this.advDirective = advDirective;
    }

    public String getAdvDirType() {
        return advDirType;
    }

    public void setAdvDirType(String advDirType) {
        this.advDirType = advDirType;
    }

    public String getPocName() {
        return pocName;
    }

    public void setPocName(String pocName) {
        this.pocName = pocName;
    }

    public String getPocPhone() {
        return pocPhone;
    }

    public void setUserAgentID(Long userAgentID) {
        this.userAgentID = userAgentID;
    }

    public Long getUserAgentID() {
        return userAgentID;
    }
    
    public void setPocPhone(String pocPhone) {
        this.pocPhone = pocPhone;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
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

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public String getUserComments() {
        return userComments;
    }

    public void setUserComments(String userComments) {
        this.userComments = userComments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

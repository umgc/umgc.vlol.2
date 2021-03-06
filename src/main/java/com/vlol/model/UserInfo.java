/**
 * User Class.
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "info_id")
  @Min(value = 1, message = "Value must be greater than 1.")
  @JsonIgnore
  private Long infoId;

  @OneToOne
  @JoinColumn(name = "user_id")
  @NotNull(message = "Value cannot be null.")
  @JsonIgnore
  //    @JsonBackReference
  private User user;

  @Column(name = "dob")
  @Past(message = "Date must be in the past.")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  private Date DOB;

  @Column(name = "is_deceased")
  private Boolean isDeceased = false;

  @Column(name = "ssn", length = 9, unique = true)
  // Check if SSN is valid per the SSA.
  @Pattern(
      regexp =
          "^$|((?!000)(?!666)(?:[0-6]\\d{2}|7[0-2][0-9]|73[0-3]|7[5-6][0-9]|77[0-2]))((?!00)\\d{2})((?!0000)(\\d{4}))$",
      message = "Invalid SSN.")
  //    // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~: \\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 9, message = "Input exceeds size limits.")
  private String SSN;

  @Column(name = "street_address", length = 100)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 100, message = "Input exceeds size limits.")
  private String streetAddress;

  @Column(name = "city", length = 64)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 50, message = "Input exceeds size limits.")
  private String city;

  @Column(name = "us_state", length = 2)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 2, message = "Input exceeds size limits.")
  private String state;

  @Column(name = "zipcode", length = 5)
  // Check if US ZIP Code is valid.
  @Pattern(regexp = "^(\\d{5})?$", message = "Input contains illegal characters.")
  @Size(max = 5, message = "Input exceeds size limits.")
  private String zipCode;

  @Column(name = "phone", length = 10)
  // Check if phone number is valid.
  @Pattern(regexp = "^(\\d{5,32})?$", message = "Invalid phone number.")
  @Size(max = 32, message = "Input exceeds size limits.")
  private String phone;

  @Column(name = "ins_company", length = 64)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-.~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 50, message = "Input exceeds size limits.")
  private String insCompany;

  @Column(name = "ins_policy_no", length = 64)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-.~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 50, message = "Input exceeds size limits.")
  private String insPolicyNo;

  @Column(name = "npi", length = 10)
  // Check if text is valid per RFC 3986.
  @Pattern(regexp = "^[0-9]*$", message = "Input contains illegal characters.")
  @Size(max = 10, message = "Input exceeds size limits.")
  private String NPI;

  //    @Column(name = "adv_directive")
  //    @NotNull(message = "Value cannot be null.")
  //    private Boolean advDirective = false;
  //
  //    @Column(name = "adv_dir_type", length = 64)
  //    // Check if text is valid per RFC 3986.
  //    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input
  // contains illegal characters.")
  //    @Size(max = 50, message = "Input exceeds size limits.")
  //    private String advDirType;

  @Column(name = "poc_name", length = 100)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 100, message = "Input exceeds size limits.")
  private String pocName;

  @Column(name = "poc_phone", length = 32)
  // Check if phone number is valid.
  @Size(max = 32, message = "Input exceeds size limits.")
  @Pattern(regexp = "^$|[\\d\\+\\-\\.\\(\\)\\/\\s]*$", message = "Invalid phone number.")
  private String pocPhone;

  @Column(name = "doctor_name", length = 100)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 100, message = "Input exceeds size limits.")
  private String doctorName;

  @Column(name = "doctor_phone", length = 32)
  // Check if phone number is valid.
  @Size(max = 32, message = "Input exceeds size limits.")
  @Pattern(regexp = "^$|[\\d\\+\\-\\.\\(\\)\\/\\s]*$", message = "Invalid phone number.")
  private String doctorPhone;

  @Column(name = "user_comments", length = 65535)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 65535, message = "Input exceeds size limits.")
  private String userComments;

  public UserInfo(User user) {
    this.user = user;
  }

  public UserInfo() {}

  public Long getInfoId() {
    return infoId;
  }

  public void setInfoId(Long infoId) {
    this.infoId = infoId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getDOB() {
    return DOB;
  }

  public void setDOB(Date DOB) {
    this.DOB = DOB;
  }

  public Boolean getIsDeceased() {
    return isDeceased;
  }

  public void setIsDeceased(Boolean isDeceased) {
    this.isDeceased = isDeceased;
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

  public String getNPI() {
    return NPI;
  }

  public void setNPI(String NPI) {
    this.NPI = NPI;
  }

  //    public Boolean getAdvDirective() {
  //        return advDirective;
  //    }
  //
  //    public void setAdvDirective(Boolean advDirective) {
  //        this.advDirective = advDirective;
  //    }
  //
  //    public String getAdvDirType() {
  //        return advDirType;
  //    }
  //
  //    public void setAdvDirType(String advDirType) {
  //        this.advDirType = advDirType;
  //    }

  public String getPocName() {
    return pocName;
  }

  public void setPocName(String pocName) {
    this.pocName = pocName;
  }

  public String getPocPhone() {
    return pocPhone;
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

  public String getUserComments() {
    return userComments;
  }

  public void setUserComments(String userComments) {
    this.userComments = userComments;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((infoId == null) ? 0 : infoId.hashCode());
    return result;
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
    UserInfo other = (UserInfo) obj;
    if (infoId == null) {
      if (other.infoId != null) {
        return false;
      }
    } else if (!infoId.equals(other.infoId)) {
      return false;
    }
    return true;
  }
}

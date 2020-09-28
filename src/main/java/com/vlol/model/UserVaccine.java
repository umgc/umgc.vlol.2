package com.vlol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_vaccine")
public class UserVaccine implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vaccine_id")
  @Min(value = 1, message = "Value must be greater than 1.")
  @JsonIgnore
  private Long vaccineId;

  @Column(name = "vaccine_name", length = 256, unique = true)
  @NotBlank(message = "Vaccine name is required.")
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 256, message = "Input exceeds size limits.")
  private String vaccineName;

  @Column(name = "ref_id", length = 64)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 64, message = "Input exceeds size limits.")
  private String referenceId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  //    @JsonBackReference
  private User user;

  public Long getVaccineId() {
    return vaccineId;
  }

  public void setVaccineId(Long vaccineId) {
    this.vaccineId = vaccineId;
  }

  public String getVaccineName() {
    return vaccineName;
  }

  public void setVaccineName(String vaccineName) {
    this.vaccineName = vaccineName;
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Override the toString() method so vaccine names will be shown in the form. And equals() and
   * hashCode() must be overridden so Spring MVC and Thymeleaf will show the check marks correctly
   * when the form is in edit mode. Thanks to
   * https://www.codejava.net/frameworks/spring-boot/spring-thymeleaf-form-multi-checkboxes-mapping-with-collection-example
   */
  /** @return */
  @Override
  public String toString() {
    return this.vaccineName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((vaccineId == null) ? 0 : vaccineId.hashCode());
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
    UserVaccine other = (UserVaccine) obj;
    if (vaccineId == null) {
      if (other.vaccineId != null) {
        return false;
      }
    } else if (!vaccineId.equals(other.vaccineId)) {
      return false;
    }
    return true;
  }
}

/**
 * Medical Condition Class.
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
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "condition")
public class Condition implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "condition_id")
  @Min(value = 1, message = "Value must be greater than 1.")
  private Long conditionId;

  @Column(name = "condition_name", length = 256, unique = true)
  @NotBlank(message = "Condition name is required.")
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 256, message = "Input exceeds size limits.")
  private String conditionName;

  @Column(name = "ref_id", length = 64)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 64, message = "Input exceeds size limits.")
  private String referenceId;

  public Long getConditionId() {
    return conditionId;
  }

  public void setConditionId(Long conditionId) {
    this.conditionId = conditionId;
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public String getConditionName() {
    return conditionName;
  }

  public void setConditionName(String conditionName) {
    this.conditionName = conditionName;
  }

  /**
   * Override the toString() method so condition names will be shown in the form. And equals() and
   * hashCode() must be overridden so Spring MVC and Thymeleaf will show the check marks correctly
   * when the form is in edit mode. Thanks to
   * https://www.codejava.net/frameworks/spring-boot/spring-thymeleaf-form-multi-checkboxes-mapping-with-collection-example
   */
  /** @return */
  @Override
  public String toString() {
    return this.conditionName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((conditionId == null) ? 0 : conditionId.hashCode());
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
    Condition other = (Condition) obj;
    if (conditionId == null) {
      if (other.conditionId != null) {
        return false;
      }
    } else if (!conditionId.equals(other.conditionId)) {
      return false;
    }
    return true;
  }
}

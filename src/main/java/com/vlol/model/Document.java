/**
 * Document Class.
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
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "document")
public class Document implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "document_id")
  @Min(value = 1, message = "Value must be greater than 1.")
  private Long documentId;

  @Lob
  @Column(name = "document_file", columnDefinition = "BLOB")
  @NotNull(message = "File is required.")
  @JsonIgnore
  private byte[] documentFile;

  @Column(name = "document_type", length = 64)
  @NotBlank(message = "Type is required.")
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 64, message = "Input exceeds size limits.")
  private String documentType;

  @Column(name = "document_content_type", length = 128)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 128, message = "Input exceeds size limits.")
  private String documentContentType;

  @Column(name = "document_filename", length = 256)
  // Check if text is valid per RFC 3986.
  @Pattern(
      regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$",
      message = "Input contains illegal characters.")
  @Size(max = 128, message = "Input exceeds size limits.")
  private String documentFilename;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  //    @JsonBackReference
  private User user;

  public Long getDocumentId() {
    return documentId;
  }

  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  public byte[] getDocumentFile() {
    return documentFile;
  }

  public void setDocumentFile(byte[] documentFile) {
    this.documentFile = documentFile;
  }

  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  public String getDocumentContentType() {
    return documentContentType;
  }

  public void setDocumentContentType(String documentContentType) {
    this.documentContentType = documentContentType;
  }

  public String getDocumentFilename() {
    return documentFilename;
  }

  public void setDocumentFilename(String documentFilename) {
    this.documentFilename = documentFilename;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Override the toString() method so document names will be shown in the form. And equals() and
   * hashCode() must be overridden so Spring MVC and Thymeleaf will show the check marks correctly
   * when the form is in edit mode. Thanks to
   * https://www.codejava.net/frameworks/spring-boot/spring-thymeleaf-form-multi-checkboxes-mapping-with-collection-example
   */
  /** @return */
  @Override
  public String toString() {
    return "Advance Directive File: " + this.documentId.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
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
    Document other = (Document) obj;
    if (documentId == null) {
      if (other.documentId != null) {
        return false;
      }
    } else if (!documentId.equals(other.documentId)) {
      return false;
    }
    return true;
  }
}

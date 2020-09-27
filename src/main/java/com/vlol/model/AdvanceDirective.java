/**
 * AdvanceDirective Class.
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
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "advance_directive")
public class AdvanceDirective implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advance_directive_id")
    @Min(value = 1, message = "Value must be greater than 1.")
    private Long advanceDirectiveId;

    @Lob
    @Column(name = "advance_directive_file", columnDefinition="BLOB")
    @NotNull(message = "File is required.")
    @JsonIgnore
    private byte[] advanceDirectiveFile;

    @Column(name = "advance_directive_type", length = 64)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 64, message = "Input exceeds size limits.")
    private String advanceDirectiveType;
    
    @Column(name = "advance_directive_content_type", length = 128)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 128, message = "Input exceeds size limits.")
    private String advanceDirectiveContentType;
    
    @Column(name = "advance_directive_filename", length = 256)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 128, message = "Input exceeds size limits.")
    private String advanceDirectiveFilename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
//    @JsonBackReference
    private User user;
    
    public Long getAdvanceDirectiveId() {
        return advanceDirectiveId;
    }

    public void setAdvanceDirectiveId(Long advanceDirectiveId) {
        this.advanceDirectiveId = advanceDirectiveId;
    }

    public byte[] getAdvanceDirectiveFile() {
        return advanceDirectiveFile;
    }

    public void setAdvanceDirectiveFile(byte[] advanceDirectiveFile) {
        this.advanceDirectiveFile = advanceDirectiveFile;
    }
    
    public String getAdvanceDirectiveType() {
        return advanceDirectiveType;
    }

    public void setAdvanceDirectiveType(String advanceDirectiveType) {
        this.advanceDirectiveType = advanceDirectiveType;
    }
    
    public String getAdvanceDirectiveContentType() {
        return advanceDirectiveContentType;
    }

    public void setAdvanceDirectiveContentType(String advanceDirectiveContentType) {
        this.advanceDirectiveContentType = advanceDirectiveContentType;
    }
    public String getAdvanceDirectiveFilename() {
        return advanceDirectiveFilename;
    }

    public void setAdvanceDirectiveFilename(String advanceDirectiveFilename) {
        this.advanceDirectiveFilename = advanceDirectiveFilename;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Override the toString() method so advanceDirective names will be shown in the
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
        return "Advance Directive File: "+this.advanceDirectiveId.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((advanceDirectiveId == null) ? 0 : advanceDirectiveId.hashCode());
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
        AdvanceDirective other = (AdvanceDirective) obj;
        if (advanceDirectiveId == null) {
            if (other.advanceDirectiveId != null) {
                return false;
            }
        } else if (!advanceDirectiveId.equals(other.advanceDirectiveId)) {
            return false;
        }
        return true;
    }
}

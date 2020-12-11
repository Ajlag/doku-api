package org.i4di.account.dto;

import org.i4di.common.dto.TimestampedDTO;
import org.i4di.common.validator.Required;
import org.i4di.common.validator.user.PasswordMatch;
import org.i4di.common.validator.user.UniqueEmail;
import org.i4di.common.validator.user.UniqueUsername;
import org.i4di.common.validator.user.ValidEmailFormat;

import javax.validation.Valid;

public class UserRegisterDTO extends TimestampedDTO {

    @Required
    @UniqueUsername
    private String username;

    @PasswordMatch
    @Valid
    private PasswordDTO password;

    @Required
    @ValidEmailFormat
    @UniqueEmail
    private String email;

    @Required
    private String firstName;

    @Required
    private String lastName;

    private Boolean deleted;

    private Boolean active;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PasswordDTO getPassword() {
        return password;
    }

    public void setPassword(PasswordDTO password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getDeleted() {
        if (deleted == null) {
            deleted = false;
        }
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getActive() {
        if (active == null) {
            active = false;
        }
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

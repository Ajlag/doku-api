package org.i4di.account.dto;

import org.i4di.common.validator.Required;
import org.i4di.common.validator.user.PasswordMatch;

import javax.validation.Valid;
import java.io.Serializable;

public class PasswordResetDTO implements Serializable {

    @Required
    private String currentPassword;

    @PasswordMatch
    @Valid
    private PasswordDTO newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public PasswordDTO getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(PasswordDTO newPassword) {
        this.newPassword = newPassword;
    }
}

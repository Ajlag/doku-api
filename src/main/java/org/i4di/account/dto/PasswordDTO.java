package org.i4di.account.dto;

import org.i4di.common.validator.Required;

import java.io.Serializable;

public class PasswordDTO implements Serializable {

    @Required
    private String password;
    @Required
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

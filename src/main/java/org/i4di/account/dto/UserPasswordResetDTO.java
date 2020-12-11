package org.i4di.account.dto;

import org.i4di.common.validator.Required;

import java.io.Serializable;

public class UserPasswordResetDTO implements Serializable {

    @Required
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

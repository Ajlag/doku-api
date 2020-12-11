package org.i4di.account.service;

import org.i4di.account.dto.PasswordResetDTO;
import org.i4di.doku.dto.UserDTO;
import org.i4di.account.dto.UserRegisterDTO;

import java.util.Optional;

public interface AccountService {
    Optional<UserDTO> create(UserRegisterDTO user);

    boolean activateUser(String value);

    boolean passwordReset(String value, PasswordResetDTO password);
}

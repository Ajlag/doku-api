package org.i4di.account.service;

import org.i4di.doku.domain.Category;
import org.i4di.account.dto.TokenDTO;
import org.i4di.doku.dto.UserDTO;

import java.util.Optional;

public interface TokenService {
    Optional<TokenDTO> createToken(String email, Category category);

    boolean isValidToken(String value, Category category);
}

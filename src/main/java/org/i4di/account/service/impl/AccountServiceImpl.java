package org.i4di.account.service.impl;

import org.i4di.account.dto.PasswordResetDTO;
import org.i4di.account.repository.TokenRepository;
import org.i4di.account.service.TokenService;
import org.i4di.doku.domain.Category;
import org.i4di.doku.domain.Token;
import org.i4di.doku.domain.User;
import org.i4di.doku.dto.UserDTO;
import org.i4di.account.dto.UserRegisterDTO;
import org.i4di.doku.dto.mapper.UserMapper;
import org.i4di.account.dto.mapper.UserRegisterMapper;
import org.i4di.doku.repository.UserRepository;
import org.i4di.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserRegisterMapper userRegisterMapper;

    private TokenService tokenService;
    private TokenRepository tokenRepository;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, UserMapper userMapper, UserRegisterMapper userRegisterMapper, TokenService tokenService, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userRegisterMapper = userRegisterMapper;
        this.tokenService = tokenService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Optional<UserDTO> create(UserRegisterDTO user) {
        User toCreate = userRegisterMapper.userRegisterDTOToUser(user);

        return Optional.of(userMapper.userToUserDTO(
            userRepository.save(toCreate)
        ));
    }

    @Override
    public boolean activateUser(String value) {
        if (!tokenService.isValidToken(value, Category.ACCOUNT_VERIFICATION)) {
            return false;
        }

        Optional<Token> byValueAndCategory = tokenRepository.findByValueAndCategory(value, Category.ACCOUNT_VERIFICATION);
        User user = byValueAndCategory.get().getUser();

        userRepository.activateAccountByEmail(user.getEmail());

        return true;
    }

    @Override
    public boolean passwordReset(String value, PasswordResetDTO password) {
        if (!tokenService.isValidToken(value, Category.RESET_PASSWORD)) {
            return false;
        }

        Optional<Token> byValueAndCategory = tokenRepository.findByValueAndCategory(value, Category.RESET_PASSWORD);

        if (!byValueAndCategory.isPresent()) {
            return false;
        }

        Optional<User> userByEmail = userRepository.findByEmail(byValueAndCategory.get().getUser().getEmail());

        if (!userByEmail.isPresent()) {
            return false;
        }

        if (!userByEmail.get().getPassword().equals(password.getCurrentPassword())) {
            return false;
        }

        User updated = userByEmail.get();

        updated.setPassword(password.getNewPassword().getPassword());
        userRepository.save(updated);

        return true;
    }
}

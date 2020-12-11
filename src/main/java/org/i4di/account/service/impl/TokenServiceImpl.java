package org.i4di.account.service.impl;

import org.i4di.doku.domain.*;
import org.i4di.account.dto.TokenDTO;
import org.i4di.doku.dto.UserDTO;
import org.i4di.account.dto.mapper.TokenMapper;
import org.i4di.doku.dto.mapper.UserMapper;
import org.i4di.account.repository.TokenRepository;
import org.i4di.doku.repository.UserRepository;
import org.i4di.account.service.TokenService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    private TokenRepository tokenRepository;
    private TokenMapper tokenMapper;

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository, TokenMapper tokenMapper, UserRepository userRepository, UserMapper userMapper) {
        this.tokenRepository = tokenRepository;
        this.tokenMapper = tokenMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<TokenDTO> createToken(String email, Category category) {
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            return Optional.empty();
        }

        Token toCreate = new Token();

        String randomUUID = UUID.randomUUID().toString();
        DateTime expireTime = new DateTime().plusDays(7);

        toCreate.setCategory(category);
        toCreate.setValue(randomUUID);
        toCreate.setExpireTime(expireTime);
        toCreate.setUser(user.get());

        // TODO: 21-Oct-20 posalji token na tu email adresu (pending implementation)

        return Optional.of(tokenMapper.tokenToTokenDTO(
            tokenRepository.save(toCreate)));
    }

    @Override
    public boolean isValidToken(String value, Category category) {
        Optional<Token> byValueAndCategory = tokenRepository.findByValueAndCategory(value, category);

        if (!byValueAndCategory.isPresent()) {
            return false;
        }

        if (isExpired(byValueAndCategory.get().getValue())) {
            return false;
        }

        return true;
    }

    private boolean isExpired(String value) {
        Optional<Token> byExpiredTime = tokenRepository.findByValue(value);

        if (!byExpiredTime.isPresent()) {
            return false;
        }

        DateTime tokenExpiryTime = byExpiredTime.get().getExpireTime();
        return !tokenExpiryTime.isAfterNow();
    }
}

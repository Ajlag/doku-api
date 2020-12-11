package org.i4di.account.api;

import org.i4di.account.dto.PasswordResetDTO;
import org.i4di.account.dto.UserPasswordResetDTO;
import org.i4di.doku.domain.Category;
import org.i4di.account.dto.TokenDTO;
import org.i4di.doku.dto.UserDTO;

import org.i4di.account.dto.UserRegisterDTO;
import org.i4di.account.service.AccountService;
import org.i4di.account.service.TokenService;
import org.i4di.doku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountAPI {

    private AccountService accountService;
    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public AccountAPI(AccountService accountService, UserService userService, TokenService tokenService) {
        this.accountService = accountService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserRegisterDTO user) {
        Optional<UserDTO> createdUser = accountService.create(user);

        if (!createdUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<TokenDTO> token = tokenService.createToken(createdUser.get().getEmail(),
            Category.ACCOUNT_VERIFICATION);

        if (!token.isPresent()) {
            //ako se token kreira, posalji email sa token value
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/verify/{value}")
    public ResponseEntity<?> activateUser(@PathVariable(value = "value") String value) {
        boolean result = accountService.activateUser(value);

        return result
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/password/forgot")
    public ResponseEntity<?> passwordForgot(@Valid @RequestBody UserPasswordResetDTO user) {
        Optional<TokenDTO> token = tokenService.createToken(user.getEmail(),
            Category.RESET_PASSWORD);

        if (!token.isPresent()) {
            //ako se token kreira, posalji email sa token value
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/password/reset/{value}")
    public ResponseEntity<?> verifyPasswordReset(@PathVariable(value = "value") String value) {
        boolean result = tokenService.isValidToken(value, Category.RESET_PASSWORD);

        return result
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/password/reset/{value}")
    public ResponseEntity<?> passwordReset(@PathVariable(value = "value") String value,
                                           @Valid @RequestBody PasswordResetDTO password) {
        boolean result = accountService.passwordReset(value, password);

        return result
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

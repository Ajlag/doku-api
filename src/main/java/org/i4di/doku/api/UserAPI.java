package org.i4di.doku.api;

import org.i4di.doku.dto.UserDTO;
import org.i4di.account.dto.UserRegisterDTO;
import org.i4di.doku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserAPI {

    private UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        Optional<UserDTO> byId = userService.show(id);

        return byId
            .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UserRegisterDTO user) {
        user.setId(id);

        Optional<UserDTO> updated = userService.update(user);

        return updated
            .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> toggleDeleted(@PathVariable(value = "id") Long id) {
        boolean result = userService.delete(id);

        return result
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //handle User's roles requests

    @PutMapping(value = "/{userId}/roles/{roleId}")
    public ResponseEntity<?> addRole(@PathVariable(value = "userId") Long userId, @PathVariable(value = "roleId") Long roleId) {
        boolean result = userService.addRole(userId, roleId);

        return result
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{userId}/roles/{roleId}")
    public ResponseEntity<?> removeRole(@PathVariable(value = "userId") Long userId, @PathVariable(value = "roleId") Long roleId) {
        boolean result = userService.removeRole(userId, roleId);

        return result
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package org.i4di.doku.service;

import org.i4di.doku.dto.UserDTO;
import org.i4di.account.dto.UserRegisterDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> listAll();

    Optional<UserDTO> show(Long id);

    Optional<UserDTO> showByEmail(String email);

    Optional<UserDTO> showByUsername(String username);

    Optional<UserDTO> update(UserRegisterDTO user);

    boolean delete(Long id);

    boolean addRole(Long userId, Long roleId);

    boolean removeRole(Long userId, Long roleId);
}

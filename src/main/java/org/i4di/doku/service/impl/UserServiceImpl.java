package org.i4di.doku.service.impl;

import org.i4di.doku.domain.Role;
import org.i4di.doku.domain.User;
import org.i4di.doku.dto.UserDTO;
import org.i4di.doku.dto.mapper.RoleMapper;
import org.i4di.doku.dto.mapper.UserMapper;
import org.i4di.account.dto.UserRegisterDTO;
import org.i4di.account.dto.mapper.UserRegisterMapper;
import org.i4di.doku.repository.RoleRepository;
import org.i4di.doku.repository.UserRepository;
import org.i4di.doku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserRegisterMapper userRegisterMapper;

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserRegisterMapper userRegisterMapper, RoleRepository roleRepository, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userRegisterMapper = userRegisterMapper;
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<UserDTO> listAll() {
        return userMapper.usersToUserDTOs(
            userRepository.findByDeleted(false)
        );
    }

    @Override
    public Optional<UserDTO> show(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            return Optional.empty();
        }
        return byId.map(user -> userMapper.userToUserDTO(user));
    }

    @Override
    public Optional<UserDTO> showByEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);

        return byEmail.map(user -> userMapper.userToUserDTO(user));
    }

    @Override
    public Optional<UserDTO> showByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);

        return byUsername.map(user -> userMapper.userToUserDTO(user));
    }

    @Override
    public Optional<UserDTO> update(UserRegisterDTO user) {
        Optional<User> byId = userRepository.findById(user.getId());

        if (!byId.isPresent()) {
            return Optional.empty();
        }

        User updated = userRegisterMapper.userRegisterDTOToUser(user);

        return Optional.of(userMapper.userToUserDTO(
            userRepository.save(updated)
        ));
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> byId = userRepository.findById(id);

        if (!byId.isPresent()) {
            return false;
        }

        userRepository.delete(id);
        return true;
    }

    @Override
    public boolean addRole(Long userId, Long roleId) {
        Optional<User> userById = userRepository.findById(userId);
        Optional<Role> roleById = roleRepository.findById(roleId);

        if (!userById.isPresent() || !roleById.isPresent()) {
            return false;
        }

        if (userRepository.countUsersRoles(userId, roleId) > 0) {
            return false;
        }

        userRepository.addRole(userId, roleId);
        return true;
    }

    @Override
    public boolean removeRole(Long userId, Long roleId) {
        Optional<User> userById = userRepository.findById(userId);
        Optional<Role> roleById = roleRepository.findById(roleId);

        if (!userById.isPresent() || !roleById.isPresent()) {
            return false;
        }

        if (userRepository.countUsersRoles(userId, roleId) == 0) {
            return false;
        }

        userRepository.removeRole(userId, roleId);
        return true;
    }
}

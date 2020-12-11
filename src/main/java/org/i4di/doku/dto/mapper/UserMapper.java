package org.i4di.doku.dto.mapper;

import org.i4di.doku.domain.User;
import org.i4di.doku.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = User.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = User.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "roles", target = "roles")
    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUserDTOs(List<User> users);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = User.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = User.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "roles", target = "roles")
    User userDTOToUser(UserDTO userDTO);

    List<User> userDTOsToUsers(List<UserDTO> userDTOs);
}

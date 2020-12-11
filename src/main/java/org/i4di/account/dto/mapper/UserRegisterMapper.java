package org.i4di.account.dto.mapper;

import org.i4di.doku.domain.User;
import org.i4di.account.dto.UserRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRegisterMapper {
    //bespotrebno

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = User.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = User.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password.password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "active", target = "active")
    User userRegisterDTOToUser(UserRegisterDTO userRegisterDTO);

    List<User> userRegisterDTOsToUsers(List<UserRegisterDTO> userRegisterDTOS);
}

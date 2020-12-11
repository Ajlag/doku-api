package org.i4di.doku.dto.mapper;

import org.i4di.doku.domain.Role;
import org.i4di.doku.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = PermissionMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Role.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Role.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "permissions", target = "permissions")
    RoleDTO roleToRoleDTO(Role role);

    List<RoleDTO> rolesToRoleDTOs(List<Role> roles);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Role.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Role.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "permissions", target = "permissions")
    Role roleDTOToRole(RoleDTO roleDTO);

    List<Role> roleDTOsToRoles(List<RoleDTO> roleDTOs);
}

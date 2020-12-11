package org.i4di.doku.dto.mapper;

import org.i4di.doku.domain.Permission;
import org.i4di.doku.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Permission.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Permission.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "deleted", target = "deleted")
    PermissionDTO permissionToPermissionDTO(Permission permission);

    List<PermissionDTO> permissionsToPermissionDTOs(List<Permission> permissions);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Permission.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Permission.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "deleted", target = "deleted")
    Permission permissionDTOToPermission(PermissionDTO permissionDTO);

    List<Permission> permissionDTOsToPermissions(List<PermissionDTO> permissionDTOs);
}

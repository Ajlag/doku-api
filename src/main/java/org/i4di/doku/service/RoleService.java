package org.i4di.doku.service;

import org.i4di.doku.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTO> listAll();

    Optional<RoleDTO> show(Long id);

    Optional<RoleDTO> show(String name);

    Optional<RoleDTO> create(RoleDTO role);

    Optional<RoleDTO> update(RoleDTO role);

    boolean delete(Long id);

    boolean addPermissions(Long roleId, Long permissionId);

    boolean removePermission(Long roleId, Long permissionId);
}

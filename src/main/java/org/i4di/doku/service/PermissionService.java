package org.i4di.doku.service;

import org.i4di.doku.dto.PermissionDTO;

import java.util.List;
import java.util.Optional;

public interface PermissionService {

    List<PermissionDTO> listAll();

    Optional<PermissionDTO> show(Long id);

    Optional<PermissionDTO> show(String name);

    Optional<PermissionDTO> create(PermissionDTO permission);

    Optional<PermissionDTO> update(PermissionDTO permission);

    boolean delete(Long id);
}

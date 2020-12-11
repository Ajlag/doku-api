package org.i4di.doku.service.impl;

import org.i4di.doku.domain.Permission;
import org.i4di.doku.dto.PermissionDTO;
import org.i4di.doku.dto.mapper.PermissionMapper;
import org.i4di.doku.repository.PermissionRepository;
import org.i4di.doku.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    private PermissionRepository permissionRepository;
    private PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<PermissionDTO> listAll() {
        return permissionMapper.permissionsToPermissionDTOs(
            permissionRepository.findAll()
        );
    }

    @Override
    public Optional<PermissionDTO> show(Long id) {
        Optional<Permission> byId = permissionRepository.findById(id);

        return byId.map(permission -> permissionMapper.permissionToPermissionDTO(permission));
    }

    @Override
    public Optional<PermissionDTO> show(String name) {
        Optional<Permission> byName = permissionRepository.findByName(name);

        return byName.map(permission -> permissionMapper.permissionToPermissionDTO(permission));
    }

    @Override
    public Optional<PermissionDTO> create(PermissionDTO permission) {
        Permission toCreate = permissionMapper.permissionDTOToPermission(permission);

        return Optional.of(permissionMapper.permissionToPermissionDTO(
            permissionRepository.save(toCreate)
        ));
    }

    @Override
    public Optional<PermissionDTO> update(PermissionDTO permission) {
        Optional<Permission> byId = permissionRepository.findById(permission.getId());

        if (!byId.isPresent()) {
            return Optional.empty();
        }
        Permission updated = permissionMapper.permissionDTOToPermission(permission);

        return Optional.of(permissionMapper.permissionToPermissionDTO(
            permissionRepository.save(updated)
        ));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Permission> byId = permissionRepository.findById(id);

        if (!byId.isPresent()) {
            return false;
        }

        permissionRepository.delete(id);

        return true;
    }
}

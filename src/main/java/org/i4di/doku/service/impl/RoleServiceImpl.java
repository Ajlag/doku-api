package org.i4di.doku.service.impl;


import org.i4di.doku.domain.Permission;
import org.i4di.doku.domain.Role;
import org.i4di.doku.dto.RoleDTO;
import org.i4di.doku.dto.mapper.PermissionMapper;
import org.i4di.doku.dto.mapper.RoleMapper;
import org.i4di.doku.repository.PermissionRepository;
import org.i4di.doku.repository.RoleRepository;
import org.i4di.doku.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    private PermissionRepository permissionRepository;
    private PermissionMapper permissionMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<RoleDTO> listAll() {
        return roleMapper.rolesToRoleDTOs(
            roleRepository.findAll()
        );
    }

    @Override
    public Optional<RoleDTO> show(Long id) {
        Optional<Role> byId = roleRepository.findById(id);
        if (!byId.isPresent()) {
            return Optional.empty();
        }
        return byId.map(role -> roleMapper.roleToRoleDTO(role));
    }

    @Override
    public Optional<RoleDTO> show(String name) {
        Optional<Role> byName = roleRepository.findByName(name);

        return byName.map(role -> roleMapper.roleToRoleDTO(role));
    }

    @Override
    public Optional<RoleDTO> create(RoleDTO role) {
        Role toCreate = roleMapper.roleDTOToRole(role);

        return Optional.of(roleMapper.roleToRoleDTO(
            roleRepository.save(toCreate)
        ));
    }

    @Override
    public Optional<RoleDTO> update(RoleDTO role) {
        Optional<Role> byId = roleRepository.findById(role.getId());

        if (!byId.isPresent()) {
            return Optional.empty();
        }

        Role updated = roleMapper.roleDTOToRole(role);

        return Optional.of(roleMapper.roleToRoleDTO(
            roleRepository.save(updated)
        ));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Role> byId = roleRepository.findById(id);

        if (!byId.isPresent()) {
            return false;
        }

        roleRepository.delete(id);

        return true;
    }

    @Override
    public boolean addPermissions(Long roleId, Long permissionId) {
        Optional<Role> roleById = roleRepository.findById(roleId);
        Optional<Permission> permissionById = permissionRepository.findById(permissionId);

        if (!roleById.isPresent() || !permissionById.isPresent()) {
            return false;
        }

        if (roleRepository.countRolesPermission(roleId, permissionId) > 0) {
            return false;
        }

        roleRepository.addRolePermission(roleId, permissionId);
        return true;
    }


    @Override
    public boolean removePermission(Long roleId, Long permissionId) {
        Optional<Role> roleById = roleRepository.findById(roleId);
        Optional<Permission> permissionById = permissionRepository.findById(permissionId);

        if (!roleById.isPresent() || !permissionById.isPresent()) {
            return false;
        }

        if (roleRepository.countRolesPermission(roleId, permissionId) == 0) {
            return false;
        }

        roleRepository.removeRolePermission(roleId, permissionId);
        return true;
    }

}

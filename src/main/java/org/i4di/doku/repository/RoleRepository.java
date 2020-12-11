package org.i4di.doku.repository;

import org.i4di.doku.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();

    @Override
    Optional<Role> findById(Long id);

    Optional<Role> findByName(String name);

    @Query(value = "INSERT INTO roles_permissions(role_id, permission_id) VALUES (:roleId, :permissionId)", nativeQuery = true)
    @Modifying
    void addRolePermission(@Param(value = "roleId") Long roleId, @Param(value = "permissionId") Long permissionId);

    @Query(value = "DELETE FROM roles_permissions WHERE role_id =:roleId AND permission_id=:permissionId", nativeQuery = true)
    @Modifying
    void removeRolePermission(@Param(value = "roleId") Long roleId, @Param(value = "permissionId") Long permissionId);

    @Query(value = "UPDATE role SET deleted = TRUE WHERE id = :id ;", nativeQuery = true)
    @Modifying
    void delete(@Param(value = "id") Long id);

    @Query(value = "SELECT COUNT(*) FROM roles_permissions WHERE role_id=:roleId AND permission_id=:permissionId", nativeQuery = true)
    int countRolesPermission(@Param(value = "roleId") Long roleId, @Param(value = "permissionId") Long permissionId);
}

package org.i4di.doku.repository;

import org.i4di.doku.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findAll();

    @Override
    Optional<Permission> findById(Long id);

    Optional<Permission> findByName(String name);

    @Query(value = "UPDATE permission SET deleted = TRUE WHERE id = :id ;", nativeQuery = true)
    @Modifying
    void delete(@Param(value = "id") Long id);

}

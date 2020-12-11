package org.i4di.doku.repository;

import org.i4di.doku.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAll();

    List<Project> findByDeleted(boolean deleted);

    Optional<Project> findById(Long id);

    Optional<Project> findByName(String name);

    List<Project> findAllByOrderByNameAsc();

    @Query(value = "INSERT INTO projects_users(project_id, user_id) VALUES (:projectId, :userId)", nativeQuery = true)
    @Modifying
    void addUser(@Param(value = "projectId") Long projectId, @Param(value = "userId") Long userId);

    @Query(value = "DELETE FROM projects_users WHERE project_id =:projectId AND user_id=:userId", nativeQuery = true)
    @Modifying
    void removeUser(@Param(value = "projectId") Long projectId, @Param(value = "userId") Long userId);

    @Query(value = "UPDATE project SET deleted = TRUE WHERE id = :id ;", nativeQuery = true)
    @Modifying
    void delete(@Param(value = "id") Long id);

    @Query(value = "SELECT COUNT(*) FROM projects_users WHERE project_id=:projectId AND user_id=:userId", nativeQuery = true)
    int countProjectsUsers(@Param(value = "projectId") Long projectId, @Param(value = "userId") Long userId);
}

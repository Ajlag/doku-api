package org.i4di.doku.repository;

import org.i4di.doku.domain.Project;
import org.i4di.doku.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllBy();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findByDeleted(boolean deleted);

    @Query(value = "INSERT INTO users_roles(user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    @Modifying
    void addRole(@Param(value = "userId") Long userId, @Param(value = "roleId") Long roleId);

    @Query(value = "DELETE FROM users_roles WHERE user_id=:userId AND role_id=:roleId", nativeQuery = true)
    @Modifying
    void removeRole(@Param(value = "userId") Long userId, @Param(value = "roleId") Long roleId);

    @Query(value = "UPDATE user SET deleted = TRUE WHERE id = :id ;", nativeQuery = true)
    @Modifying
    void delete(@Param(value = "id") Long id);

    @Query(value = "SELECT COUNT(*) FROM users_roles WHERE user_id=:userId AND role_id=:roleId", nativeQuery = true)
    int countUsersRoles(@Param(value = "userId") Long userId, @Param(value = "roleId") Long roleId);

    @Query(value = "SELECT COUNT(*) FROM user WHERE user_id=:userId and email=:email", nativeQuery = true)
    int countUser(@Param(value = "userId") Long userId, @Param(value = "email") String email);

    @Query(value = "UPDATE user SET active = TRUE WHERE email = :email ;", nativeQuery = true)
    @Modifying
    void activateAccountByEmail(@Param(value = "email") String email);

    @Query(value = "SELECT COUNT(*) FROM user WHERE email=:email", nativeQuery = true)
    int countEmail(@Param(value = "email") String email);

    @Query(value = "UPDATE user SET password = :password WHERE email = :email ;", nativeQuery = true)
    @Modifying
    void newPassword(@Param(value = "password") String password, @Param(value = "email") String email);
}

package com.spring.jcompany.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u ORDER BY u.userLevel asc, u.userTeam desc")
    List<User> findAllDesc();

    @Query("SELECT u FROM User u WHERE u.userTeam = :userTeam ORDER BY u.userLevel asc")
    List<User> findAllByUserTeam(UserTeam userTeam);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:name% ORDER BY u.userLevel asc, u.userTeam desc")
    List<User> findAllByName(String name);

    @Query("SELECT u FROM User u WHERE u.userLevel = :userLevel ORDER BY u.userTeam desc ")
    List<User> findAllByUserLevel(UserLevel userLevel);

    @Query("SELECT u FROM User u WHERE u.userTeam = :userTeam and u.role = :role and u.userLevel < :userLevel ORDER BY u.userLevel asc")
    List<User> findAllByUserTeamAndRoleAndUserLevelIsGreaterThan(UserTeam userTeam, Role role, UserLevel userLevel);
}

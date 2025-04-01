package com.example.tpo_project_04_part_2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u")
    List<User> findAllUsers();

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    Optional<User> findUserById(Long id);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    List<User> findUsersByEmail(String email);
    @Query("SELECT u FROM User u WHERE :role MEMBER OF u.roles")
    List<User> findByRoles(Set<Role> roles);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (email) VALUES (?1)", nativeQuery = true)
    void insertUser(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = ?1")
    void deleteUserById(Long id);
}
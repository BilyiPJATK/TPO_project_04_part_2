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
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r")
    List<Role> findAllRoles();

    @Query("SELECT r FROM Role r WHERE r.id = ?1")
    Optional<Role> findRoleById(Long id);

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    List<Role> findRolesByName(String name);


    @Modifying
    @Transactional
    @Query("DELETE FROM Role r WHERE r.id = ?1")
    void deleteRoleById(Long id);
}


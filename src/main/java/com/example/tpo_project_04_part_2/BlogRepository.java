package com.example.tpo_project_04_part_2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b")
    List<Blog> findAllBlogs();

    @Query("SELECT b FROM Blog b WHERE b.id = :id")
    Optional<Blog> findBlogById(Long id);

    @Query("SELECT b FROM Blog b WHERE b.name = :name")
    List<Blog> findBlogsByName(String name);
    @Query("SELECT b FROM Blog b WHERE b.manager.id = :managerId")
    List<Blog> findByManager(Long managerId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Blog b WHERE b.id = :id")
    void deleteBlogById(Long id);
};


package com.example.tpo_project_04_part_2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a")
    List<Article> findAllArticles();

    @Query("SELECT a FROM Article a WHERE a.id = :id")
    Optional<Article> findArticleById(Long id);

    @Query("SELECT a FROM Article a WHERE a.title = :title")
    List<Article> findArticlesByTitle(String title);

    @Query("SELECT a FROM Article a WHERE a.author.id = :authorId")
    List<Article> findArticlesByAuthorId(Long authorId);

    @Query("SELECT a FROM Article a WHERE a.blog.id = :blogId")
    List<Article> findArticlesByBlogId(Long blogId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Article a WHERE a.id = :id")
    default void delete(Long id) {

    }


}
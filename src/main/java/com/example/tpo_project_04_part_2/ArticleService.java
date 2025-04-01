package com.example.tpo_project_04_part_2;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAll() {
        return articleRepository.findAllArticles();
    }

    public Optional<Article> getById(Long id) {
        return articleRepository.findArticleById(id);
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void delete(Long id) {
        articleRepository.delete(id);
    }
}

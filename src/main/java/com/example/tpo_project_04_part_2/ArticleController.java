package com.example.tpo_project_04_part_2;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ArticleController {

    private final ArticleService articleService;
    private final Scanner scanner = new Scanner(System.in);

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    public void runArticleMenu() {
        while (true) {
            System.out.println("\nArticle Management:");
            System.out.println("1. View all articles");
            System.out.println("2. Add article");
            System.out.println("3. Search article by ID");
            System.out.println("4. Delete article");
            System.out.println("5. Back to main menu");
            System.out.print("Select: ");

            switch (scanner.nextLine()) {
                case "1" -> displayAllArticles();
                case "2" -> addArticle();
                case "3" -> searchArticle();
                case "4" -> deleteArticle();
                case "5" -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void displayAllArticles() {
        List<Article> articles = articleService.getAll();
        if (articles.isEmpty()) {
            System.out.println("No articles found.");
            return;
        }
        articles.forEach(article -> System.out.println(article));
    }

    private void addArticle() {
        System.out.print("Enter article title: ");
        String title = scanner.nextLine();
        articleService.save(new Article(title, null, null));
        System.out.println("Article added.");
    }

    public void create(Article article){
        articleService.save(article);
    }

    private void searchArticle() {
        System.out.print("Enter article ID: ");
        long id = Long.parseLong(scanner.nextLine());
        articleService.getById(id).ifPresentOrElse(
                a -> System.out.println("Found: " + a.getTitle()),
                () -> System.out.println("Article not found!")
        );
    }

    private void deleteArticle() {
        System.out.print("Enter article ID to delete: ");
        long id = Long.parseLong(scanner.nextLine());
        if (articleService.getById(id).isPresent()) {
            articleService.delete(id);
            System.out.println("Article deleted.");
        } else {
            System.out.println("Article not found!");
        }
    }
}
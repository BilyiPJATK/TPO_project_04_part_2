package com.example.tpo_project_04_part_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

@SpringBootApplication
public class TpoProject04Part2Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TpoProject04Part2Application.class, args);

        UserController userController = context.getBean(UserController.class);
        RoleController roleController = context.getBean(RoleController.class);
        BlogController blogController = context.getBean(BlogController.class);
        ArticleController articleController = context.getBean(ArticleController.class);

        roleController.create(new Role("USER"));
        roleController.create(new Role("ADMIN"));

        for (int i = 1; i <= 20; i++) {
            String email = "user" + i + "@example.com";
            User user = new User(email, Set.of(roleController.getById(1)));
            userController.create(user);
        }

        for (int i = 1; i <= 20; i++) {
            String blogName = "Blog " + i;
            User manager = userController.getUserById((long) i);
            Blog blog = new Blog(blogName, manager);
            blogController.create(blog);
        }

        for (int i = 1; i <= 20; i++) {
            String articleTitle = "Article " + i;
            User author = userController.getUserById((long) i);
            Blog blog = blogController.getBlogById((long) i);
            Article article = new Article(articleTitle, author, blog);
            articleController.create(article);
        }

        System.out.println("Database initialized with 20 records per entity.");


        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\nMenu:");
            System.out.println("1. User CRUD");
            System.out.println("2. Article CRUD");
            System.out.println("3. Blog CRUD");
            System.out.println("4. Role CRUD");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userController.runUserMenu();
                    break;
                case "2":
                    articleController.runArticleMenu();
                    break;
                case "3":
                    blogController.runBlogMenu();
                    break;
                case "4":
                    roleController.runRoleMenu();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

    }
}

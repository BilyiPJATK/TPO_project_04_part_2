package com.example.tpo_project_04_part_2;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class BlogController {

    private final BlogService blogService;
    private final UserService userService;
    private final Scanner scanner;

    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void runBlogMenu() {
        while (true) {
            System.out.println("\nBlog Management:");
            System.out.println("1. Add Blog");
            System.out.println("2. Display All Blogs");
            System.out.println("3. Search Blog by ID");
            System.out.println("4. Delete Blog");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBlog();
                    break;
                case "2":
                    displayAllBlogs();
                    break;
                case "3":
                    searchBlogById();
                    break;
                case "4":
                    deleteBlog();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void addBlog() {
        System.out.print("Enter blog name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter manager's user ID: ");
        try {
            Long managerId = Long.parseLong(scanner.nextLine());
            User manager = userService.getById(managerId)
                    .orElseThrow(() -> new RuntimeException("User not found: "));

            if (manager.getManagedBlog() != null) {
                System.out.println("This user already manages blog");
                return;
            }
            Blog blog = new Blog(name, manager);
            blogService.save(blog);
            System.out.println("Blog added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }
    public void create(Blog blog){
        blogService.save(blog);
    }
    public Blog getBlogById(Long id) {
        return blogService.getById(id);
    }
    private void displayAllBlogs() {
        List<Blog> blogs = blogService.getAll();
        if (blogs.isEmpty()) {
            System.out.println("No blogs found.");
        } else {
            System.out.println("\nAll Blogs:");
            blogs.forEach(blog -> System.out.println(blog));
        }
    }

    private void searchBlogById() {
        System.out.print("Enter blog ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Blog blog = blogService.getById(id);

            System.out.println(blog);

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }

    private void deleteBlog() {
        System.out.print("Enter blog ID to delete: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            blogService.delete(id);

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }
}
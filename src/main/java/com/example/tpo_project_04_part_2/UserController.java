package com.example.tpo_project_04_part_2;

import org.springframework.stereotype.Component;

import java.net.CacheRequest;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final Scanner scanner;

    public UserController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void runUserMenu() {
        while (true) {
            System.out.println("\nUser Management:");
            System.out.println("1. Add User");
            System.out.println("2. Display All Users");
            System.out.println("3. Search User by ID");
            System.out.println("4. Delete User");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addUser();
                    break;
                case "2":
                    displayAllUsers();
                    break;
                case "3":
                    searchUserById();
                    break;
                case "4":
                    deleteUser();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void addUser() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        User newUser = new User(email, Set.of(roleService.getById(1L)));
        userService.save(newUser);
        System.out.println("User added successfully!");
    }
    public void create(User user){
        userService.save(user);
    }
    public User getUserById(Long id) {
        return userService.getById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    private void displayAllUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("\nAll Users:");
            users.forEach(user -> System.out.println(user.getId() + ": " + user.getEmail()));
        }
    }

    private void searchUserById() {
        System.out.print("Enter user ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Optional<User> user = userService.getById(id);
            if (user.isPresent()) {
                System.out.println(user);
            } else {
                System.out.println("User not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }

    private void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            if (userService.getById(id).isPresent()) {
                userService.delete(id);
            } else {
                System.out.println("User not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }

}
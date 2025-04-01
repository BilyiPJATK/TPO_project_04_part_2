package com.example.tpo_project_04_part_2;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class RoleController {
    private final RoleService roleService;
    private final Scanner scanner;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
        this.scanner = new Scanner(System.in);
    }

    public void runRoleMenu() {
        while (true) {
            System.out.println("\nRole Management:");
            System.out.println("1. Add Role");
            System.out.println("2. Display All Roles");
            System.out.println("3. Search Role by ID");
            System.out.println("4. Delete Role");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addRole();
                    break;
                case "2":
                    displayAllRoles();
                    break;
                case "3":
                    searchRoleById();
                    break;
                case "4":
                    deleteRole();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void addRole() {
        System.out.print("Enter role name: ");
        String name = scanner.nextLine().trim();
        roleService.save(new Role(name));
        System.out.println("Role added successfully!");
    }
    public void create(Role role){
        roleService.save(role);
    }
    public Role getById(long id){
        return roleService.getById(id);
    }

    private void displayAllRoles() {
        List<Role> roles = roleService.getAll();
        if (roles.isEmpty()) {
            System.out.println("No roles found.");
        } else {
            System.out.println("\nAll Roles:");
            roles.forEach(role -> System.out.println(role));
        }
    }

    private void searchRoleById() {
        System.out.print("Enter role ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Role role = roleService.getById(id);

            System.out.println(role);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }

    private void deleteRole() {
        System.out.print("Enter role ID to delete: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            roleService.delete(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }
}
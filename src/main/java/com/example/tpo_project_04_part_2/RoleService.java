package com.example.tpo_project_04_part_2;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<Role> getAll() {
        return roleRepository.findAllRoles();
    }

    public Role getById(Long id) {
        return roleRepository.findRoleById(id)
                .orElseThrow(() -> new RuntimeException("Role not found: " + id));
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void delete(Long id) {
        Role role = roleRepository.findRoleById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        List<User> users = userRepository.findByRoles(Set.of(role));
        if(!users.isEmpty()){
            System.out.println("Role has users, cannot delete");
            return;
        }

        roleRepository.deleteRoleById(role.getId());
        System.out.println("Role deleted successfully.");
    }
}

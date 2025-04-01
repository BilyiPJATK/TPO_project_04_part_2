package com.example.tpo_project_04_part_2;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final ArticleRepository articleRepository;

    public UserService(UserRepository userRepository,
                       BlogRepository blogRepository,
                       ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.articleRepository = articleRepository;
    }

    public List<User> getAll() {
        return userRepository.findAllUsers();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findUserById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Blog> managedBlogs = blogRepository.findByManager(user.getId());
        if(!managedBlogs.isEmpty()){
            System.out.println("User has blogs, cannot delete");
            return;
        }
        List<Article> authoredArticles = articleRepository.findArticlesByAuthorId(user.getId());
        if(!authoredArticles.isEmpty()){
            System.out.println("User has articles, cannot delete");
            return;
        }

        userRepository.deleteUserById(user.getId());
        System.out.println("User deleted successfully.");

    }
}


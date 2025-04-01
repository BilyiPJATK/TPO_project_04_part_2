package com.example.tpo_project_04_part_2;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public BlogService(BlogRepository blogRepository, ArticleRepository articleRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public List<Blog> getAll() {
        return blogRepository.findAllBlogs();
    }

    public Blog getById(Long id) {
        return blogRepository.findBlogById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }


    public void delete(Long id) {
        Blog blog = blogRepository.findBlogById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        if (!articleRepository.findArticlesByBlogId(blog.getId()).isEmpty()){
            System.out.println("blog has articles, cannot delete");
            return;
        }
        if (blog.getManager() != null) {
            User manager = blog.getManager();
            manager.setManagedBlog(null);
            userRepository.save(manager);
        }

        blogRepository.deleteBlogById(blog.getId());
        System.out.println("Blog deleted successfully.");
    }

}

package com.example.tpo_project_04_part_2;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "blog")
    private Set<Article> articles = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    public Blog() {}

    public Blog(String name, User manager) {
        this.name = name;
        this.manager = manager;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public User getManager() {
        return manager;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager=" + (manager != null ? manager.getId() : "null") +
                '}';
    }
}
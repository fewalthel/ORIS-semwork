package org.example.orissemwork.model;

public class Question {
    private Integer id;
    private String title;
    private String description;
    private User author;
    private Category category;

    public Question(Integer id, String title, String description, User author, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.category = category;
    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public User getAuthor() { return author; }

    public Integer getId() { return id; }

    public Category getCategory() { return category; }
}

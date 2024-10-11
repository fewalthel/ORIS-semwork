package org.example.orissemwork.model;

public class Question {
    private String title;
    private String description;
    private User author;

    public Question(String title, String description, User author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }

}

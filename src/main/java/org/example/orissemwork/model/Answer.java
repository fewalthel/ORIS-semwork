package org.example.orissemwork.model;

public class Answer {
    private Integer id;
    private Question question;
    private String content;
    private User author;

    public Answer(Integer id, Question question, String content, User author) {
        this.id = id;
        this.question = question;
        this.content = content;
        this.author = author;
    }

    public Question getQuestion() { return question; }

    public String getContent() { return content; }

    public User getAuthor() { return author; }

    public Integer getId() { return id; }
}

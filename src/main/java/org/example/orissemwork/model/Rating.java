package org.example.orissemwork.model;

public class Rating {
    private Answer answer;
    private User author;
    private Boolean liked;

    public Rating(Answer answer, User author, Boolean liked) {
        this.answer = answer;
        this.author = author;
        this.liked = liked;
    }

    public Answer getAnswer() { return answer; }
    public User getAuthor() { return author; }
    public Boolean getLiked() { return liked; }
}

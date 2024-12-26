package org.example.orissemwork.model;

public class User {
    private Integer id;
    private String email;
    private String username;
    private String password;
    private String role;

    public User(Integer id, String email, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getRole() { return role; }

    public Integer getId() { return id; }

    public void setPassword(String password) { this.password = password; }

}

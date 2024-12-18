<%@ page import="org.example.orissemwork.db.UserDAO" %>
<%@ page import="org.example.orissemwork.model.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <title>Account</title>
</head>
<link rel="stylesheet" type="text/css" href="views/universal.css">
<link rel="stylesheet" type="text/css" href="views/profile/profile.css">
<body>
<header>
    <div class="user-info">
            <span class="username">
                <h3>You are loginned as <a>${username}</a></h3>
            </span>
        <a class="button" href="signout">Sign Out</a>
    </div>
</header>
<nav class="sidebar">
    <ul>
        <li><a href="profile">About me</a></li>
        <li><a href="all_questions">All questions</a></li>
        <li><a href="my_questions">My questions</a></li>
        <li><a href="settings">Settings</a></li>
        <li><a href="favorites_answers">Favorites answers</a></li>
        <% User user = UserDAO.getByEmail((String) session.getAttribute("email"));
            if (user.getRole().equals("admin")) { %>
        <li><a href="all_users">All users</a></li>
        <%}%>
        <li><a href="main">Go to main</a></li>
    </ul>
</nav>
<main>
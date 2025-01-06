<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <title>Account</title>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>

</head>
<link rel="stylesheet" type="text/css" href="styles/universal.css">
<link rel="stylesheet" type="text/css" href="styles/profile.css">
<body>
<header>
    <div class="user-info">
        <span class="username"><strong>You are loginned as ${username}</strong></span>
        <a class="button" href="signout">Sign Out</a>
    </div>
</header>
<nav class="sidebar">
    <ul>
        <li><a href="profile">About me</a></li>
        <li><a href="all_questions">All questions</a></li>
        <li><a href="my_questions">My questions</a></li>
        <li><a href="favorites_answers">Favorites answers</a></li>
        <li><a href="settings">Settings</a></li>

        <c:if test="${role.equals('admin')}">
            <li><a href="admin_menu">Admin settings</a></li>
        </c:if>

        <li><a href="main">Go to main</a></li>
    </ul>
</nav>
<main>
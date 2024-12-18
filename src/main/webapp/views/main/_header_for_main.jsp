<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teach Your Friend</title>
</head>
<link rel="stylesheet" type="text/css" href="views/universal.css">
<link rel="stylesheet" type="text/css" href="views/main/main.css">
<body>
<header>
    <nav><ul>
        <li><a class="button">About Us</a>
            <ul class="dropdown">
                <p>This site was created for mutual assistance<br>
                    to each other in their studies.<br>
                    Here you can ask questions and<br>
                    other peers will answer you.<br>
                    Enjoy!</p>
            </ul>
        </li>

        <c:if test="${empty user}">
            <li><a class="button" href="signin">Sign In</a></li>
        </c:if>

        <c:if test="${not empty user}">
            <li><a class="button" href="profile">Profile</a></li>
            <li><a class="button" href="signout">Sign Out</a></li>
        </c:if>
    </ul></nav>

</header>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account</title>
</head>
<style>
    <%@include file="/styles/profile.css"%>
</style>
<body>
<header class="header">
    <div class="user-info">
        <span class="username"><h3>You are loginned as ${email}</h3></span>
        <a id="logout" href="<c:url value="/signout"/>">Sign Out</a>
    </div>
</header>

<nav class="sidebar">
    <ul>
        <li><a href="<c:url value="/profile"/>">About me</a></li>
        <li><a href="<c:url value="/all_questions"/>">All questions</a></li>
        <li><a href="<c:url value="/my_questions"/>">My questions</a></li>
        <li><a href="<c:url value="/settings"/>">Settings</a></li>
        <li><a href="<c:url value="/main"/>">Go to main</a></li>
    </ul>
</nav>
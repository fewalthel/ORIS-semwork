<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
</head>
<link rel="stylesheet" type="text/css" href="views/universal.css">
<link rel="stylesheet" type="text/css" href="views/register/register.css">
<body>
<div class="register-form">
    <h2>Tell us about yourself</h2>
    <div class="error-message">
        <p>${error}</p>
    </div>
    <form action="<c:url value=""/>" method="POST">
        <input type="text" id="username" name="username" placeholder="username" required>
        <input type="email" id="email" name="email" placeholder="example@email.com" required>
        <input type="password" id="password" name="password" placeholder="password" required>
        <div id="rule-for-password">
            <p>Your password must contain: at least one lowercase letter, at least one uppercase letter,
                at least one number, at least one special character. The password must be at least 8 characters
                long.</p>
            <br>
        </div>
        <input type="password" id="confirm-password" name="confirm_password" placeholder="confirm password" required>
        <button type="button" class="toggle-password"><span>show password</span></button>
        <div id="container-for-buttons">
            <button type="submit" id="register">Register</button>
        </div>
    </form>
</div>
<script src="js/hide_password_func.js"></script>
<script src="js/show_rules_for_password.js"></script>
<%@include file="/views/_footer.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authorization</title>
</head>
<style>
    <%@include file="/WEB-INF/styles/signin.css"%>
</style>
<body>
<div class="auth-form">
    <h2>Authorization</h2>
    <h4>please enter your email and password</h4>
    <form action="<c:url value="signin"/>" method="POST">
        <input type="email" placeholder="example@email.com" name="email" required <c:if test="${not empty email}"> value="<c:out value="${email}"/>"</c:if>>
        <input type="password" placeholder="password" id="password" name ="password" required>
        <button type="button" class="toggle-password"><span>show password</span></button>
        <div id="container-for-buttons">
            <button type="submit" class="button" id="sign-in">Sign In</button>
            <a href="register.html" class="button">I am a new user</a>
        </div>
    </form>
</div>
<script>
    <%@include file="/WEB-INF/js/script.js"%>
</script>
</body>
</html>
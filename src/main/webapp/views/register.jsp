<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register</title>
</head>
<style>
  <%@include file="/styles/register.css"%>
</style>
<body>
<div class="register-form">
  <div class="error-message">
    <p>${error}</p>
  </div>
  <h2>Tell us about yourself</h2>
  <form action="<c:url value="register"/>" method="POST">
    <input type="text" id="username" name="username" placeholder="username" required>
    <input type="email" id="email" name="email" placeholder="example@email.com" required>
    <input type="password" id="password" name="password" placeholder="password" required>
    <input type="password" id="confirm-password" name="confirm_password" placeholder="confirm password" required>
    <button type="button" class="toggle-password"><span>show password</span></button>
    <div id="container-for-buttons"><button type="submit" id="register">Register</button></div>
  </form>
</div>
<script><%@include file="/js/hide_password_func.js"%></script>
<%@include file="/views/footers/_footer.jsp"%>
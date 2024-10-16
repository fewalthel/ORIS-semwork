<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teach Your Friend</title>
</head>
<style>
    <%@include file="/styles/main.css"%>
</style>
<body>
<header>
    <nav><ul>
        <li><a class="button" href="#about-us">About Us</a></li>
        <c:if test="${empty user}">
            <li><a class="button" href="<c:url value="/signin"/>">Sign In</a></li>
        </c:if>

        <c:if test="${not empty user}">
            <li><a class="button" href="<c:url value="/profile"/>">Profile</a></li>
            <li><a class="button" href="<c:url value="/signout"/>">Sign Out</a></li>
        </c:if>
    </ul></nav>

</header>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <title>Confirm</title>
</head>
<link rel="stylesheet" type="text/css" href="views/universal.css">
<link rel="stylesheet" type="text/css" href="views/confirm/confirm.css">
<body>
<div class="confirm-form">
    <h2>Confirm your email</h2>

    <%String confirmationCode = (String) request.getSession().getAttribute("confirmationCode");
        if (confirmationCode != null) { %>

    <form action="<c:url value="confirm"/>" method="post">
        <input type="text" name="code" placeholder="enter your confirmation code">
        <div>
            <button id="confirm" type="submit">Confirm</button>
        </div>
    </form>

    <% } else { %>
    <p>Ошибка: код подтверждения не найден.</p>
    <% } %>
</div>
</body>
</html>
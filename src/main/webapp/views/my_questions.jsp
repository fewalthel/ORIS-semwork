<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/views/headers/_header_for_profile.jsp" %>

<%--форма для отправки вопроса--%>
<div id="container-for-content">
<section class="form-to-send-question">
    <h2>Ask your question to other peers here</h2><br>
    <form action="" method="POST">
        <div class="form-group">
            <label for="title">Title:</label><br>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="form-group"><br>
            <label for="description">Description:</label><br>
            <textarea id="description" name="description" required></textarea>
        </div><br>
        <button type="submit" id="ask-question">Ask a question</button>
    </form>
</section>
</div>

<%--страница должна принимать данные об авторизированном пользователе
и отображать только вопросы под его авторством--%>

<%@include file="/views/footers/_footer.jsp" %>
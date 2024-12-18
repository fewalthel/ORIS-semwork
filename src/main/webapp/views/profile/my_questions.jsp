<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<%@page import="org.example.orissemwork.model.Question" %>
<%@page import="org.example.orissemwork.db.QuestionDAO" %>
<%@page import="java.util.List" %>
<%@ page import="org.example.orissemwork.db.UserDAO" %>
<%@ page import="org.example.orissemwork.model.User" %>

<div id="container-for-content">
    <section class="form-to-send-question">
        <h2>Ask your question to other peers here</h2><br>
        <div class="error-message">
            <p>${error}</p>
        </div>
        <form action="<c:url value="my_questions"/>" method="POST">
            <div class="form-group">
                <label for="title">Title:</label><br>
                <input type="text" id="title" name="title" required>
                <select style="margin-left: 30px" name="category">
                    <option value="math">Math</option>
                    <option value="english">English</option>
                    <option value="physics">Physics</option>
                </select>
            </div>
            <div class="form-group"><br>
                <label for="description">Description:</label><br>
                <textarea id="description" name="description" required></textarea>
            </div>
            <br>
            <button type="submit" class="button">Submit</button>

        </form>
    </section>

    <ul>
        <% String emailOfAuthor = (String) session.getAttribute("email");
            User author = UserDAO.getByEmail(emailOfAuthor);

            List<Question> questions = QuestionDAO.getAllByAuthor(author);
            for (Question question : questions) { %>

        <%String url = "question?id=" + question.getId();%>
        <li>
            <%@include file="/views/profile/_question_model.jsp" %>
        </li>
        <%}%>
    </ul>
</div>
</main>

<%@include file="/views/_footer.jsp" %>
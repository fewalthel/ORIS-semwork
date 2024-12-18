<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/views/profile/_header_for_profile.jsp" %>

<%@ page import="org.example.orissemwork.db.*" %>
<%@ page import="org.example.orissemwork.model.*" %>
<%@ page import="java.util.List" %>

<div id="container-for-content">
    <ul>
        <li>
            <% Question question = QuestionDAO.getById(Integer.parseInt(request.getParameter("id")));
                String id = request.getParameter("id");%>

            <section class="question-card">
                <div style=" display: flex; justify-content: space-between;">
                    <h2 class="question_title"><%= question.getTitle() %></h2>
                    <p style="margin-top: 35px; opacity: 0.75;">category: <%= question.getCategory().getName() %></p>
                </div>
                <p class="question_description"><%= question.getDescription() %></p>
                <p class="question_author"> author: <%= question.getAuthor().getUsername()%></p>
            </section>
        </li>

        <section class="form-to-send-answer">
            <h2>Answer the question here</h2><br>
            <div class="error-message">
                <p>${error}</p>
            </div>
            <form action="question" method="POST">
                <div class="form-group">
                    <label for="answer">Add a answer:</label>
                    <br>
                    <input type="text" id="answer" name="answer" required>
                </div>
                <br>
                <input type="hidden" name="id" value="<%=id%>">
                <button type="submit" class="button">Send</button>
            </form>
        </section>


        <%  List<Answer> answers = AnswerDAO.getAllByQuestion(question);
            for (Answer answer : answers) { %>
        <li>
            <%@include file="/views/profile/_answer_model.jsp" %>
        </li>
        <%}%>
    </ul>
</div>
</main>
<%@include file="/views/_footer.jsp" %>
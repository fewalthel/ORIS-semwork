<%@ page import="org.example.orissemwork.db.WorkWithDBForQuestion" %>
<%@ page import="org.example.orissemwork.model.Question" %>
<%@ page import="org.example.orissemwork.model.Answer" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.orissemwork.db.WorkWithDBForAnswer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
    <ul>
        <li>
            <% Question question = WorkWithDBForQuestion.getQuestionById(Integer.parseInt(request.getParameter("id")));
                String title = question.getTitle();
                String description = question.getDescription();
                String author = question.getAuthor().getEmail();
            String id = request.getParameter("id");%>

            <section class="question-card">
                <h2 class="question_title"><%= title %></h2>
                <p class="question_description"><%= description %></p>
                <p class="question_author"><%= author%></p>
            </section>
        </li>

        <section class="form-to-send-answer">
            <h2>Answer the question here</h2><br>
            <div class="error-message">
                <p>${error}</p>
            </div>
            <form action="<c:url value="question"/>" method="POST">
                <div class="form-group">
                <label for="answer">Add a answer:</label>
                <br>
                <input type="text" id="answer" name="answer" required>
                </div>
                <input type="hidden" name="id" value="<%=id%>">
                <br>
                <button type="submit" id="add-answer">Submit</button>
            </form>
        </section>


        <%  List<Answer> answers = WorkWithDBForAnswer.getAllAnswersByQuestion(question);
            for (Answer answer : answers) { %>
        <li>
            <section class="answer-card">
                <h2 class="question_title"><%= question.getTitle() %></h2>
                <p class="answer_description"><%= answer.getContent() %></p>
                <p class="answer_author"><%= answer.getAuthor().getEmail() %></p>
            </section>
        </li>
        <%}%>
    </ul>
</div>

<%@include file="/views/_footer.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<%@page import="org.example.orissemwork.model.Question" %>
<%@page import="org.example.orissemwork.db.WorkWithDBForQuestion" %>
<%@page import="java.util.List" %>

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
        </div>
        <div class="form-group"><br>
            <label for="description">Description:</label><br>
            <textarea id="description" name="description" required></textarea>
        </div><br>
        <button type="submit" id="ask-question">Submit</button>
    </form>
</section>

    <ul>
        <%  String author = (String) session.getAttribute("email");
            List<Question> questions = WorkWithDBForQuestion.getQuestionsByAuthor(author);
            for (Question question : questions) { %>

        <%String url = "question?id="+question.getId();%>
        <li>
            <section class="question-card">
                <a href=${pageContext.request.contextPath}/<%=url%>><h2 class="question_title"><%= question.getTitle() %></h2></a>
                <p class="question_description"><%= question.getDescription() %></p>
                <p class="question_author"><%= question.getAuthor().getEmail() %></p>
            </section>
        </li>
        <%}%>
    </ul>
</div>

<%@include file="/views/_footer.jsp" %>
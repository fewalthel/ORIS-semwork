<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<%@page import="org.example.orissemwork.model.Question" %>
<%@page import="org.example.orissemwork.db.WorkWithDBForQuestion" %>
<%@page import="java.util.List" %>

<div id="container-for-content">
    <ul>
        <%List<Question> questions = WorkWithDBForQuestion.getAllQuestions();
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
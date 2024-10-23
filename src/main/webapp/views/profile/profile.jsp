<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<%@ page import="org.example.orissemwork.db.WorkWithDBForQuestion" %>
<%@ page import="org.example.orissemwork.db.WorkWithDBForAnswer" %>

<div id="container-for-content">
    <section id="about-user" class="statistics-card">
        <h2>My username: ${username}</h2>
        <h2>My email: ${email}</h2>
    </section>

    <section id="questions" class="statistics-card">
        <h2>Questions asked</h2>
        <p>Total: <span><%  String author = (String) session.getAttribute("email");%>
            <%=WorkWithDBForQuestion.getQuestionsByAuthor(author).size()%></span></p>
    </section>

    <section id="answers" class="statistics-card">
        <h2>Answers given</h2>
        <p>Total: <span><%=WorkWithDBForAnswer.getAnswersByAuthor(author).size()%></span></p>
    </section>

</div>

<%@include file="/views/_footer.jsp" %>
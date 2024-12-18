<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<%@ page import="org.example.orissemwork.db.QuestionDAO" %>
<%@ page import="org.example.orissemwork.db.AnswerDAO" %>
<%@ page import="org.example.orissemwork.model.User" %>
<%@ page import="org.example.orissemwork.db.UserDAO" %>

<div id="container-for-content">
    <section id="about-user" class="statistics-card">
        <h2>My username: ${username}</h2>
        <h2>My email: ${email}</h2>
    </section>

    <section id="questions" class="statistics-card">
        <h2>Questions asked</h2>
        <p>Total: <span><% String emailOfAuthor = (String) session.getAttribute("email");

            User author = UserDAO.getByEmail(emailOfAuthor);%>
            <%=QuestionDAO.getAllByAuthor(author).size()%></span></p>
    </section>

    <section id="answers" class="statistics-card">
        <h2>Answers given</h2>
        <p>Total: <span><%=AnswerDAO.getAllByAuthor(author).size()%></span></p>
    </section>

</div>
</main>

<%@include file="/views/_footer.jsp" %>
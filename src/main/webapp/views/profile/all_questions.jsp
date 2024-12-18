<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<%@page import="org.example.orissemwork.model.Question" %>
<%@page import="org.example.orissemwork.db.QuestionDAO" %>
<%@page import="java.util.List" %>

<div id="container-for-content">
    <ul>
        <%List<Question> questions = QuestionDAO.getAll();
            for (Question question : questions) { %>

        <%String url = "question?id="+question.getId();%>
        <li>
            <%@include file="/views/profile/_question_model.jsp" %>
        </li>
        <%}%>
    </ul>
</div>

</main>
<%@include file="/views/_footer.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<%@page import="java.util.List" %>
<%@ page import="org.example.orissemwork.model.*" %>
<%@page import="org.example.orissemwork.db.*" %>

<div id="container-for-content">
  <ul>
    <%List<Answer> favorite_answers = AnswerDAO.getFavoriteAnswers(user);

      for (Answer answer : favorite_answers) {
      Question question = answer.getQuestion();%>

      <li>
        <%@include file="/views/profile/_answer_model.jsp" %>
      </li>
    <%}%>

  </ul>
</div>
</main>
<%@include file="/views/_footer.jsp" %>
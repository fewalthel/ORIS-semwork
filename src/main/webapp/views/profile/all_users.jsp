<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/views/profile/_header_for_profile.jsp" %>

<%@ page import="org.example.orissemwork.model.User" %>
<%@ page import="org.example.orissemwork.db.*" %>


<div id="container-for-content">
    <ul>
        <%
            List<User> users = UserDAO.getAll();
            for (User userList : users) {%>
        <li>
            <%@include file="/views/profile/_user_model.jsp" %>
        </li>
        <%}%>
    </ul>
</div>
<%@include file="/views/_footer.jsp" %>
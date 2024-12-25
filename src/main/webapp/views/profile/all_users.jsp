<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/views/profile/_header_for_profile.jsp" %>


<div id="container-for-content">
    <ul>
        <c:forEach items="${all_users}" var="userList">
            <li>
                <%@include file="/views/profile/_user_model.jsp" %>
            </li>
        </c:forEach>

    </ul>
</div>
<%@include file="/views/_footer.jsp" %>
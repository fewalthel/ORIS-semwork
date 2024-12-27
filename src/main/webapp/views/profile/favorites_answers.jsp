<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
    <ul>

        <c:forEach items="${favorites_answers}" var="answer">
            <c:set var="question" value="${answer.getQuestion()}"/>
            <li>
                <%@include file="/views/profile/_answer_model.jsp" %>
            </li>
        </c:forEach>

    </ul>
</div>
</main>
<%@include file="/views/_footer.jsp" %>
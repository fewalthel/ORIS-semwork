<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
    <ul>
        <c:forEach items="${all_questions}" var="question">
            <c:set var="url" value="${pageContext.request.contextPath}/question?id=${question.getId()}" />

            <li>
                <%@include file="/views/profile/_question_model.jsp" %>
            </li>
        </c:forEach>
    </ul>
</div>

</main>
<%@include file="/views/_footer.jsp" %>
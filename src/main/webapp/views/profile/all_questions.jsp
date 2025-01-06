<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/views/profile/_header_for_profile.jsp" %>


<div id="container-for-content">
    <div class="menu-card">
        <h1>Questions by categories</h1>
        <a href="${pageContext.request.contextPath}/all_questions">all questions</a>

        <c:forEach items="${all_categories}" var="categoryList">
            <a href="${pageContext.request.contextPath}/all_questions?category=${categoryList.getName()}">${categoryList.getName()}</a>
        </c:forEach>
    </div>

    <c:if test="${not empty all_questions}">
        <ul>
            <c:forEach items="${all_questions}" var="question">
                <c:set var="url" value="${pageContext.request.contextPath}/question?id=${question.getId()}"/>
                <li>
                    <%@include file="/views/profile/_question_model.jsp" %>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${not empty questions_by_category}">
        <ul>
            <c:forEach items="${questions_by_category}" var="question">
                <c:set var="url" value="${pageContext.request.contextPath}/question?id=${question.getId()}"/>
                <li>
                    <%@include file="/views/profile/_question_model.jsp" %>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</div>
</main>
</body>
</html>
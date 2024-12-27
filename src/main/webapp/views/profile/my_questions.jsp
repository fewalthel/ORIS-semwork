<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
    <section class="form-to-send-question">
        <h2>Ask your question to other peers here</h2><br>
        <div class="error-message">
            <p>${error}</p>
        </div>
        <form action="my_questions" method="POST">
            <div class="form-group">
                <label for="title">Title:</label><br>
                <input type="text" id="title" name="title" required>
                <select style="margin-left: 30px" name="category">
                    <option value="math">Math</option>
                    <option value="english">English</option>
                    <option value="physics">Physics</option>
                </select>
            </div>
            <div class="form-group"><br>
                <label for="description">Description:</label><br>
                <textarea id="description" name="description" required></textarea>
            </div>
            <br>
            <button type="submit" class="button">Submit</button>

        </form>
    </section>

    <ul>
        <c:forEach items="${my_questions}" var="question">
            <c:set var="url" value="${pageContext.request.contextPath}/question?id=${question.getId()}" />
            <li>
                <%@include file="/views/profile/_question_model.jsp" %>
            </li>
        </c:forEach>
    </ul>
</div>
</main>

<%@include file="/views/_footer.jsp" %>
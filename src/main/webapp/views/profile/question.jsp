<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
    <ul>
        <li>
            <section class="question-card">
                <div class="container-for-question-info">
                    <h2 class="question_title">${question.getTitle()}</h2>
                    <p style="margin-top: 35px; opacity: 0.75;">category: ${question.getCategory().getName()}</p>
                </div>
                <p class="question_description">${question.getDescription()}</p>
                <p class="question_author"> author: ${question.getAuthor().getUsername()}</p>
            </section>
        </li>

        <section class="form-to-send-answer">
            <h2>Answer the question here</h2><br>
            <div class="error-message">
                <p>${error}</p>
            </div>
            <form action="question" method="POST">
                <div class="form-group">
                    <label for="answer">Add a answer:</label>
                    <br>
                    <input type="text" id="answer" name="answer" required>
                </div>
                <br>
                <input type="hidden" name="id" value="${question.getId()}">
                <button type="submit" class="button">Send</button>
            </form>
        </section>


        <c:set var="favorites_answers" value="${favorites_answers_for_user}" />

        <c:forEach items="${all_answers_for_this_question}" var="answer">
            <li>
                <%@include file="/views/profile/_answer_model.jsp" %>
            </li>
        </c:forEach>
    </ul>
</div>
</main>
</body>
</html>
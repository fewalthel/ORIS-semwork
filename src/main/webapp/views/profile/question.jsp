<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/views/profile/_header_for_profile.jsp" %>

<%@ page import="org.example.orissemwork.db.*" %>
<%@ page import="org.example.orissemwork.model.*" %>
<%@ page import="java.util.List" %>

<%--

<script>

    function updateRating(json_document, id_of_button) {
        let btn = document.getElementById(id_of_button);
        let mark = json_document[1]; //второй параметр это строка с true, false или null

        //удаляем дочерние элементы нашего button
        btn.innerHTML = '';

        const newImg = document.createElement('img');

        if (id_of_button.includes('thumbs-up')) {
            if (mark === 'null') {
                newImg.src = 'pics/thumbs-up.svg';
            }
            if (mark === 'true') {
                newImg.src = 'pics/thumbs-up-fill.svg';
            }
        }
        if (id_of_button.includes('thumbs-down')) {
            if (mark === 'null') {
                newImg.src = 'pics/thumbs-down.svg';
            }
            if (mark === 'false') {
                newImg.src = 'pics/thumbs-down-fill.svg';
            }
        }

        btn.appendChild(newImg);

    }

    function sendLikes(id_of_answer, rating, id_of_button) {

        let data = {
            "id_of_answer": id_of_answer, //number of id answer
            "rating": rating //'true', 'false' or 'null'
        };

        $.ajax({
            type: "POST",
            url: "/rating",
            data: JSON.stringify(data),
            success: function (response) {
                //запсукаем функцию перерисовки картинки
                updateRating(response, id_of_button)
            },
            dataType: "json",
            contentType: "application/json"
        });

    }
</script>
--%>

<div id="container-for-content">
    <ul>
        <li>
            <%--<% Question question = QuestionDAO.getById(Integer.parseInt(request.getParameter("id")));
                String id = request.getParameter("id");%>
--%>
            <section class="question-card">
                <div style=" display: flex; justify-content: space-between;">
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

        <c:forEach items="${all_answers_for_this_question}" var="answer">
            <li>
                <%@include file="/views/profile/_answer_model.jsp" %>
            </li>
        </c:forEach>
    </ul>
</div>
</main>

<%@include file="/views/_footer.jsp" %>
<section class="answer-card">
    <%String url = "question?id=" + question.getId();%>

    <div style=" display: flex; justify-content: space-between;">
        <a class="question_title" href=${pageContext.request.contextPath}/<%=url%>>
        <h2><%= question.getTitle() %></h2>
        </a>
        <form action="favorites_answers" method="POST" class="favourite-form">
            <input type="hidden" value="<%= answer.getId() %>" name="id_of_answer">
            <%if (AnswerDAO.ansInFavForUser(answer, user)) { %>
                <button style="background-color: var(--dark-text-color); color: white; margin-top: 25px; margin-bottom: 25px;" type="submit" class="button fav-button">In your favorites</button>
            <%} else { %>
                <button style="background-color: var(--mint-color); margin-top: 25px; margin-bottom: 25px;" type="submit" class="button fav-button">Add to favorites</button>
            <%}%>
        </form>
    </div>

    <p class="answer_description"><%= answer.getContent() %></p>

    <div style=" display: flex; justify-content: space-between;">
        <p class="answer_author">author: <%= answer.getAuthor().getUsername() %></p>
        <%@include file="/views/profile/_rating_model.jsp" %>
    </div>
</section>
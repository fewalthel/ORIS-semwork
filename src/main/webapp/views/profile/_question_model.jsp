<section class="question-card">
    <div style=" display: flex; justify-content: space-between;">
        <a class="question_title" href=${pageContext.request.contextPath}/<%=url%>><h2><%= question.getTitle() %></h2></a>
        <p style="margin-top: 20px;">category: <%= question.getCategory().getName() %></p>
    </div>
    <p class="question_description"><%= question.getDescription() %></p>
    <p class="question_author">author: <%= question.getAuthor().getUsername() %></p>
</section>
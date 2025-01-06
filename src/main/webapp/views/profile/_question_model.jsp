<section class="question-card">
    <div class="container-for-question-title">
        <a class="question_title" href="${url}"><h2>${question.getTitle()}</h2></a>
        <p class="category">category: ${question.getCategory().getName()}</p>
    </div>
    <p class="question_description">${question.getDescription()}</p>
    <p class="question_author">author: ${question.getAuthor().getUsername()}</p>
</section>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
    <section id="about-user" class="statistics-card" style="display: flex; justify-content: space-between">
        <img src="${path_to_avatar}" alt="avatar" style="width: 15vw; border-radius: 50%; /*box-shadow: 0 0 #7dffd3; */filter: drop-shadow(0 0 15px #7dffd3); margin-left: 2vw;">
        <div style="margin-right: 2vw; margin-top: 2vw;">
            <h2>My username: ${username}</h2>
            <h2>My email: ${email}</h2>
        </div>
    </section>

    <section id="questions" class="statistics-card">
        <h2>Questions asked</h2>
        <p>Total: <span>${total_questions}</span></p>
    </section>

    <section id="answers" class="statistics-card">
        <h2>Answers given</h2>
        <p>Total: <span>${total_answers}</span></p>
    </section>

</div>
</main>

<%@include file="/views/_footer.jsp" %>
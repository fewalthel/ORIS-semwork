<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
    <section id="about-user" class="statistics-card">
        <img class="avatar"
             src="${pageContext.request.contextPath}/avatar?id_user=${id}"
             alt="avatar">
        <div class="container-for-username-email">
            <h2>My username: ${username}</h2>
            <h2>My email: ${email}</h2>
        </div>
    </section>

    <section  class="statistics-card">
        <h2>Questions asked</h2>
        <p>Total: ${total_questions}</p>
        <br>
        <h2>Answers given</h2>
        <p>Total: ${total_answers}</p>
    </section>
</div>
</main>
</body>
</html>
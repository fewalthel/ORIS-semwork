<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/headers/_header_for_profile.jsp" %>

<main>
    <div id="container-for-content">
        <section id="questions" class="statistics-card">
            <h2>Questions</h2>
            <p>Total: <span>123</span></p>
        </section>
        <section id="answers" class="statistics-card">
            <h2>Answers</h2>
            <p>Total: <span>45</span></p>
        </section>
        <section id="statistics" class="statistics-card">
            <h2>Statistics</h2>
            <ul>
                <li><strong>Questions asked:</strong> <span>30</span></li>
                <li><strong>Answers given:</strong> <span>20</span></li>
            </ul>
        </section>
    </div>
</main>
<%@include file="/views/footers/_footer.jsp" %>
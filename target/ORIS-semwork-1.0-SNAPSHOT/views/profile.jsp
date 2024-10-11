<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/headers/_header_for_authorized_user.jsp" %>


<main class="content">
    <div id="container-for-statistics">
        <section id="all-questions" class="statistics-card">
            <h2>Questions</h2>
            <p>Total: <span>123</span></p>
        </section>
        <section id="my-questions" class="statistics-card">
            <h2>Answers</h2>
            <p>Total: <span>45</span></p>
        </section>
        <section id="settings" class="statistics-card">
            <h2>Statistics</h2>
            <ul>
                <li><strong>Questions asked:</strong> <span>30</span></li>
                <li><strong>Answers given:</strong> <span>20</span></li>
            </ul>
        </section>
    </div>
</main>
</body>
</html>
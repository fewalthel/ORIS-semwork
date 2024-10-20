<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
<section class="form-to-send-question">
    <h2>Ask your question to other peers here</h2><br>
    <div class="error-message">
        <p>${error}</p>
    </div>
    <form action="<c:url value="my_questions"/>" method="POST">
        <div class="form-group">
            <label for="title">Title:</label><br>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="form-group"><br>
            <label for="description">Description:</label><br>
            <textarea id="description" name="description" required></textarea>
        </div><br>
        <button type="submit" id="ask-question">Submit</button>
    </form>
</section>
</div>

<%@include file="/views/_footer.jsp" %>
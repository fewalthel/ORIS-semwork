<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
    <section class="form-to-settings">
        <h2>Settings</h2>
        <h4>Here you can change your username or password</h4>
        <div class="error-message">
            <p>${error}</p>
        </div>
        <form action="<c:url value="settings"/>" method="POST">
            <div class="form-group"><br>
                <label for="change_password">Change password</label>
                <input type="text" name="old_password" id="change_password" placeholder="old password">
                <br>
                <input type="text" name="new_password" id="repeat_password" placeholder="new password">
            </div><br>
            <div class="form-group"><br>
                <label for="change_username">Change username</label>
                <input type="text" name ="new_username" id="change_username" placeholder="new username">
                <br>
            </div><br>
            <button type="submit" id="apply">Apply changes</button>
        </form>
    </section>
</div>
<%@include file="/views/_footer.jsp" %>
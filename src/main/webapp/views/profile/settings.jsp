<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
    <section class="form-to-settings">
        <h2>Settings</h2>
        <h4>Here you can change your username or password</h4>
        <div class="error-message">
            <p>${error}</p>
        </div>
        <form action="settings" method="POST">
            <div class="form-group"><br>
                <label for="password">Change password</label>
                <input type="password" name="old_password" id="password" placeholder="old password">
                <br>
                <input type="password" name="new_password" id="confirm-password" placeholder="new password">
                <button type="button" class="toggle-password"><span>show password</span></button>
            </div>
            <br>
            <div class="form-group"><br>
                <label for="change_username">Change username</label>
                <input type="text" name="new_username" id="change_username" placeholder="new username">
                <br>
            </div>
            <br>
            <button type="submit" class="button">Apply changes</button>
        </form>
    </section>
    <section class="form-to-settings">
        <h4>Here you can delete your account if you want</h4>
        <div class="error-message">
            <p>${error}</p>
        </div>
        <form action="settings" method="POST">
            <div class="form-group"><br>
                <label for="current_username">Delete account</label>
                <input type="text" name="current_username" id="current_username" placeholder="username" required>
                <input type="password" name="current_password" id="current_password" placeholder="password" required>
            </div>
            <br>
            <button type="submit" class="button">Confirm and delete</button>
        </form>
    </section>
</div>
</main>
<script src="js/hide_password_func.js"></script>
<%@include file="/views/_footer.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<div id="container-for-content">
    <section class="form-to-settings">
    <h2>Settings</h2>
    <h4>Here you can change your urename or password</h4>
    <form action="<c:url value="settings"/>" method="POST">
        <label for="change_password">Change password</label>
        <input type="text" name="change_password" id="change_password">
        <label for="repeat_password">Repeat password</label>
        <input type="text" name="repeat_password" id="repeat_password">
        <br>
        <label for="change_username">Change username</label>
        <input type="text" name ="change_username" id="change_username">
        <button type="submit" id="apply">Apply changes</button>
    </form>
    </section>
</div>
<%@include file="/views/_footer.jsp" %>
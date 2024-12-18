<section class="user-card" style="height: 24vw;">
    <h2 class="user_username" style="display: flex; justify-content: space-between; padding-bottom: 2vw;">username: <%=userList.getUsername()%>
        <% if (userList.getEmail().equals(user.getEmail())) {%>
            <p>your account</p>
        <%}%>

        <% if (userList.getRole().equals("default")) {%>
        <form action="all_users" method="post">
            <input type="hidden" name="deleted_username" value="<%=userList.getUsername()%>">
            <button type="submit" class="button" style="margin-left: 20vw;">Delete user</button>
        </form>
        <form action="all_users" method="post">
            <input type="hidden" name="upgraded_username" value="<%=userList.getUsername()%>">
            <button type="submit" class="button">Upgrade to admin</button>
        </form>
        <%}%>
    </h2>
    <p class="user_email" style="font-size: 1.5vw; height: 3vw; padding-bottom: 1vw; padding-left: 1vw; ">email: <%=userList.getEmail()%></p>
    <p class="user_role" style="font-size: 1.5vw; height: 3vw; padding-bottom: 1vw; padding-left: 1vw;">role: <%=userList.getRole()%></p>
    <p class="user_questions" style="font-size: 1.5vw; height: 3vw; padding-bottom: 1vw; padding-left: 1vw;">questions asked: <%=QuestionDAO.getAllByAuthor(userList).size()%></p>
    <p class="user_answers" style="font-size: 1.5vw; height: 3vw; padding-bottom: 1vw; padding-left: 1vw;">answers given: <%=AnswerDAO.getAllByAuthor(userList).size()%></p>
</section>
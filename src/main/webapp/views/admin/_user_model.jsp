<section class="user-card">
    <h2 class="container-for-user-info">
        <img class="avatar"
             src="${pageContext.request.contextPath}/avatar?id_user=${userList.getId()}"
             alt="avatar">

        <div class="container-for-user-data">
            <h4 class="container-for-user-info">
                <c:if test="${userList.getEmail().equals(user.getEmail())}">
                    <p class="your-account">your account</p>
                </c:if>

                <c:if test="${userList.getRole().equals('default')}">
                    <form action="admin_menu" method="post">
                        <input type="hidden" name="deleted_username" value="${userList.getUsername()}">
                        <button type="submit" class="button">Delete user</button>
                    </form>
                    <form action="admin_menu" method="post">
                        <input type="hidden" name="upgraded_username" value="${userList.getUsername()}">
                        <button type="submit" class="button">Upgrade to admin</button>
                    </form>
                </c:if>
            </h4>
            <p class="user_username"> username: ${userList.getUsername()} </p><br>
            <div class="container-for-qa">
                <p class="user_email">email: ${userList.getEmail()}</p>
                <p class="user_role">role: ${userList.getRole()}</p>
            </div>
        </div>

    </h2>
    <%--
<p class="user_questions" style="font-size: 1.5vw; height: 3vw; padding-bottom: 1vw; padding-left: 1vw;"&ndash;%&gt;>
    questions asked: <%=QuestionDAO.getAllByAuthor(userList).size()%></p>
<p class="user_answers" style="font-size: 1.5vw; height: 3vw; padding-bottom: 1vw; padding-left: 1vw;"&ndash;%&gt;>
    answers given: <%=AnswerDAO.getAllByAuthor(userList).size()%></p>--%>
</section>
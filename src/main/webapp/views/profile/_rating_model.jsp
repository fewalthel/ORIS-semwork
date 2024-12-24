<%@ page import="org.example.orissemwork.db.RatingDAO" %>

<div class="container-for-marks" id="container<%= answer.getId() %>">
  <%--  <form action="rating" method="post">
    <input type="hidden" name="id_of_answer" value="<%= answer.getId() %>">
--%>
    <% if (RatingDAO.getByIdOfUser(user, answer) == null) {%>

        <button onclick="sendLikes(<%= answer.getId() %>, 'true', 'thumbs-up<%= answer.getId() %>')" id="thumbs-up<%= answer.getId() %>" <%--type="submit" name="rating" value="true" --%>><img class="mark-img" style="width: 35px;" src="pics/thumbs-up.svg" alt="thumbs up"><br>
            <%= RatingDAO.getAllByAnswer(answer, true).size()%></button>
        <button onclick="sendLikes(<%= answer.getId() %>, 'false')" id="thumbs-down<%= answer.getId() %>" <%--type="submit" name="rating" value="false"--%>><img class="mark-img" style="width: 35px;" src="pics/thumbs-down.svg" alt="thumbs down"><br>
            <%= RatingDAO.getAllByAnswer(answer, false).size()%></button>

    <%} if (RatingDAO.getByIdOfUser(user, answer) != null && RatingDAO.getByIdOfUser(user, answer).getLiked()) {%>

        <button onclick="sendLikes(<%= answer.getId() %>, 'null')" id="thumbs-up<%= answer.getId() %>" <%--type="submit" name="rating" value="null"--%>><img class="mark-img" style="width: 35px;" src="pics/thumbs-up-fill.svg" alt="thumbs up"><br>
            <%= RatingDAO.getAllByAnswer(answer, true).size()%></button>
        <button onclick="sendLikes(<%= answer.getId() %>, 'false')" id="thumbs-down<%= answer.getId() %>" <%-- type="submit" name="rating" value="false"--%>><img class="mark-img" style="width: 35px;" src="pics/thumbs-down.svg" alt="thumbs down"><br>
            <%= RatingDAO.getAllByAnswer(answer, false).size()%></button>

    <%} if (RatingDAO.getByIdOfUser(user, answer) != null && !RatingDAO.getByIdOfUser(user, answer).getLiked()) {%>

        <button onclick="sendLikes(<%= answer.getId() %>, 'true')"  id="thumbs-up<%= answer.getId() %>" <%--type="submit" name="rating" value="true"--%>><img class="mark-img" style="width: 35px;" src="pics/thumbs-up.svg" alt="thumbs up"><br>
            <%= RatingDAO.getAllByAnswer(answer, true).size()%></button>
        <button onclick="sendLikes(<%= answer.getId() %>, 'null')" id="thumbs-down<%= answer.getId() %>" <%--type="submit" name="rating" value="null"--%>><img class="mark-img" style="width: 35px;" src="pics/thumbs-down-fill.svg" alt="thumbs down"><br>
            <%= RatingDAO.getAllByAnswer(answer, false).size()%></button>

    <%}%>
    <%--</form>--%>
</div>

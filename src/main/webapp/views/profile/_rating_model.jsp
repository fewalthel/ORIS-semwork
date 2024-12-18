<%@ page import="org.example.orissemwork.db.RatingDAO" %>

<div class="container-for-marks">
    <form action="rating" method="post">
    <input type="hidden" name="idOfAnswer" value="<%= answer.getId() %>">

    <% if (RatingDAO.getByIdOfUser(user, answer).getLiked() == null) {%>
        <button type="submit" name="rating" value="true"><img class="mark-img" style="width: 35px;" src="pics/thumbs-up.svg" alt="thumbs up"></button>
        <button type="submit" name="rating" value="false"><img class="mark-img" style="width: 35px;" src="pics/thumbs-down.svg" alt="thumbs up"></button>
    <%} if (RatingDAO.getByIdOfUser(user, answer).getLiked()) {%>
        <button type="submit" name="rating" value="null"><img class="mark-img" style="width: 35px;" src="pics/thumbs-up-fill.svg" alt="thumbs up"></button>
        <button type="submit" name="rating" value="false"><img class="mark-img" style="width: 35px;" src="pics/thumbs-down.svg" alt="thumbs up"></button>
    <%} if (!RatingDAO.getByIdOfUser(user, answer).getLiked()) {%>
        <button type="submit" name="rating" value="true"><img class="mark-img" style="width: 35px;" src="pics/thumbs-up.svg" alt="thumbs up"></button>
        <button type="submit" name="rating" value="null"><img class="mark-img" style="width: 35px;" src="pics/thumbs-down-fill.svg" alt="thumbs up"></button>
    <%}%>
    </form>
</div>
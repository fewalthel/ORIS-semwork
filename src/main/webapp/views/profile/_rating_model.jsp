<%@ page import="org.example.orissemwork.dao.RatingDAO" %>
<%@ page import="org.example.orissemwork.model.Answer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-for-marks">
    <form action="rating" method="post">
        <input type="hidden" name="id_of_answer" value="${answer.getId()}">

        <c:forEach items="${ratings_for_user}" var="rating">

            <c:if test="${rating == null}">
                <button type="submit" name="rating" value="true">
                    <img class="mark-img" src="pics/thumbs-up.svg" alt="thumbs up"><br>
                </button>
                <button type="submit" name="rating" value="false">
                    <img class="mark-img" src="pics/thumbs-down.svg" alt="thumbs down"><br>
                </button>
            </c:if>

            <c:if test="${rating != null && rating.getLiked()}">
                <button type="submit" name="rating" value="null">
                    <img class="mark-img" src="pics/thumbs-up-fill.svg" alt="thumbs up"><br>
                </button>
                <button type="submit" name="rating" value="false">
                    <img class="mark-img" src="pics/thumbs-down.svg" alt="thumbs down"><br>
                </button>
            </c:if>

            <c:if test="${rating != null && !rating.getLiked()}">
                <button type="submit" name="rating" value="true">
                    <img class="mark-img" src="pics/thumbs-up.svg" alt="thumbs up"><br>
                </button>
                <button type="submit" name="rating" value="null">
                    <img class="mark-img" src="pics/thumbs-down-fill.svg" alt="thumbs down"><br>
                </button>
            </c:if>

            <% RatingDAO ratingDAO = (RatingDAO) request.getAttribute("ratingDAO");
               Answer answerObject = (Answer) pageContext.getAttribute("answer");

               Integer likes = ratingDAO.getAllByAnswer((answerObject), true).size();
               Integer dislikes = ratingDAO.getAllByAnswer((answerObject), false).size();%>
            <br>
            <div class="container-for-count-marks">
                <p><%=likes%></p>
                <p><%=dislikes%></p>
            </div>

        </c:forEach>
    </form>
</div>
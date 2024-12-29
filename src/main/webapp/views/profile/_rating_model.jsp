<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-for-marks" >
    <form action="rating" method="post">
    <input type="hidden" name="id_of_answer" value="${answer.getId()}">

    <c:forEach items="${ratings_for_user}" var="rating">
        <c:if test="${rating.getAnswer().equals(answer)}">

            <c:if test="${empty rating}">
                <button type="submit" name="rating" value="true"><img class="mark-img" style="width: 35px;" src="pics/thumbs-up.svg" alt="thumbs up"><br>
                <%--<%= RatingDAO.getAllByAnswer(answer, true).size()%>--%></button>
                <button type="submit" name="rating" value="true"><img class="mark-img" style="width: 35px;" src="pics/thumbs-down.svg" alt="thumbs down"><br>
                <%--<%= RatingDAO.getAllByAnswer(answer, false).size()%>--%></button>
            </c:if>

            <c:if test="${not empty rating && rating.getLiked()}">
                <button type="submit" name="rating" value="null"><img class="mark-img" style="width: 35px;" src="pics/thumbs-up-fill.svg" alt="thumbs up"><br>
                <%--<%= RatingDAO.getAllByAnswer(answer, true).size()%>--%></button>
                <button type="submit" name="rating" value="false"><img class="mark-img" style="width: 35px;" src="pics/thumbs-down.svg" alt="thumbs down"><br>
                <%--<%= RatingDAO.getAllByAnswer(answer, false).size()%>--%></button>
            </c:if>

            <c:if test="${not empty rating && not rating.getLiked()}">
                <button type="submit" name="rating" value="true"><img class="mark-img" style="width: 35px;" src="pics/thumbs-up.svg" alt="thumbs up"><br>
                <%--<%= RatingDAO.getAllByAnswer(answer, true).size()%>--%></button>
                <button type="submit" name="rating" value="null"><img class="mark-img" style="width: 35px;" src="pics/thumbs-down-fill.svg" alt="thumbs down"><br>
                <%--<%= RatingDAO.getAllByAnswer(answer, false).size()%>--%></button>
            </c:if>

        </c:if>
    </c:forEach>

    </form>
</div>

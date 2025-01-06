<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/views/profile/_header_for_profile.jsp" %>

<script>
    function renderTable(categories, table) {
        let innerHtml = '<tr>\n' +
            '               <th>id</th>' +
            '               <th>name</th>' +
            '            </tr>';

        for (let i = 0; i < categories.length; i++) {
            innerHtml += '<tr>';
            innerHtml += '  <td>' + categories[i]['id'] + '</td>';
            innerHtml += '  <td>' + categories[i]['name'] + '</td>';
            innerHtml += '</tr>';
        }

        table.html(innerHtml);
    }

    function sendCategory(category_id, category_name) {
        let data = {
            "id": category_id,
            "name": category_name
        };

        $.ajax({
            type: "POST",
            url: "/all_categories",
            data: JSON.stringify(data),
            success: function (response) {
                renderTable(response, $('#table'))
            },
            dataType: "json",
            contentType: "application/json"
        });
    }
</script>

<div id="container-for-content">
    <%@include file="/views/admin/_header_for_admin_menu.jsp" %>
    <table id="table">
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>

        <c:forEach items="${all_categories}" var="category">
            <%@include file="/views/admin/_category_model.jsp" %>
        </c:forEach>

        <tr>
            <td><input type="number" placeholder="id" id="category_id"></td>
            <td><input type="text" placeholder="name" id="category_name"></td>
            <td>
                <button class="button"
                        onclick="sendCategory( $('#category_id').val(), $('#category_name').val())">Add new category
                </button>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
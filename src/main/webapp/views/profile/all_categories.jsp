<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/views/profile/_header_for_profile.jsp" %>


<script>
    // принимает пользователей в формате JSON и таблицу, которую нужно заполнить
    function renderTable(categories, table) {
        let innerHtml = '<tr>\n' +
            '               <th>ID</th>' +
            '               <th>Name</th>' +
            '            </tr>';

        for (let i = 0; i < categories.length; i++) {
            innerHtml += '<tr>';
            innerHtml += '  <td>' + categories[i]['id'] + '</td>';
            innerHtml += '  <td>' + categories[i]['name'] + '</td>';
            innerHtml += '</tr>';
        }

        innerHtml += '<tr><td><input type="number" placeholder="id" id="category_id"></td>' +
           ' <td><input type="text" placeholder="name" id="category_name"></td>' +
            '<td><button class="button" onclick="sendCategory($('#category_id').val(), $('#category_name').val())">Add new category</button>' +'</td></tr>' ;

        table.html(innerHtml);
    }

    function sendCategory(category_id, category_name) {
        let data = {
            "id": category_id,
            "name": category_name
        };

        $.ajax({
            type: "POST", // метод запроса
            url: "/all_categories", //урл запроса
            data: JSON.stringify(data), // данные для отправки из JSON-объекта превращаем в JSON-строку
            // что происходит, если запрос прошел успешнго?
            success: function (response) {
                // рисуем таблицу на основе ответа на запрос
                renderTable(response, $('#table'))
            },
            //тип данных, который мы отпралвяем
            dataType: "json",
            contentType: "application/json"
        });
    }
</script>

<div id="container-for-content">
    <%@include file="/views/profile/_header_for_admin_menu.jsp" %>
    <table id="table">
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>

        <c:forEach items="${all_categories}" var="category">
            <%@include file="/views/profile/_category_model.jsp" %>
        </c:forEach>

        <tr>
            <td><input type="number" placeholder="id" id="category_id"></td>
            <td><input type="text" placeholder="name" id="category_name"></td>
            <td>
                <button class="button"
                    onclick="sendCategory( $('#category_id').val(), $('#category_name').val())">Add new category</button>
            </td>
        </tr>
    </table>
</div>
<%@include file="/views/_footer.jsp" %>
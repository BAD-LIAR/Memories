<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/styles/add_memorie.css">
</head>
<body>

<form method="post">
    <div style="height: 400px">
        <div class="add_body">
            <input type="hidden" id="csrftoken" name="${_csrf.parameterName}" value="${_csrf.token}">
            <textarea id="tittle" name="tittle" style="width: 170px;height: 100px;border-radius: 8px;">${memorie.text}</textarea>
            <br>
            <button class="sent" style="width: 100px; height: 40px">Сохранить</button>
        </div>

        <div>
            <table class="my_table" id="my_table">
                <th>email</th>
                <th>name</th>
                <th></th>
                <#list users as memories>
                    <tr id="tr${memories.id}">
                        <td class="eml" contenteditable="true">${memories.email}</td>
                        <td contenteditable="true">${memories.firstName} ${memories.lastName}</td>
                        <td><img class="edit" src="/styles/img/delete.jpg" onclick="$('#tr${memories.id}').remove();"></td>
                    </tr>
                </#list>
                <tr id="inpt_div">
                    <td colspan="4">
                        <input id="inpt">
                        <button class="find_and_add">Add</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <br>
    <a href="/profile">К воспомнинаниям</a>
</form>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    $(document).ready(function () {


        $("body").on("click", ".find_and_add", function (e) {
            e.preventDefault();


            var myData = ({
                'email': $('#inpt').val(),
                '_csrf': $('#csrftoken').val()
            });

            jQuery.ajax({
                type: "POST",
                url: "add_user_to_memorie",
                dataType: "text",
                data: myData,
                success: function (response) {
                    $('#not_found').remove();
                    if (response != 'not_found') {
                        $('#inpt_div').remove();
                        $('#my_table tr:last').after(response);
                        $('#my_table tr:last').after("<tr id=\"inpt_div\">\n" +
                            "                    <td colspan=\"4\">\n" +
                            "                    <input id=\"inpt\">\n" +
                            "                    <button class=\"find_and_add\">Add</button>\n" +
                            "                    </td>\n" +
                            "                </tr>");
                    } else {
                        $('#my_table tr:last').after(response);
                    }

                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                }

            });
        });


        $("body").on("click", ".sent", function (e) {
            e.preventDefault();


            var row = [];

            $(".eml").each(function () {
                var z = $(this);
                row.push($(this).text());
            });

            // var array = rows.map(function (i, x) { return ({
            //     email: x.cells[1].textContent
            // }); }).toArray();


            var myData = {
                '_csrf': $('#csrftoken').val(),
                "tittle": $("#tittle").val(),
                "row": JSON.stringify(row)
            };

            jQuery.ajax({
                type: "POST",
                url: "${url}",
                dataType: "text",
                data: myData,
                success: function (response) {
                    window.location.href = "profile";
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                }

            });
        });

        function foo(a) {

        };
    });
</script>
</html>

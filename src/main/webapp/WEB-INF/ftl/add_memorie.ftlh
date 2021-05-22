<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- fotorama.css & fotorama.js. -->
    <link href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
    <link rel="stylesheet" href="/styles/add_memorie.css">
</head>
<body>

<form method="post" enctype="multipart/form-data">
    <div style="height: 400px">
        <div class="add_body">
            <input type="hidden" id="csrftoken" name="${_csrf.parameterName}" value="${_csrf.token}">
            <textarea id="tittle" name="tittle"
                      style="width: 170px;height: 100px;border-radius: 8px;">${memorie.text}</textarea>
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
                        <td><img class="edit" src="/styles/img/delete.jpg" onclick="$('#tr${memories.id}').remove();">
                        </td>
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

    <input type="file" id="files" name="files[]"  multiple />
    <div class="fotorama" id="list" style="border: 10px black;">
        <#list photos as photo>
            <img src="${photo}"
        </#list>
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
                "row": JSON.stringify(row),
                "files": $('#files').val()
            };

            var formData = new FormData();
            formData.append('_csrf', $('#csrftoken').val());
            formData.append('tittle', $("#tittle").val());
            formData.append('row', JSON.stringify(row));
            formData.append('files', $('#files').val());

            jQuery.ajax({
                type: "POST",
                processData: false,  // Important!
                contentType: false,
                cache: false,
                enctype: "multipart/form-data",
                url: "${url}",
                dataType: "text",
                data: formData,
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

    // var control = document.getElementById("files");
    // control.addEventListener("change", function(event) {
    //     // Когда происходит изменение элементов управления, значит появились новые файлы
    //     var i = 0,
    //         files = control.files,
    //         len = files.length;
    //     for (var i = 0, f; f = files[i]; i++) {
    //         if (!f.type.match('image.*')) continue;
    //         var fr = new FileReader();
    //         fr.onload = (function(theFile) {
    //             return function(e) {
    //                 var li = document.createElement('');
    //                 li.innerHTML = "<img src='" + e.target.result + "' />";
    //             };
    //         })(f);
    //
    //         fr.readAsDataURL(f);
    //     }
    //
    //
    // }, false);
    // function showFile(e) {
    //     var files = e.target.files;
    //     for (var i = 0, f; f = files[i]; i++) {
    //         if (!f.type.match('image.*')) continue;
    //         var fr = new FileReader();
    //         fr.onload = (function(theFile) {
    //             return function(e) {
    //                 var li = document.createElement('');
    //                 li.innerHTML = "<img src='" + e.target.result + "' />";
    //             };
    //         })(f);
    //
    //         fr.readAsDataURL(f);
    //         $('#my-fotorama').fotorama();
    //     }
    //
    // }
</script>
</html>

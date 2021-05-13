<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Document</title>
</head>




<body>
<div>
    <table>
        <input type="hidden" id="csrftoken" name="${_csrf.parameterName}" value="${_csrf.token}">
        <tr>
            <th>id</th>
            <th>email</th>
        </tr>

        <#list usersList as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td id="state-${user.id}">${user.state}</td>
                <#if user.state == 'ACTIVE'>
                    <td><a href="#" id="ban-${user.id}" class="ban">ban</a></td>
                    <td><a href="#" id="unban-${user.id}" class="unban" hidden>unban</a></td>
                <#else>
                    <td><a href="#" id="ban-${user.id}" class="ban" hidden>ban</a></td>
                    <td><a href="#" id="unban-${user.id}" class="unban">unban</a></td>
                </#if>
            </tr>
        </#list>
    </table>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    $(document).ready(function() {


        $("body").on("click", ".ban", function(e) {
            e.preventDefault();



            var clickedID = this.id.split("-");
            var DbNumberID = clickedID[1];
            var myData = ({'recordToDelete': DbNumberID,
                    '_csrf': $('#csrftoken').val()});

            jQuery.ajax({
                type: "POST",
                url: "ban",
                dataType:"text",
                data: myData,
                success:function(response){
                    $('#ban-'+DbNumberID).hide();
                    $('#state-'+DbNumberID).text('BANNED');
                    $('#unban-'+DbNumberID).show();
                },
                error:function (xhr, ajaxOptions, thrownError){
                    alert(thrownError);
                }

            });
        });


        $("body").on("click", ".unban", function(e) {
            e.preventDefault();
            var clickedID = this.id.split("-");
            var DbNumberID = clickedID[1];
            var myData = ({'recordToDelete': DbNumberID,
                '_csrf': $('#csrftoken').val()});

            jQuery.ajax({
                type: "POST",
                url: "unban",
                dataType:"text",
                data: myData,
                success:function(response){
                    $('#ban-'+DbNumberID).show();
                    $('#state-'+DbNumberID).text('ACTIVE');
                    $('#unban-'+DbNumberID).hide();
                },
                error:function (xhr, ajaxOptions, thrownError){
                    alert(thrownError);
                }
            });
        });
    });

</script>
</html>

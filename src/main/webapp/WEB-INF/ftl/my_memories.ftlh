<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/styles/memories.css">
    <title>Document</title>
</head>
<body>
<div class="exit" onclick="location.href='/logout'">Выйти</div>
<div class="profile" onclick="location.href='/profile'">Профиль</div>
<br>
<div style="margin-right: 100px">
    <#list memoriesList as memorie>
        <div class="memories">
            <a>ID: ${memorie.id}</a>
            <img class="edit" src="/styles/img/edit.png" onclick="location.href='/edit?id=${memorie.id}'">
            <img class="edit" src="/styles/img/delete.jpg" onclick="location.href='/delete?id=${memorie.id}'">
            <br>
            <a>TEXT: ${memorie.text}</a>
        </div>
    </#list>
</div>


</body>
</html>

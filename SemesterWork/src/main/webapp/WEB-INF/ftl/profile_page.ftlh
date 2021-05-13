<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/styles/profile.css">
    <title>Document</title>
</head>
<body>
<div class="tab">
    <h1>PROFILE</h1>
    <div>
        <a class="exit" href="/logout">Выйти</a>
        <a class="email">${user.username}</a>
    </div>
</div>

<div class="my_blocks" onclick="location.href='/my_memories'">
    <a>Мои воспомнинания</a>
<#--    <br>-->
<#--    <#list memoriesList as memorie>-->
<#--        <tr>-->
<#--            <td>${memorie.id}</td>-->
<#--            <td>${memorie.text}</td>-->
<#--        </tr>-->
<#--        <br>-->
<#--    </#list>-->
</div>

<div class="my_blocks"onclick="location.href='/together'">
    <a>Совместные воспоминания</a>
</div>
<div class="my_blocks" onclick="location.href='/new_memorie'">
    <a>Добавить воспамнинание</a>
</div>


</body>
</html>

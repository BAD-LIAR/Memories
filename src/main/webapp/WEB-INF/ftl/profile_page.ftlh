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
<div class="exit" onclick="location.href='/logout'">Выйти</div>
<div class="tab">
    <h1>PROFILE</h1>
    <img class="settings" src="/styles/img/settings.png" onclick="location.href='/settings'">
    <br>
    <img class="avatar" src="/styles/img/avatars/profile_photo.jpg">
    <br>
    <a class="email">${user.firstName} ${user.lastName} </a>
    <br>
    <br>
    <a class="profile_text">Воспоминаний: ${user.memoriesCount}</a>
    <br>
    <br>
    <a class="profile_text">Совместных воспоминаний: ${user.togetherMemoriesCount}</a>
</div>


<div class="blocks">
    <div class="my_blocks" onclick="location.href='/my_memories'">
        <a class="my_text">Мои воспомнинания</a>
        <#--    <br>-->
        <#--    <#list memoriesList as memorie>-->
        <#--        <tr>-->
        <#--            <td>${memorie.id}</td>-->
        <#--            <td>${memorie.text}</td>-->
        <#--        </tr>-->
        <#--        <br>-->
        <#--    </#list>-->
    </div>

    <div class="my_blocks" onclick="location.href='/together'">
        <a class="my_text">Совместные воспоминания</a>
    </div>
    <div class="my_blocks" onclick="location.href='/new_memorie'">
        <a class="my_text">Добавить воспамнинание</a>
    </div>

</div>


</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/styles/settings.css">
</head>
<body>
<div class="exit" onclick="location.href='/profile'">Профиль</div>
<div class="tab">
    <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <h1>PROFILE</h1>
        <br>
        <label for="file">
            <div class="avatar" style="background-image: url('${user.avatar}');
             background-position: center, center;background-size: cover;margin-top: 20px">
                <img class="avatar_h" src="/styles/img/settings.png">
            </div>
        </label>
        <br>
        <input class="email" name="firstName" value="${user.firstName}">
        <br>
        <br>
        <input class="email" name="lastName" value="${user.lastName}">
        <#--        <br>-->
        <#--        <br>-->
        <#--        <input class="email" name="email" value="${user.email}">-->
        <input type="file" name="file" id="file" class="inputfile">
        <br>
        <button class="submit" type="reset">Reset</button>
        <button class="submit" type="submit">Подтвердить</button>
    </form>

</div>
</body>
</html>

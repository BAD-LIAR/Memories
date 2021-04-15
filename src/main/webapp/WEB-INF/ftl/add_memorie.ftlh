<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <@spring.formTextarea "memorieForm.text"/>
    <br>
    <button type="submit" style="width: 100px; height: 40px">Сохранить</button>
    <br>
    <a href="/profile">К воспомнинаниям</a>
</form>

</body>
</html>

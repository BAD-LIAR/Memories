<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div class="memories">
    <a>ID: ${memorie.id}</a>
    <br>
    <a>TEXT: ${memorie.text}</a>
</div>

<form method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input hidden value="${memorie.id}">
    <a>Вы точно хотите удалить воспоминание?</a>
    <button type="submit">Удалить</button>
</form>

</body>
</html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Вход</title>
</head>
<body>
<form action="/login" method="post">
    <input type="text" name="login" placeholder="Введите логин"><br>
    <input type="password" name="password" placeholder="Введите пароль"><br>
    <input type="submit">
</form>
<#if error??>
    <p>Неверный логин или пароль</p>
</#if>
</body>
</html>
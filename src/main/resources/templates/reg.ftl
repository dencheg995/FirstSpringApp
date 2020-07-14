<#import "/spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация</title>
</head>
<body>
<@spring.bind "user"/>
<form action="/reg" method="post">
    Придумайте логин: <br>
    <@spring.formInput  path="user.login"/><br>
    <@spring.showErrors "<br>"/><br>
    Введите email: <br>
    <@spring.formInput path="user.email"/><br>
    <@spring.showErrors "<br>"/><br>
    Придумайте пароль: <br>
    <@spring.formPasswordInput path="user.password"/><br>
    <@spring.showErrors "<br>"/><br>
    Введите пароль повторно: <br>
    <@spring.formPasswordInput path="user.confirmPassword"/><br>
    <input type="submit" value="Зарегистрироваться">
</form>
<#if noErrors ??>
    <p>Вы успешно зарегистрировались!</p>
    <p><a href="/login">Войти</a></p>
</#if>

</body>
</html>
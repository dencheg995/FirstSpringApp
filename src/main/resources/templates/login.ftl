<#import "parts/common.ftl" as structure>

<@structure.page>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="inputLogin">Логин:</label>
            <input type="text" class="form-control col-6" id="inputLogin" name="login">
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" class="form-control col-6" id="password" name="password">
        </div>
        <button type="submit" class="btn btn-primary col-2">Войти</button>
    </form>
    <#if error??>
        <p style="color:red;">Неверный логин или пароль</p>
    </#if>
    <p><a href="/reg">Нет аккаунта?</a></p>
</@structure.page>
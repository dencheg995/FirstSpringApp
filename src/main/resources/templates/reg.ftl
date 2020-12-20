<#import "/spring.ftl" as spring />
<#import "parts/common.ftl" as structure>
<@structure.page>
    <@spring.bind "user"/>
    <form action="/reg" method="post">
        <label>Придумайте логин: </label><br>
        <@spring.formInput path="user.login"/><br>
        <@spring.showErrors "<br>"/><br>
        <label>Введите email: </label><br>
        <@spring.formInput path="user.email"/><br>
        <@spring.showErrors "<br>"/><br>
        <label>Придумайте пароль:</label><br>
        <@spring.formPasswordInput path="user.password"/><br>
        <@spring.showErrors "<br>"/><br>
        <label>Введите пароль повторно:</label><br>
        <@spring.formPasswordInput path="user.confirmPassword"/><br>
        <button type="submit" class="btn btn-primary col-3 mt-2">Зарегистрироваться</button>
    </form>
    <#if noErrors ??>
        <p>Вы успешно зарегистрировались!</p>
        <p><a href="/login">Войти</a></p>
    </#if>
    <script>
        let inputs = document.getElementsByTagName("input");
        for (let input of inputs) {
            input.classList.add("form-control");
            input.classList.add("col-6");
        }
        let errors = document.getElementsByTagName("b");
        for (let error of errors){
            error.style.color = "red";
        }
    </script>
</@structure.page>
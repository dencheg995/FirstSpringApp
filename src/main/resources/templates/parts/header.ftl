<#include "security.ftl">
<nav class="navbar navbar-inverse sticky-top navbar-expand-lg navbar-dark bg-dark text-white">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Stories</a>
        <ul class="nav navbar-nav">
            <li class="nav-item" id="home">
                <a class="nav-link" href="/">Главная <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item" id="stories">
                <a class="nav-link" href="/stories">Все рассказы</a>
            </li>
            <li class="nav-item" id="best">
                <a class="nav-link" href="/best">Рейтинг рассказов</a>
            </li>
            <li class="nav-item" id="publish">
                <a class="nav-link" href="/publish">Опубликовать рассказ</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <#if known>
                <li class="nav-text mr-3 mt-2" style="color:white">
                    ${name}
                </li>
                <li><a class="btn btn-primary" href="/logout" role="button">Выйти</a></li>
            <#else>
                <li class="mr-2"><a href="/reg" class="btn btn-primary" role="button">Регистрация</a></li>
                <li><a href="/login" class="btn btn-primary" role="button">Войти</a></li>
            </#if>
        </ul>
    </div>
</nav>
<#import "pager.ftl" as p>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Истории</title>
</head>
<body>
<div class="search-form-div">
    <form action="/stories/filter" method="get">
        <label for="author">Поиск по автору</label><br>
        <input type="text" name="author" placeholder="Никнейм автора" autocomplete="off"><br>
        <label for="title">Поиск по названию</label><br>
        <input type="text" name="title" placeholder="Введите название рассказа" autocomplete="off"><br>
        <label for="genre">Жанр</label><br>
        <select name="genre">
            <option value="" selected>-любой-</option>
            <#list genres as genre>
                <option value="${genre.id}">${genre.name}</option>
            </#list>
        </select><br>
        <input type="submit" value="Найти рассказы">
    </form>
</div>
<div class="stories-section">
    <@p.pager url page></@>
    <div class="stories-page">
        <#list page.content as story>
            <div id="story${story.id}">
                <h2>${story.title}</h2>
                <p>${story.shortDesc}</p>
                <div class="story-genre">
                    <span>Жанр: </span>
                    <#list story.genres as genre>
                        <span>${genre.name} </span>
                    </#list>
                </div>
                <p>Рейтинг: ${story.raiting}, Количество оценок: ${story.amountOfMarks}</p>
                <p>Автор: ${story.author.login}</p>
                <a href="/stories/${story.id}">Прочитать</a>
            </div><br>
        </#list>
    </div>
    <@p.pager url page></@>
</div>
</body>
</html>
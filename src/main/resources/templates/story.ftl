<#import "pager.ftl" as p>
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Истории</title>
</head>
<body>
<h2>${story.title}</h2>
<p>Автор: ${story.author.login}</p>
<div class="full-story">
    ${story.fullText}
</div>
<div class="mark-section">
    <div class="story-mark"></div>
    <div class="has-mark">Вы оценили данный рассказ: <#if mark ??>${mark}</#if></div>
    <div class="add-mark">
        <p>Оценить рассказ:</p>
        <form action = "/stories/${story.id}/setMark" method="post">
            <input type="number" name="mark" min="1" max="5" step="1" value="1">
            <input type="submit" value="Оценить">
        </form>
    </div>
</div>
<div class="comment-section">
    <div class="comment-add">
        <form action="/stories/${story.id}/comment" method="post">
            <input type="text" name="text" placeholder="Оставить комментарий">
            <input type="submit" value="Отправить">
        </form>
    </div>
    <div class="comment-list">
        <#list comments.content as comment>
            <div class="comment-wrap" style="border:1px solid black; margin: 3px">
                <h3>${comment.user.login}</h3>
                <p>${comment.text}</p>
                <p>${comment.date}</p>
                <p><a href="/stories/${story.id}/comment/delete/${comment.id}">Удалить</a></p>
            </div>
        </#list>
        <@p.pager url comments></@>
    </div>
</div>
</body>
</html>
<html lang="en">
<head>
    <title>Опубликовать историю</title>
</head>
<body>
<form action="/publish" method="post">
    <input type="text" name="title" placeholder="Введите название рассказа"><br>
    <#if errorTitle ??>
        <div class="error">${errorTitle}</div>
    </#if>
    <label>Выберите жанр:</label><br>
    <#list genres as genre>
        <label><input type="checkbox" name="genres" value="${genre.id}">${genre.name}</label><br>
    </#list>
    <#if errorGenre ??>
        <div class="error">${errorGenre}</div>
    </#if>
    <label>Кратко опишите Ваш рассказ:</label><br>
    <textarea name="shortDesc"></textarea> <br>
    <#if errorDesc ??>
        <div class="error">${errorDesc}</div>
    </#if>
    <label>Скопируйте Ваш рассказ в поле ниже:</label><br>
    <textarea name="fullText"></textarea>
    <#if errorStory ??>
        <div class="error">${errorStory}</div>
    </#if>
    <input type="submit" value="Опубликовать">
</form>
</body>
</html>
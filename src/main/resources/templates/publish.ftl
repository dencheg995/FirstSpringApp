<#import "parts/common.ftl" as structure>
<@structure.page>
    <form action="/publish" method="post">
        <label><b>Введите название рассказа:</b></label>
        <input class="form-control col-3" type="text" name="title">
        <#if errorTitle ??>
            <small class="form-text" style="color:red;">${errorTitle}</small>
        </#if>
        <label><b>Выберите жанр:</b></label><br>
        <#list genres as genre>
            <label><input type="checkbox" name="genres" value="${genre.id}"> ${genre.name}</label><br>
        </#list>
        <#if errorGenre ??>
            <small class="form-text" style="color:red;">${errorGenre}</small>
        </#if>
        <label><b>Кратко опишите Ваш рассказ:</b></label><br>
        <textarea class="form-control col-8" name="shortDesc"></textarea>
        <#if errorDesc ??>
            <small class="form-text" style="color:red;">${errorDesc}</small>
        </#if>
        <label><b>Скопируйте Ваш рассказ в поле ниже:</b></label><br>
        <textarea class="form-control col-12" style="height: 600px;" name="fullText"></textarea>
        <#if errorStory ??>
            <small class="form-text" style="color:red;">${errorStory}</small>
        </#if>
        <button type="submit" class="btn btn-primary mt-3">Опубликовать</button>
    </form>
</@structure.page>
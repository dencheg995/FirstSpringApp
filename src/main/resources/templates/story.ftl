<#import "parts/pager.ftl" as p>
<#import "parts/common.ftl" as structure>
<#include "parts/security.ftl">
<@structure.page>
    <h2 align="center">${story.title}</h2>
    <p><b>Автор: ${story.author.login}</b></p>
    <p>
        Жанр: <#list story.genres as genre>
            ${genre.getName()}<#if genre_has_next>, </#if>
        </#list>
    </p>
    <p>${story.publishDate}</p>
    <div class="full-story">
        ${story.fullText?replace("\r\n","<br>")}
    </div>
    <br>
    <h3>Рейтинг рассказа: ${story.rating}, количество оценок: ${story.amountOfMarks}</h3>
    <#if name!="unknown">
        <div class="mark-section" style="border-bottom: 1px solid gray;">
            <div class="story-mark"></div>
            <#if mark ??>
                <div class="has-mark">Вы оценили данный рассказ: ${mark}</div></#if>
            <div class="add-mark">
                <#if mark ??>
                    <p>Изменить оценку</p>
                <#else>
                    <p>Оценить рассказ:</p>
                </#if>
                <form action="/stories/${story.id}/setMark" method="post">
                    <input type="number" class="form-control" name="mark" min="1" max="5" step="1" value="1">
                    <button type="submit" class="btn btn-primary col-2 mt-2 mb-2">Оценить</button>
                </form>
            </div>
        </div>
    </#if>
    <br>
    <h3 style="border-bottom: 1px solid gray;">Комментарии: ${comments.totalElements}</h3>
    <div class="comment-section">
        <#if name!="unknown">
            <div class="comment-add" style="border-bottom: 1px solid gray;">
                <form action="/stories/${story.id}/comment" method="post">
                    <textarea name="text" class="form-control"></textarea>
                    <button type="submit" class="btn btn-primary col-4 mt-2 mb-2">Оставить комментарий</button>
                </form>
            </div>
        </#if>
        <div class="comment-list">
            <#list comments.content as comment>
                <div class="media mt-2" style="border-bottom: 1px solid gray;">
                    <div class="media-body">
                        <h5 class="mt-0">${comment.user.login}</h5>
                        <p>${comment.text?replace("\r\n","<br>")}</p>
                        <p class="float-right">${comment.date}</p>
                        <#if comment.user.login == name>
                            <p><a href="/stories/${story.id}/comment/delete/${comment.id}">Удалить</a></p>
                        </#if>
                    </div>
                </div>
            </#list>
            <#if comments.getTotalPages() != 0>
                <@p.pager url comments></@>
            </#if>
        </div>
    </div>
</@structure.page>
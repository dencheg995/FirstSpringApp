<#import "parts/pager.ftl" as p>
<#import "parts/common.ftl" as structure>
<@structure.page>
    <div class="col">
    <div class="stories-page">
        <#list bestStories.content as story>
            <div class="card">
                <h5 class="card-header">${story.title}</h5>
                <div class="card-body">
                    <p class="card-text pb-3" style="border-bottom: 1px solid gray;">${story.shortDesc}</p>
                    <div class="card-text">
                        Жанр:
                        <#list story.genres as genre>
                            <b>${genre.name}</b><#if genre_has_next>, </#if>
                        </#list>
                    </div>
                    <p class="card-text float-right">Рейтинг: <b>${story.rating}</b>, Количество оценок: <b>${story.amountOfMarks}</b></p>
                    <p class="card-text">Автор: <b>${story.author.login}</b></p>
                    <a href="/stories/${story.id}" class="btn btn-primary">Прочитать</a>
                </div>
            </div><br>
        </#list>
    </div>
    <@p.pager url bestStories></@>
</@structure.page>
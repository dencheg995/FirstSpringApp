<#import "parts/pager.ftl" as p>
<#import "parts/common.ftl" as structure>
<@structure.page>
    <div class="row py-3">
        <div class="col-3">
            <div class="sticky-top" style="top:100px;">
                <form action="/stories/filter" method="get">
                    <label for="author">Поиск по автору</label><br>
                    <input class="form-control" type="text" name="author" placeholder="Никнейм автора"
                           autocomplete="off"><br>
                    <label for="title">Поиск по названию</label><br>
                    <input class="form-control" type="text" name="title" placeholder="Введите название рассказа"
                           autocomplete="off"><br>
                    <label for="genre">Жанр</label><br>
                    <select class="form-control" name="genre">
                        <option value="" selected>-любой-</option>
                        <#list genres as genre>
                            <option value="${genre.id}">${genre.name}</option>
                        </#list>
                    </select><br>
                    <button type="submit" class="btn btn-primary">Найти рассказы</button>
                </form>
            </div>
        </div>
        <div class="col">
            <div class="stories-page">
                <#if page?? && page.totalElements gt 0>
                    <#list page.content as story>
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
                                <p class="card-text float-right">Рейтинг: <b>${story.rating}</b>, Количество оценок:
                                    <b>${story.amountOfMarks}</b></p>
                                <p class="card-text">Автор: <b>${story.author.login}</b></p>
                                <a href="/stories/${story.id}" class="btn btn-primary">Прочитать</a>
                            </div>
                        </div><br>
                    </#list>
                    <@p.pager url page></@>
                <#else>
                    <h2>По данным критериям рассказов не найдено</h2>
                </#if>
            </div>
        </div>
    </div>
</@structure.page>
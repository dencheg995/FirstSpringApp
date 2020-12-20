<#macro pager url page>
    <#if page.getTotalPages() gt 7>
        <#assign
        total = page.getTotalPages()
        pageNumber = page.getNumber() + 1

        head = (pageNumber > 4)?then([1,-1],[1,2,3])
        tail = (pageNumber < total - 3)?then([-1,total],[total-2,total-1,total])
        bodyBefore = (pageNumber > 4 && pageNumber < total-1)?then([pageNumber-2,pageNumber-1],[])
        bodyAfter = (pageNumber < total-3 && pageNumber > 2)?then([pageNumber+1,pageNumber+2],[])

        body = head + bodyBefore + (pageNumber > 3 && pageNumber < total -2)?then([pageNumber],[]) + bodyAfter+tail

        >
    <#else>
        <#assign body = 1..page.getTotalPages()>
    </#if>
    <nav aria-label="Search results pages">
        <ul class="pagination">
            <#if page.getNumber() gt 0>
                <li class="page-item"><a class="page-link" href="${url}?page=${page.getNumber()-1}">Предыдущая</a></li>
            <#else>
                <li class="page-item disabled"><a class="page-link" href="#">Предыдущая</a></li>
            </#if>
            <#list body as p>
                <#if p-1 == page.getNumber()>
                    <li class="page-item active">
                        <a class="page-link" href="#">${p}</a>
                    </li>
                <#elseif p == -1>
                    <li class="page-item disabled">
                        <a class="page-link" href="#">...</a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="${url}?page=${p-1}">${p}</a>
                    </li>
                </#if>
            </#list>
            <#if page.getNumber() lt page.getTotalPages()-1>
                <li class="page-item"><a class="page-link" href="${url}?page=${page.getNumber()+1}">Следующая</a></li>
            <#else>
                <li class="page-item disabled"><a class="page-link" href="#">Следующая</a></li>
            </#if>
        </ul>
    </nav>
</#macro>
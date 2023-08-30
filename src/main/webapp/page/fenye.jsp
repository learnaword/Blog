<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 构建分页导航 --%>
<div Style="text-align: center;">
    <ul class="pagination">
        <li><a href="https://www.bangmangma.com/${pageUrl}" rel="nofollow">首页</a></li>
        <c:if test="${pageInfo.hasPreviousPage}">
            <li><a href="/${pageUrl}?page=${pageInfo.prePage}" rel="nofollow">上一页</a></li>
        </c:if>
        <%--如果当前页为第一页时，就没有上一页这个超链接显示 --%>
        <c:forEach var="item" items="${pageInfo.navigatepageNums}">
            <c:if test="${item == pageInfo.pageNum}">
                <li><a class ="active" href="/${pageUrl}?page=${item}" rel="nofollow">${item}</a></li>
            </c:if>
            <c:if test="${pageInfo.pageNum != item}">
                <li><a href="/${pageUrl}?page=${item}" rel="nofollow">${item}</a></li>
            </c:if>
        </c:forEach>
        <%--如果当前页为第一页时并且是最后一页，就没有下一页这个超链接显示 --%>
        <c:if test="${pageInfo.hasNextPage}">
            <li><a href="/${pageUrl}?page=${pageInfo.nextPage}" rel="nofollow">下一页</a></li>
        </c:if>
    </ul>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--无-->
<c:choose>
    <c:when test="${blog.soft.id == 79}">
           <%@ include file="info/kuais.jsp"%>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>


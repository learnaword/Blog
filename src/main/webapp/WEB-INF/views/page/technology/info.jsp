<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt"  prefix="fmt"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>${blog.title}-NEW个对象</title>
    <meta name="keywords" content="${blog.keyword}" />
    <meta name="description" content="${blog.introduction}" />
    <link rel="shortcut icon" href="/images/favicon.ico">
    <meta http-equiv="Content-Language" content="zh-CN" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="applicable-device" content="pc,mobile">
    <meta http-equiv="Cache-Control" content="no-transform" />
    <link href="/static/css/page/info.min.css" rel="stylesheet">
    <script src="/static/js/common/jquery.min.js"></script>
    <script src="/static/js/common/comm.js"></script>
    <script src="/static/js/common/info.js"></script>
</head>
<body>
<%@ include file="../top.jsp"%>
<article>
    <div class="lbox">
       <div class="infos">
            <div class="newsview">
                <div class="intitle">
                    <a href="${baseUrl}">NEW个对象</a>&nbsp;&gt;&nbsp;<a href="${baseUrl}/technology/softDetail/${blog.soft.id}.html" rel="nofollow">${blog.soft.title}</a>&nbsp;&gt;&nbsp;当前页面
                        </div>
                        <h1 class="news_title">${blog.title}</h1>
                        <div>
                            <span class="m_time">${blog.createTime}</span>
                            <span style="margin-left:10px;"><i class="fa fa-user fa-fw"></i>NEW个对象</span>
                        </div>
                        <hr style="margin:5px 0px 5px 0px;"/>
                       <div class="news_infos">${blog.content}</div>
            </div>
         </div>
        <div style="margin:3px 0px 3px 0px;" class="nextinfo">
             <p>
                上一篇：<span class="prev"><c:if test="${prev != null}"><a href="${baseUrl}/technology/find/${prev.id}.html" rel="bookmark">${prev.title}</a></c:if><c:if test="${prev == null}">无</c:if></span>
            </p>
            <p>
                下一篇：<span class="next"><c:if test="${next != null}"> <a href="${baseUrl}/technology/find/${next.id}.html" rel="bookmark">${next.title}</a> </c:if> <c:if test="${next == null}"> 无</c:if></span>
            </p>
       </div>
        <!--无-->
    <div style="border-top:3px solid #eeeeee;" class="newblogs bloglist whitebg">
            <h2 class="htitle">相关文章</h2>
            <ul>
                <c:forEach var="item" items="${relBlogs}" end="2"  varStatus="status">
                    <li>
                        <h3 class="blogtitle">
                            <a style="color:#467ab2;"  href="${baseUrl}/technology/find/${item.id}.html" rel="bookmark">${item.title}</a>
                        </h3>
                        <p class="blogtext">
                                ${item.introduction}
                        </p>
                         <p class="bloginfo">
                            <span>NEW个对象</span>
                            <span style="margin-left:0px;" class="m_time">${item.createTime}</span>
                        </p>
                    </li>
                </c:forEach>
            </ul>
    </div>
</div>
<div class="rbox">
    <div style="background-color:white;padding:10px;" class="hitebg notice">
            <h3 class="htitle">推荐文章</h3>
            <ul>
                <c:forEach var="item" items="${topBlogs}" end="1"  varStatus="status">
                    <li><a href="${baseUrl}/technology/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div style="margin-top:10px;background-color:white;padding:10px;" class="hitebg notice">
            <h3 class="htitle">最新文章</h3>
            <ul>
                <c:forEach var="item" items="${newBlogs}" end="6" varStatus="status">
                    <li><a href="${baseUrl}/technology/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>

            </ul>
        </div>
    </div>
</article>
<%@ include file="../fonter.jsp"%>
</body>
</html>

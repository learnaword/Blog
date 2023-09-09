<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt"  prefix="fmt"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>${media.title}-帮忙么</title>
    <meta name="keywords" content="${media.keyword}" />
    <meta name="description" content="${media.introduction}" />
    <link rel="shortcut icon" href="/images/favicon.ico">
    <meta http-equiv="Content-Language" content="zh-CN" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="applicable-device" content="pc,mobile">
    <meta http-equiv="Cache-Control" content="no-transform" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="/static/css/page/media.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/1.8.3/jquery.min.js"></script>
    <script src="/static/js/comm.js"></script>
</head>
<body>
<%@ include file="../top.jsp"%>
<article>
    <div class="lbox">
        <div class="infos">
            <div class="newsview">
                <div class="intitle">
                    <a href="${baseUrl}">帮忙么</a>&nbsp;> <a href="/media/media.html" rel="nofollow">自媒体</a>&nbsp;&gt;&nbsp;当前页面
                </div>
                <h1 class="news_title">${media.title}</h1>
                <div>
                    <span class="m_time">${media.createTime}</span>
                    <span style="margin-left:10px;"><i class="fa fa-user fa-fw"></i>思想与现实</span>
                </div>
                <hr style="margin:5px 0px 5px 0px;"/>
                <div class="news_infos">${media.content}</div>
            </div>
        </div>
        <div style="margin:3px 0px 3px 0px;" class="nextinfo">
            <p>
                上一篇：<span class="prev"><c:if test="${prev != null}"><a href="${baseUrl}/media/find/${prev.id}.html" rel="bookmark">${prev.title}</a></c:if><c:if test="${prev == null}">无</c:if></span>
            </p>
            <p>
                下一篇：<span class="next"><c:if test="${next != null}"> <a href="${baseUrl}/media/find/${next.id}.html" rel="bookmark">${next.title}</a> </c:if> <c:if test="${next == null}"> 无</c:if></span>
            </p>
        </div>
        <div style="border-top:3px solid #eeeeee;" class="newblogs bloglist whitebg">
            <h2 class="htitle">相关文章</h2>
            <ul>
                <c:forEach var="item" items="${relMedias}" end="2"  varStatus="status">
                    <li>
                        <h3 class="blogtitle">
                            <a style="color:#467ab2;"  href="${baseUrl}/media/find/${item.id}.html" rel="bookmark">${item.title}</a>
                        </h3>
                        </span>
                        <p class="blogtext">
                                ${item.introduction}
                        </p>

                         <p class="bloginfo">
                            <i class = "avatar">
                                <img src="${baseUrl}/images/image_.jpg" border=0 width="30" height="30">
                            </i>
                            <span>思想与现实</span>
                             <span style="margin-left:10px;" class="m_time">${item.createTime}</span>
                         </p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="rbox">
<div class="mediaCard" style="background:#fff;">
    <span class="mediaRecSpan">思想与现实</span>
    <img style="height:50px;" src="${baseUrl}/images/image_.jpg"  alt="思想与现实">
    <div class="mediaRecDiv">
         <span class="mediaRecSpan2">嗯～真是个复杂的问题。</span>
   </div>
   <div class="mediaRecText">

   </div>
</div>
        <div style="margin-top:10px;background-color:white;padding:10px;" class="hitebg notice">
            <h3 class="htitle">推荐文章</h3>
            <ul>
                <c:forEach var="item" items="${topMedias}" end="6" varStatus="status">
                    <li><a href="${baseUrl}/media/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>

            </ul>
        </div>
        <div style="margin-top:10px;background-color:white;padding:10px;" class="hitebg notice">
            <h3 class="htitle">最新文章</h3>
            <ul>
                <c:forEach var="item" items="${newMedias}" end="6" varStatus="status">
                    <li><a href="${baseUrl}/media/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>

            </ul>
        </div>
    </div>
</article>
<%@ include file="../fonter.jsp"%>
</body>
</html>

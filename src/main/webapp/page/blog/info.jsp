<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt"  prefix="fmt"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>${blog.title}-帮忙么</title>
    <meta name="keywords" content="${blog.keyword}" />
    <meta name="description" content="${blog.introduction}" />
    <meta http-equiv="Content-Language" content="zh-CN" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="applicable-device" content="pc,mobile">
    <meta http-equiv="Cache-Control" content="no-transform" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="/static/css/info.css" rel="stylesheet">
    <link rel="shortcut icon" href="/static/images/favicon.ico">
    <link href="/static/css/infocommon.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/1.8.3/jquery.min.js"></script>
    <script src="/static/js/comm.js"></script>
</head>
<body>
<%@ include file="top.jsp"%>
 <input class="id" type="hidden" valdue="${blog.id}">
 <input class="softId" type="hidden" value="${blog.soft.id}">
<article>
    <div class="lbox">
       <div class="infos">
            <div class="newsview">
                <div class="intitle">
                    <a href="https://www.bangmangma.com/">帮忙么</a>&nbsp;&gt;&nbsp;<a href="https://www.bangmangma.com/softDetail/${blog.soft.id}.html" rel="nofollow">${blog.soft.title}</a>&nbsp;&gt;&nbsp;当前页面
                        </div>
                        <h1 class="news_title">${blog.title}</h1>
                        <div>
                           <span><i class="fa fa-clock-o fa-fw"></i><${item.createTime}</span>
			   <span style="margin-left:10px;"><i class="fa fa-user fa-fw"></i>帮忙么</span>
                        </div>
                        <hr style="margin:5px 0px 5px 0px;"/>
                       <div class="news_infos">${blog.content}</div>
            </div>
         </div>
        <div style="margin:3px 0px 3px 0px;" class="nextinfo">
             <p>
                上一篇：<span class="prev"><c:if test="${prev != null}"><a href="https://www.bangmangma.com/find/${prev.id}.html" rel="bookmark">${prev.title}</a></c:if><c:if test="${prev == null}">无</c:if></span>
            </p>
            <p>
                下一篇：<span class="next"><c:if test="${next != null}"> <a href="https://www.bangmangma.com/find/${next.id}.html" rel="bookmark">${next.title}</a> </c:if> <c:if test="${next == null}"> 无</c:if></span>
            </p>
       </div>
       <%@ include file="recommend/footBottom.jsp"%>
      <div style="border-top:3px solid #eeeeee;" class="newblogs bloglist whitebg">
            <h2 class="htitle">相关文章</h2>
            <ul>
                <c:forEach var="item" items="${relBlogList}" end="2"  varStatus="status">
                    <li>
                        <h3 class="blogtitle">
                            <a style="color:#467ab2;"  href="https://www.bangmangma.com${item.soft.token}/find/${item.id}.html" rel="bookmark">${item.title}</a>
                        </h3>
                        <span class="blogpic imgscale">
                            <a href="https://www.bangmangma.com${item.soft.token}/find/${item.id}.html">
                                <img src="${item.images}"/>
                            </a>
                        </span>
                        <p class="blogtext">
                                ${item.introduction}
                        </p>
                         <p class="bloginfo">
                            <i class = "avatar">
                                <img src="/static/images/image_.jpg" border=0 width="30" height="30">
                            </i>
                            <span>帮忙么</span>
                            <span style="margin-left:10px;" class="m_time"><fmt:formatDate value="${Date(item.createTime )}" pattern="yyyy-MM-dd"/></span>
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
                <c:forEach var="item" items="${blogRecommendList}" end="2"  varStatus="status">
                    <li><a href="https://www.bangmangma.com${item.soft.token}/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>
                <c:forEach var="item" items="${hotBlogList}" end="2"  varStatus="status">
                    <li><a href="https://www.bangmangma.com${item.soft.token}/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>

            </ul>
        </div>
        <div style="margin-top:10px;background-color:white;padding:10px;" class="hitebg notice">
            <h3 class="htitle">最新文章</h3>
            <ul>
                <c:forEach var="item" items="${newBlogList}" end="5" varStatus="status">
                    <li><a href="https://www.bangmangma.com${item.soft.token}/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>

            </ul>
        </div>
    </div>
    <a href="#" class="top cd-top animated ">Top</a>
</article>
<%@ include file="fonter.jsp"%>
</body>
</html>
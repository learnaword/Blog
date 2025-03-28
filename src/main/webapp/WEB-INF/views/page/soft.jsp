<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>经验分享</title>
    <meta name="keywords" content="手机兼职,手机赚钱软件,免费赚钱" />
    <meta name="description" content="手机赚钱软件专区:在这里有大量的手机免费赚钱赚钱软件，软件专区功能，在这里你将充分了解这个软件，如何赚钱，能赚到多少，有什么优点和缺点等等，尽我最大可能让你找到适合自己的方式。" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon"href="/images/favicon.ico">
    <link href="/static/css/page/soft.min.css" rel="stylesheet">
    <script src="/static/js/common/jquery.min.js"></script>
    <script src="/static/js/common/comm.js"></script>
</head>
<body>
<%@ include file="top.jsp"%>
<article>
    <div class="lbox">
        <div class="fenlei">
            <div class="item">
                <a href="${baseUrl}/softInfo/77.html"><img src="${baseUrl}/images/soft_task.PNG" alt="做任务"/>
                    <span class="caption">做任务</span></a>
            </div>
            <div class="item">
                <a href="${baseUrl}/softInfo/79.html"><img src="${baseUrl}/images/soft_news.PNG" alt="看新闻"/>
                    <span class="caption">看新闻</span></a>
            </div>
            <div class="item">
                <a href="${baseUrl}/softInfo/78.html"><img src="${baseUrl}/images/soft_vedio.PNG" alt="看视频"/>
                    <span class="caption">看视频</span></a>
            </div>
            <div class="item">
                <a href="${baseUrl}/softInfo/80.html"><img src="${baseUrl}/images/soft_taobaoke.PNG" alt="淘宝客"/>
                    <span class="caption">淘宝客</span></a>
            </div>
        </div>
        <div class="fenlei">
            <div class="item">
                <a href="${baseUrl}/softInfo/82.html"><img src="${baseUrl}/images/soft_game.PNG" alt="玩游戏"/>
                    <span class="caption">玩游戏</span></a>
            </div>
            <div class="item">
                <a href="${baseUrl}/softInfo/84.html"><img src="${baseUrl}/images/soft_wap.PNG" alt="建网站"/>
                    <span class="caption">建网站</span></a>
            </div>
            <div class="item">
                <a href="${baseUrl}/softInfo/83.html"><img src="${baseUrl}/images/soft_media.PNG" alt="自媒体"/>
                    <span class="caption">自媒体</span></a>
            </div>
            <div class="item">
                <a href="${baseUrl}/softInfo/85.html"><img src="${baseUrl}/images/soft_douyin.PNG" alt="玩抖音"/>
                    <span class="caption">短视频</span></a>
            </div>
        </div>
        <div class="newblogs bloglist whitebg">
            <ul>
                <c:forEach var="item" items="${softList}" varStatus="status">
                    <li>
                        <h3 class="blogtitle">
                            <a style="color:#467ab2;"  href="${baseUrl}/softDetail/${item.id}/1.html">${item.title}</a>
                        </h3>
                        <span class="blogpic">
                             <a href="${baseUrl}/softDetail/${item.id}/1.html">
                                 <img src="${baseUrl}${item.images}"/>
                             </a>
                         </span>
                        <p class="blogtext">${item.introduction}</p>
                    </li>
                </c:forEach>
            </ul>
            <%@ include file="fenye.jsp"%>
        </div>
    </div>
    <div class="rbox">
        <%@ include file="recommend/rec/soft.jsp"%>
        <div style="margin-top:10px;" class="cloud whitebg">
            <h2 class="cloud_hometitle">软件类型</h2>
            <ul>
                <a href="${baseUrl}/softInfo/79.html">看新闻</a>
                <a href="${baseUrl}/softInfo/78.html">看视频</a>
                <a href="${baseUrl}/softInfo/77.html">做任务</a>
                <a href="${baseUrl}/softInfo/85.html">短视频</a>
                <a href="${baseUrl}/softInfo/86.html">试玩赚</a>
                <a href="${baseUrl}/softInfo/83.html">自媒体</a>
                <a href="${baseUrl}/softInfo/84.html">建网站</a>
                <a href="${baseUrl}/softInfo/88.html">看小说</a>
                <a href="${baseUrl}/softInfo/82.html">玩游戏</a>
                <a href="${baseUrl}/softInfo/80.html">淘宝客</a>
            </ul>
        </div>
    </div>
</article>
<%@ include file="fonter.jsp"%>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt"  prefix="fmt"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>${softInfo.title}</title>
    <meta name="keywords" content="${softInfo.title}" />
    <meta name="description" content="${softInfo.introduction}" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon"href="https://www.bangmangma.com/images/favicon.ico">
    <link href="https://www.bangmangma.com/css/style.css" rel="stylesheet">
    <link href="https://www.bangmangma.com/css/loaders.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://www.bangmangma.com/css/common.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/1.8.3/jquery.min.js"></script>
    <link href="https://www.bangmangma.com/css/soft.css" rel="stylesheet">
    <script src="https://www.bangmangma.com/js/comm.js"></script>
</head>
<body>
<%@ include file="top.jsp"%>
<article>
    <div class="lbox">
              <c:if test="${softId != 83 && softId != 84}">
        <div class="fenlei">
            <div class="item">
                <a href="https://www.bangmangma.com/softDetail/${softId}/1.html"><img src="https://www.bangmangma.com/upload/blog/2020-10-30683715.PNG" alt="软件介绍"/>
                    <span class="caption">专区介绍</span></a>
            </div>
            <div class="item">
                <a href="https://www.bangmangma.com/softDetail/${softId}/2.html"><img src="https://www.bangmangma.com/upload/blog/2020-10-29136485.PNG" alt="使用经验"/>
                    <span class="caption">使用经验</span></a>
            </div>
            <div class="item">
                <a href="https://www.bangmangma.com/softDetail/${softId}/3.html"><img src="https://www.bangmangma.com/upload/blog/2020-10-29539107.PNG" alt="疑难解答"/>
                    <span class="caption">疑难解答</span></a>
            </div>
            <div class="item">
                <a href="https://www.bangmangma.com/softDetail/${softId}.html"><img src="https://www.bangmangma.com/upload/blog/2020-10-3079698.PNG" alt="全部"/>
                    <span class="caption">全部</span></a>
            </div>
        </div>
</c:if>
        <div class="newblogs bloglist">
            <ul>
                <c:forEach var="item" items="${blogList}" varStatus="status">
                    <li>
                        <h3 style="font-weight:bold;" class="blogtitle">
                            <a href="https://www.bangmangma.com${item.soft.token}/find/${item.id}.html">
                             ${item.title}
                            </a>
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
                    </li>
                </c:forEach>
            </ul>
            <%@ include file="fenye.jsp"%>
        </div>
    </div>
    <div class="rbox">
       <%@ include file="recommend/rec/soft.jsp"%>
        <div class="cloud whitebg">
            <h2 class="cloud_hometitle">软件类型</h2>
            <ul>
                <c:forEach var="item" items="${typeList}" varStatus="status">
                    <a href="/softInfo/${item.id}.html">${item.typename}</a>
                </c:forEach>
            </ul>
        </div>
    </div>
    <a href="#" class="top cd-top animated ">Top</a>
</article>
<%@ include file="fonter.jsp"%>
<script src="/static/js/layer/layer.js"></script>
</body>
</html>

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
    <link href="https://www.bangmangma.com/css/mediacommon.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/1.8.3/jquery.min.js"></script>
    <link href="https://www.bangmangma.com/css/media.css" rel="stylesheet">
    <script src="https://www.bangmangma.com/js/comm.js"></script>
</head>
<body>
<%@ include file="../top.jsp"%>
<article>
    <div class="lbox">
        <div class="newblogs bloglist">
            <ul>
                <c:forEach var="item" items="${blogList}" varStatus="status">
                    <li>
                        <h3 style="font-weight:bold;" class="blogtitle">
                            <a style="color:#467ab2;" href="https://www.bangmangma.com${item.soft.token}/find/${item.id}.html">
                                    ${item.title}
                            </a>
                        </h3>
                        <p class="text-container blogtext">
                                ${item.introduction}
                        </p>
                        <p class="bloginfo">
                            <i class = "avatar">
                                <img src="/static/images/image_.jpg" border=0 width="30" height="30">
                            </i>
                            <span>思想与现实</span>
                            <span style="margin-left:10px;" class="m_time"><fmt:formatDate value="${Date(item.createTime )}" pattern="yyyy-MM-dd"/></span>
                    </li>
                </c:forEach>
            </ul>
            <%@ include file="../fenye.jsp"%>
        </div>
    </div>
  <div class="rbox">
<div class="mediaCard" style="background:#fff;">
    <span class="mediaRecSpan">思想与现实</span>
    <img style="height:50px;" src="https://www.bangmangma.com/images/image_.jpg"  alt="思想与现实">
    <div class="mediaRecDiv">
         <span class="mediaRecSpan2">嗯～真是个复杂的问题。</span>
   </div>
   <div class="mediaRecText">

   </div>
</div>
        <div style="margin-top:10px;background-color:white;padding:10px;" class="hitebg notice">
            <h3 class="htitle">推荐文章</h3>
            <ul>
                <c:forEach var="item" items="${blogRecommendList}" end="6" varStatus="status">
                    <li><a href="https://www.bangmangma.com${item.soft.token}/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>

            </ul>
        </div>
        <div style="margin-top:10px;background-color:white;padding:10px;" class="hitebg notice">
            <h3 class="htitle">最新文章</h3>
            <ul>
                <c:forEach var="item" items="${blogNewRecommendList}" end="6" varStatus="status">
                    <li><a href="https://www.bangmangma.com${item.soft.token}/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>

            </ul>
        </div>
</div>
    <a href="#" class="top cd-top animated ">Top</a>
</article>
<%@ include file="../fonter.jsp"%>
<script src="/static/js/layer/layer.js"></script>
</body>
</html>

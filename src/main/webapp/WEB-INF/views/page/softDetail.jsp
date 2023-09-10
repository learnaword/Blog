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
    <link rel="shortcut icon"href="/images/favicon.ico">
    <script src="https://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>
    <link href="/static/css/page/soft.min.css" rel="stylesheet">
    <script src="/static/js/comm.js"></script>
</head>
<body>
<%@ include file="top.jsp"%>
<article>
    <div class="lbox">
              <c:if test="${softId != 83 && softId != 84}">
                  <div class="fenlei">
                      <div class="item">
                          <a href="${baseUrl}/softDetail/${softId}/1.html"><img src="https://www.bangmangma.com/images/softDetail_introduce.PNG" alt="软件介绍"/>
                              <span class="caption">专区介绍</span></a>
                      </div>
                      <div class="item">
                          <a href="${baseUrl}/softDetail/${softId}/2.html"><img src="https://www.bangmangma.com/images/softDetail_exp.PNG" alt="使用经验"/>
                              <span class="caption">使用经验</span></a>
                      </div>
                      <div class="item">
                          <a href="${baseUrl}/softDetail/${softId}/3.html"><img src="https://www.bangmangma.com/images/softDetail_questions.PNG" alt="疑难解答"/>
                              <span class="caption">疑难解答</span></a>
                      </div>
                      <div class="item">
                          <a href="${baseUrl}/softDetail/${softId}.html"><img src="https://www.bangmangma.com/images/softDetail_all.PNG" alt="全部"/>
                              <span class="caption">全部</span></a>
                      </div>
                  </div>
              </c:if>
        <div class="newblogs bloglist whitebg">
            <ul>
                <c:forEach var="item" items="${blogList}" varStatus="status">
                    <li>
                        <h3 style="font-weight:bold;" class="blogtitle">
                            <a href="${baseUrl}/find/${item.id}.html">
                             ${item.title}
                            </a>
                        </h3>
                        <span class="blogpic">
                            <a href="${baseUrl}/find/${item.id}.html">
                                <img src="${baseUrl}${item.images}"/>
                            </a>
                        </span>
                        <p class="blogtext">
                            ${item.introduction}
                        </p>
                        <p class="bloginfo">
                            <i class = "avatar">
                                <img src="${baseUrl}/images/image_.jpg" border=0 width="30" height="30">
                            </i>
                            <span>帮忙么</span>
                            <span style="margin-left:0px;" class="m_time">${item.createTime}</span>
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
                    <a href="${baseUrl}/softInfo/${item.id}.html">${item.title}</a>
                </c:forEach>
            </ul>
        </div>
    </div>
</article>
<%@ include file="fonter.jsp"%>
</body>
</html>

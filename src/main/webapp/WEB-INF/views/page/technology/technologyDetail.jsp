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
    <script src="/static/js/common/jquery.min.js"></script>
    <link href="/static/css/page/soft.min.css" rel="stylesheet">
    <link href="/static/css/page/media.min.css" rel="stylesheet">
    <script src="/static/js/common/comm.js"></script>
</head>
<body>
<%@ include file="../top.jsp"%>
<article>
    <div class="lbox">
              <c:if test="${softId != 83 && softId != 84}">
                  <div class="fenlei">
                      <div class="item">
                          <a href="${baseUrl}/technology/softDetail/${softId}/1.html"><img src="https://www.bangmangma.com/images/softDetail_introduce.PNG" alt="软件介绍"/>
                              <span class="caption">理论</span></a>
                      </div>
                      <div class="item">
                          <a href="${baseUrl}/technology/softDetail/${softId}/2.html"><img src="https://www.bangmangma.com/images/softDetail_exp.PNG" alt="使用经验"/>
                              <span class="caption">经验</span></a>
                      </div>
                      <div class="item">
                          <a href="${baseUrl}/technology/softDetail/${softId}/3.html"><img src="https://www.bangmangma.com/images/softDetail_questions.PNG" alt="疑难解答"/>
                              <span class="caption">问题</span></a>
                      </div>
                      <div class="item">
                          <a href="${baseUrl}/technology/softDetail/${softId}.html"><img src="https://www.bangmangma.com/images/softDetail_all.PNG" alt="全部"/>
                              <span class="caption">全部</span></a>
                      </div>
                  </div>
              </c:if>
        <div class="newblogs bloglist whitebg">
            <ul>
                <c:forEach var="item" items="${blogList}" varStatus="status">
                    <li>
                        <h3 style="font-weight:bold;" class="blogtitle">
                            <a href="${baseUrl}/technology/find/${item.id}.html">
                             ${item.title}
                            </a>
                        </h3>
                        <c:if test="${item.images != ''}">
                        <span class="blogpic">
                            <a href="${baseUrl}/technology/find/${item.id}.html">
                                <img src="${baseUrl}${item.images}"/>
                            </a>
                        </span>
                        </c:if>
                        <p class="blogtext">
                            ${item.introduction}
                        </p>
                        <p class="bloginfo">
                            <span>NEW个对象</span>
                            <span style="margin-left:0px;" class="m_time">${item.createTime}</span>
                    </li>
                </c:forEach>
            </ul>
            <%@ include file="../fenye.jsp"%>
        </div>
    </div>
    <div class="rbox">
        <div class="mediaCard" style="background:#fff;">
            <span class="mediaRecSpan">NEW个对象</span>
            <img style="height:50px;" src="${baseUrl}/images/touxiang.jpg"  alt="NEW个对象">
            <div class="mediaRecDiv">
                <span class="mediaRecSpan2">JAVA是世界上最好的语言</span>
            </div>
            <div class="mediaRecText">
            </div>
        </div>
        <div style="margin-top:10px;background-color:white;padding:10px;" class="whitebg notice">
            <h3 class="htitle">推荐文章</h3>
            <ul>
                <c:forEach var="item" items="${topBlogs}" end="6" varStatus="status">
                    <li><a href="${baseUrl}/technology/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div style="margin-top:10px;background-color:white;padding:10px;" class="whitebg notice">
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

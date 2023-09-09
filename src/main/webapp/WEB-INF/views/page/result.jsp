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
  <link href="https://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
  <script src="https://cdn.staticfile.org/jquery/1.8.3/jquery.min.js"></script>
  <link href="/static/css/page/soft.min.css" rel="stylesheet">
  <script src="/static/js/comm.js"></script>
</head>
<body>
<%@ include file="top.jsp"%>
<article>
  <div class="lbox">
    <div class="newblogs bloglist">
      <ul>
        <c:forEach var="item" items="${result}" varStatus="status">
          <li>
            <h3 style="font-weight:bold;" class="blogtitle">
              <a href="<%= basePath %>/find/${item.id}.html">
                  ${item.title}
              </a>
            </h3>
            <span class="blogpic">
                            <a href="/find/${item.id}.html">
                                <img src="<%= basePath %>${item.images}"/>
                            </a>
                        </span>
            <p class="blogtext">
                ${item.introduction}
            </p>
            <p class="bloginfo">
              <i class = "avatar">
                <img src="<%= basePath %>/images/image_.jpg" border=0 width="30" height="30">
              </i>
              <span>帮忙么</span>
              <span style="margin-left:10px;" class="m_time">${item.createTime}</span>
          </li>
        </c:forEach>
      </ul>
      <%@ include file="fenye.jsp"%>
    </div>
  </div>
  <div class="rbox">
    <%@ include file="recommend/rec/soft.jsp"%>
  </div>
</article>
<%@ include file="fonter.jsp"%>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
    <title>技术专区</title>
    <meta name="keywords" content="技术专区" />
    <meta name="description" content="JAVA是世界上最好的语言" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon"href="/images/favicon.ico">
    <script src="https://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>
    <link href="/static/css/page/soft.min.css" rel="stylesheet">
    <link href="/static/css/page/media.min.css" rel="stylesheet">
    <script src="/static/js/common/comm.js"></script>
</head>
<body>
  <%@ include file="../top.jsp"%>
  <article>
      <div class="lbox">
          <div class="fenlei">
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/97.html"><img src="${baseUrl}/images/java.png" alt="java"/>
                      <span class="caption">Java</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/89.html"><img src="${baseUrl}/images/spring.png" alt="Spring"/>
                      <span class="caption">Spring</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/90.html"><img src="${baseUrl}/images/mybatis.jpeg" alt="Mybatis"/>
                      <span class="caption">Mybatis</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/92.html"><img src="${baseUrl}/images/mysql.png" alt="MySQL"/>
                      <span class="caption">MySQL</span></a>
              </div>
          </div>
          <div class="fenlei">
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/91.html"><img src="${baseUrl}/images/redis.png" alt="Redis"/>
                      <span class="caption">Redis</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/99.html"><img src="${baseUrl}/images/RabbitMQ.png" alt="RabbitMQ"/>
                      <span class="caption">RabbitMQ</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/98.html"><img src="${baseUrl}/images/kafka.jpeg" alt="Kafka"/>
                      <span class="caption">Kafka</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/100.html"><img src="${baseUrl}/images/dubbo.png" alt="Dubbo"/>
                      <span class="caption">Dubbo</span></a>
              </div>
          </div>
          <div class="fenlei">
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/93.html"><img src="${baseUrl}/images/linux.png" alt="Linux"/>
                      <span class="caption">Linux</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/95.html"><img src="${baseUrl}/images/tomcat.png" alt="Tomcat"/>
                      <span class="caption">Tomcat</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/94.html"><img src="${baseUrl}/images/nginx.jpeg" alt="Nginx"/>
                      <span class="caption">Nginx</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/96.html"><img src="${baseUrl}/images/jenkins.png" alt="Jenkins"/>
                      <span class="caption">Jenkins</span></a>
              </div>
          </div>
          <div class="fenlei">
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/101.html"><img src="${baseUrl}/images/git.png" alt="Git"/>
                      <span class="caption">Git</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/104.html"><img src="${baseUrl}/images/docker.png" alt="docker"/>
                      <span class="caption">Docker</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/102.html"><img src="${baseUrl}/images/swagger.png" alt="Nginx"/>
                      <span class="caption">Swagger</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/109.html"><img src="${baseUrl}/images/suanfa.png" alt="算法"/>
                      <span class="caption">算法</span></a>
              </div>
          </div>
          <div class="fenlei">
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/106.html"><img src="${baseUrl}/images/springcloud.png" alt="Spring Cloud"/>
                      <span class="caption">Spring Cloud</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/108.html"><img src="${baseUrl}/images/weifuwu.jpeg" alt="微服务"/>
                      <span class="caption">微服务</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/107.html"><img src="${baseUrl}/images/fenbushi.png" alt="分布式"/>
                      <span class="caption">分布式</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/105.html"><img src="${baseUrl}/images/fenkufenbiao.png" alt="分库分表"/>
                      <span class="caption">分库分表</span></a>
              </div>
          </div>
          <div class="fenlei">
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/103.html"><img src="${baseUrl}/images/jiagou.jpeg" alt="Jenkins"/>
                      <span class="caption">架构</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/110.html"><img src="${baseUrl}/images/xiangmu.png" alt="项目"/>
                      <span class="caption">项目</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/111.html"><img src="${baseUrl}/images/sixiang.jpeg" alt="编程思想"/>
                      <span class="caption">编程思想</span></a>
              </div>
              <div class="item">
                  <a href="${baseUrl}/technology/softInfo/112.html"><img src="${baseUrl}/images/fenkufenbiao.png" alt="分库分表"/>
                      <span class="caption">其它</span></a>
              </div>
          </div>
          <div class="newblogs bloglist whitebg">
              <ul>
                  <c:forEach var="item" items="${softList}" varStatus="status">
                      <li>
                          <h3 class="blogtitle">
                              <a style="color:#467ab2;"  href="${baseUrl}/technology/softDetail/${item.id}.html">${item.title}</a>
                          </h3>
                          <span class="blogpic">
                             <a href="${baseUrl}/technology/softDetail/${item.id}.html">
                                 <img src="${baseUrl}${item.images}"/>
                             </a>
                         </span>
                          <p class="blogtext">${item.introduction}</p>
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

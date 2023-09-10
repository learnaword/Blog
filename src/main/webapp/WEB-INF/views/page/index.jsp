<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt"  prefix="fmt"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>网上创业,分享个人生活经验,人生感悟-帮忙么</title>
    <meta name="keywords" content="帮忙么网,网上创业,生活经验"/>
    <meta name="description" content="帮忙么网，网站主要是为了分享站长的个人的生活经验，网上创业，人生感悟，解决生活中遇到的小问题，分享个人的一些生活经验。"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Language" content="zh-CN" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="/images/favicon.ico">
    <link href="/static/css/page/index.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>
    <script src="/static/js/comm.js"></script>
</head>
<body>
<%@ include file="top.jsp" %>
<article>
    <div class="lbox">
       <div class="banbox">
            <div class="banner">
                <div id="banner" class="fader">
                  <li class="slide">
                      <a href="${baseUrl}/softDetail/84.html" target="_blank">
                          <img src="${baseUrl}/images/indexBig1.JPEG">
                      </a>
                  </li>
                   <li class="slide">
                       <a href="https://a.jrpub.cn/979855" target="_blank">
                           <img src="${baseUrl}/images/indexBig2.jpeg" alt="帮忙么网">
                       </a>
                   </li>
                    <div class="fader_controls">
                        <div class="pic_page prev" data-target="prev"></div>
                        <div class="pic_page next" data-target="next"></div>
                        <ul class="pager_list">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="headline">
            <ul>
                <li><a href="${baseUrl}/find/117258.html" title="帮忙么网">
                        <img src="${baseUrl}/images/indexSmall1.JPG" alt="帮忙么网">
                    </a>
                </li>
                <li><a href="${baseUrl}/softDetail/83.html" title="生活经验">
                        <img src="${baseUrl}/images/indexSmall2.jpg" alt="生活经验">
                        <span>生活经验分享</span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="newblogs bloglist whitebg">
             <h3 class="htitle">最新文章</h3>
             <ul>
                <c:forEach var="item" items="${blogNewList.getList()}" varStatus="status">
                    <li>
                        <h3 class="blogtitle">
                            <a style="color:#467ab2;" href="${baseUrl}/find/${item.id}.html" rel="bookmark">${item.title}</a>
                        </h3>
                       <c:if test="${item.images != ''}">
                        <span class="blogpic">
                            <a href="${baseUrl}/find/${item.id}.html">
                                <img src="${baseUrl}${item.images}"/>
                            </a>
                        </span>
                       </c:if>
                        <p class="blogtext">
                          ${item.introduction}
                        </p>
                        <p class="bloginfo">
                              <i class = "avatar">
                                    <img src="${baseUrl}/images/image_.jpg" border=0 width="30" height="30">
                                </i>
                                <span>帮忙么</span>
                                <span style="margin-left:0px;" class="m_time">${item.createTime}</span>
                            </i>
                        </p>
                    </li>
                </c:forEach>
            </ul>
            <%@ include file="fenye.jsp"%>
        </div>
</div>
    <div class="rbox">
        <div class="card">
            <h2>名片</h2>
            <p>网名：为</p>
            <p>职业：男程序员，Java研发工程师</p>
            <p>现居：上海</p>
            <p>Email：875657453@qq.com</p>
            <ul class="linkmore">
                <li><a href="" target="_blank"
                       class="iconfont icon-zhuye" title="网站地址"></a></li>
                <li><a
                        href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=875657453@qq.com"
                        target="_blank" class="iconfont icon-youxiang" title="我的邮箱" rel="nofollow"></a></li>
                <li><a
                        href="http://wpa.qq.com/msgrd?v=3&uin=875657453&site=qq&menu=yes"
                        target="_blank" class="iconfont icon---" title="QQ联系我" rel="nofollow"></a></li>
            </ul>
        </div>

        <div class="whitebg notice">
            <h2 class="htitle">热门推荐</h2>
            <ul>
                <c:forEach var="item" end="8"  items="${blogOrderList}">
                    <li><a href="${baseUrl}/find/${item.id}.html" title="${item.title}" rel="bookmark">${item.title}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</article>
<%@ include file="fonter.jsp"%>
<!-- 全局js -->
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "s://" + request.getServerName() + path;
%>
<header id="header" style="margin:0px;">
    <div class="navbox">
        <h2 id="mnavh">
            <span class="navicon"></span>
        </h2>
        <div class="logo">
            <a href=""><img class="imgTop" src="<%= basePath %>/images/logo.jpg"></a>
        </div>
        <nav class="leftNav">
            <ul id="starlist">
                <li><a href="<%= basePath %>/" title="首页" >首页</a></li>
                <li><a href="<%= basePath %>/soft.html" rel="nofollow">经验分享</a></li>
                <li><a href="<%= basePath %>/media/media.html" rel="nofollow">自媒体</a></li>
            </ul>
        </nav>
        <div class="searchico"></div>
    </div>
</header>
<div class="searchbox">
    <div class="search">
        <form action="/blog/result.html" method="get" accept-charset="UTF-8">
            <input name="keyboard" id="keyboard" class="input_text" placeholder="输入关键词" type="text">
            <input class="input_submit" type="submit">
        </form>
    </div>
    <div class="searchclose"></div>
</div>


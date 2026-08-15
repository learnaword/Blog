<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<header id="header" class="infoBar" style="margin:0px;">
    <div class="navbox">
        <div class="logo">
            帮忙么网
        </div>
        <nav class="leftNav">
            <ul id="starlist">
                <li><a href="${baseUrl}" title="首页" >首页</a></li>
                <li><a href="${baseUrl}/soft.html" rel="nofollow">经验分享</a></li>
                <li><a href="${baseUrl}/media/media.html" rel="nofollow">自媒体</a></li>
                <li><a href="${baseUrl}/technology/technology.html" rel="nofollow">技术专区</a></li>
            </ul>
        </nav>
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

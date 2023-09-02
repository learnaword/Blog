<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
  <title>帮忙么:一个网上创业,分享生活经验,人生感悟,手机赚钱的平台</title>
  <meta name="keywords" content="手机兼职,网上赚钱,网上兼职,手机挣钱"/>
  <meta name="description" content="帮忙么网，网站主要是为了分享站长的个人的生活经验，网上创业，人生感悟，解决生活中遇到的小问题，分享个人的一些生活经验。"/>
   <link rel="shortcut icon" href="/static/images/favicon.ico">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/static/css/style.css" rel="stylesheet">
   <link href="/static/css/animate.css" rel="stylesheet">
   <link href="/static/css/loaders.css" rel="stylesheet">
   <link href="https://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
   <link href="/static/css/common.min.css" rel="stylesheet">
   <script src="https://hm.baidu.com/hm.js?f655f558c510211e38805f6b586e6b15"></script>
   <script src="https://cdn.staticfile.org/jquery/1.8.3/jquery.min.js"></script>
   <script src="/static/js/comm.js"></script>
</head>
<style type="text/css">
   cursor: url('/images/ani/a.cur'), auto;}
   a {cursor: url('/images/ani/b.cur'), auto;}
   .loader-inner>div {background-color: #907f819e}
   .whitebg {background: #fff; border-radius: 3px;padding: 20px;margin-bottom: 20px;overflow: hidden;}
   .lanmu img {height: 100px;float: left;margin-right: 20px;}
   .lanmu h1 {font-size: 22px;margin-bottom: 15px;}
   .lanmu p {color: #666;}
</style>
</head>
<body>
  <%@ include file="top.jsp"%>
  <article>
    <div class="lbox">
      <div class="whitebg lanmu">
        <img src="/static/images/lm01.jpg">
        <h1>文章列表</h1>
        <p>对陌生的事物，人们都会有抵触的心态，因此我开发出了软件专区功能，在这里你将充分了解这个软件，如何赚钱，能赚到多少，有什么优>点和缺点等等，尽我最大可能让你找到适合自己的网赚方式。</p>      </div>
      <div class="newblogs bloglist">
        <h2 class="hometitle">
          <span class="tagTitle"> </span>文章专栏
        </h2>
        <ul>

        </ul>
        <p class="page" style='display:none'>
        <p>
        <p class="pageMin">
        <p>
      </div>
    </div>
    <div class="rbox">
      <input name="typeId" id="typeId" value="${typeId}"
             type="hidden">
    <input name="keyword" id="keyword" value="${keyword}"
          type="hidden">
      <div class="cloud animated fadeIn whitebg">
        <h2 class="cloud_hometitle">标签云</h2>
        <ul>

        </ul>
      </div>
      <div class="tuijian2  animated fadeIn whitebg">
        <h2 class="cloud_hometitle">推荐文章</h2>
        <ul class="tjpic animated fadeIn">
          <i><img src="images/t03.jpg"></i>
        </ul>
        <ul class="sidenews">

        </ul>
      </div>

      <div class="djpaihang dj whitebg dianji animated fadeIn"
        style="display:none;animation-delay:0.3s">
        <h2 class="cloud_hometitle">点击排行</h2>
        <ul class="click">

        </ul>

      </div>
      <div class="guanzhu gd whitebg animated fadeIn" style="display:none"
        id="follow-us">
        <h2 class="cloud_hometitle">来波关注</h2>
        <ul>
          <li class="qq"><a href="javascript:void(0)"
            target="_blank"><span>QQ群号</span>121233155</a></li>
          <li class="email"><a href="javascript:void(0)"
            target="_blank"><span>邮箱帐号</span>875657453@qq.com</a></li>
          <li class="wxgzh"><a href="javascript:void(0)"
            target="_blank"><span>微信号</span>gnalnujam</a></li>
        </ul>
      </div>
    </div>
    <a href="#" class="top cd-top animated ">Top</a>
  </article>
<%@ include file="fonter.jsp"%>
  <script src="/static/js/page/list.js"></script>
  <script src="/static/js/layer/layer.js"></script>
</body>

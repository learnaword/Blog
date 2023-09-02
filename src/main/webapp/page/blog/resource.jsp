<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>帮忙么:一个提供网上兼职,手机挣钱的网赚博客平台</title>
 <link rel="shortcut icon" href="/static/images/favicon.ico">
    <meta name="keywords" content="手机兼职,网上赚钱,网上兼职,手机挣钱,网赚软件"/>
    <meta name="description" content="帮忙么:一个提供手机兼职,网上赚钱,网上兼职,手机挣钱,网赚软件的网赚博客平台，平台讲述各种网上挣钱的方法，总有一款适合你。"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%-- <link href="/static/css/bootstrap.min.css" rel="stylesheet"> --%>
    <link href="/static/css/animate.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
	<link href="/static/css/loaders.css"
	rel="stylesheet">
	<link href="/static/css/font-awesome.css"
	rel="stylesheet">
	<style type="text/css">
	.loader-inner > div{
		background-color: #907f819e
	}
</style>
</head>
<body style="background:url(/images/bj.png) repeat top left scroll">
<%@ include file="top.jsp" %>
  <article>
    <div class="lbox">
      <div class="newblogs notice">
      <h2 class="hometitle">
	      <span class="tagTitle">
	      	<a href="javascript:void(0);">学习视频</a>
	      	<a href="javascript:void(0);">项目源码</a>
	      	<a href="javascript:void(0);">笔记</a>
	      	<a href="javascript:void(0);">开发工具</a>
	      </span>
	     资源分享
	     </h2>

        <ul>

        </ul>
        <div class="pagelist ">

            </div>
            <div class="pageMin ">

            </div>
      </div>
    </div>

    <div class="rbox ">
      <div class="search1 whitebg">
        <form name="searchform" id="searchform" style="width:100%;height: 30px;">
        <input name="keyword" id="keyword"  class="input_text"
						value="请输入关键字" style="width:70%;color: rgb(153, 153, 153);"
						onfocus="if(value=='请输入关键字'){this.style.color='#000';value=''}"
						onblur="if(value==''){this.style.color='#999';value='请输入关键字'}"
						type="text">
          <input  class="input_submit" value="搜索" type="button" onclick="selectResource()">
        </form>
      </div>
     		<div class="tuijian2 whitebg" >
		      <h2 class="cloud_hometitle">推荐文章</h2>
		      <ul class="tjpic animated fadeInDown" >
		        <i><img src="images/t03.jpg"></i>
		      </ul>
		      <ul class="sidenews">

		      </ul>
		    </div>
    </div>
  </article>

    <!-- 全局js -->
  <%--   <script src="/static/js/jquery.min.js?v=2.1.4"></script> --%>
	<script src="/static/js/page/resource.js"></script>
	<script src="/static/js/layer/layer.js"></script>

</body>
</html>

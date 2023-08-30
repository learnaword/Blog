<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>专区数据</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="favicon.ico">
	<link href="${pageContext.request.contextPath}/css/github-gist.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/font-awesome.css"
		  rel="stylesheet">
	<link
			href="${pageContext.request.contextPath}/css/plugins/bootstrap-table/bootstrap-table.min.css"
			rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/animate.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/style.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/index.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/base.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/fakeLoader.css"
		  rel="stylesheet">
	<link
			href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css"
			rel="stylesheet">

	<style>
		.news_infos span {
			font-size: 13px;
		}
	</style>
</head>

<body class="white-bg">
<div id="fakeloader"></div>
<div class="wrapper wrapper-content">
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<h5>博客信息表格</h5>
			<div class="ibox-tools">
				<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
				</a> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i
					class="fa fa-wrench"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#">选项1</a></li>
					<li><a href="#">选项2</a></li>
				</ul>
				<a class="close-link"> <i class="fa fa-times"></i>
				</a>
			</div>
		</div>
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="example">
					<table id="allBlog" data-mobile-responsive="true">

					</table>

				</div>
			</div>
		</div>
	</div>
</div>

</div>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/js/highlight.pack.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- 自定义js -->
<script src="${pageContext.request.contextPath}/js/content.js"></script>
<script
		src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/js/contabs.js"></script>
<script src="${pageContext.request.contextPath}/js/fakeLoader.min.js"></script>
<!-- Bootstrap table -->
<script
		src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script
		src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script
		src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script
		src="${pageContext.request.contextPath}/js/admin/temp/tempTable.js"></script>

</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>专区数据</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="/static/images/favicon.ico">
	<link href="/static/css/github-gist.css" rel="stylesheet">
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/font-awesome.css" rel="stylesheet">
	<link href="/static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="/static/css/animate.css" rel="stylesheet">
	<link href="/static/css/style.css" rel="stylesheet">
	<link href="/static/css/index.css" rel="stylesheet">
	<link href="/static/css/base.css" rel="stylesheet">
	<link href="/static/css/fakeLoader.css" rel="stylesheet">
	<link href="/static/css/plugins/sweetalert/sweetalert2.min.css" rel="stylesheet">
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
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/highlight.pack.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<script src="/static/js/bootstrap.min.js"></script>

<!-- 自定义js -->
<script src="/static/js/content.js"></script>
<script src="/static/js/plugins/sweetalert/sweetalert2.min.js"></script>
<script src="/static/js/contabs.js"></script>
<script src="/static/js/fakeLoader.min.js"></script>
<!-- Bootstrap table -->
<script src="/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/static/js/admin/token.js"></script>
<script src="/static/js/admin/soft/softTable.js"></script>
</body>
</html>

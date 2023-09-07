<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>博客后台 - 主页</title>
	<link href="/static/css/admin/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/admin/style.css" rel="stylesheet">
	<link href="/static/plugins/sweetalert2/sweetalert2.min.css" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<span class="label label-success pull-right">总数</span>
					<h5>博客</h5>
				</div>
				<div class="ibox-content">
					<h1 class="allBlog no-margins" style="text-align: center;">10</h1>
					<div class="allBlogPercent stat-percent font-bold text-navy">10%</div>
					<small>已发表</small>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<span class="label label-danger pull-right">总数</span>
					<h5>草稿箱</h5>
				</div>
				<div class="ibox-content">
					<h1 class="draft no-margins" style="text-align: center;">0</h1>
					<div class="draftPercent stat-percent font-bold text-warning">0%</div>
					<small>未发表</small>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<span class="label label-warning pull-right">今天</span>
					<h5>今日发表</h5>
				</div>
				<div class="ibox-content">
					<h1 class="nowBlog no-margins" style="text-align: center;">0</h1>
					<div class="nowBlogPercent stat-percent font-bold text-danger">0%</div>
					<small>较昨日</small>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<span class="label label-info pull-right">今天</span>
					<h5>今日访客</h5>
				</div>
				<div class="ibox-content">
					<h1 class="nowVisitors no-margins" style="text-align: center;">0</h1>
					<div
							class="nowVisitorsPercent stat-percent font-bold text-success">
						0%</div>
					<small>较昨日</small>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<span class="label label-success pull-right">总数</span>
					<h5>储备</h5>
				</div>
				<div class="ibox-content">
					<h1 class="reserced no-margins" style="text-align: center;">0</h1>
					<div class="resercedPercent stat-percent font-bold text-navy">
						0%</div>
					<small>已储备</small>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<span class="label label-danger pull-right">总数</span>
					<h5>垃圾箱</h5>
				</div>
				<div class="ibox-content">
					<h1 class="delete no-margins " style="text-align: center;">0</h1>
					<div class="deletePercent stat-percent font-bold text-warning">
						0%</div>
					<small>已删除</small>
				</div>
			</div>
		</div>

		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<span class="label label-warning pull-right">昨天</span>
					<h5>昨日发表</h5>
				</div>
				<div class="ibox-content">
					<h1 class="yesBlog no-margins" style="text-align: center;">0</h1>
					<div class="yesBlogPercent stat-percent font-bold text-danger">
						0%</div>
					<small>较昨日</small>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<span class="label label-info pull-right">历史</span>
					<h5>历史访客</h5>
				</div>
				<div class="ibox-content">
					<h1 class="visitors no-margins" style="text-align: center;">0</h1>
					<div class=" stat-percent font-bold text-success">
						100% <i class="fa fa-bolt"></i>
					</div>
					<small>总访问量</small>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 全局js -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/plugins/sweetalert2/sweetalert2.min.js"></script>
<script src="/static/js/axios/axios.min.js"></script>
<!-- Data picker -->
<script src="/static/js/admin/token.js"></script>
<script type="module" src="/static/js/admin/index_v1.js"></script>

</body>
</html>


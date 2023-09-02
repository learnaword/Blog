<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>博客数据</title>
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

<body class="white-bg" style="opacity:0">
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
					<div id="toolbar">
						<button class="type btn btn-outline btn-primary" type="button"
								id="typeButton" value="5">All</button>
						<button class="type btn btn-outline btn-primary" type="button"
								id="typeButton" value="-1">草稿</button>
						<button class="type btn btn-outline btn-primary" type="button"
								id="typeButton" value="-2">储备</button>
						<button class="type btn btn-outline btn-info" type="button"
								id="typeButton" value="1">发布</button>
						<button class="type btn btn-outline btn-danger" type="button"
								id="typeButton" value="2">垃圾箱</button>
						<button class="type btn btn-outline btn-success" type="button"
								id="typeButton" value="3">推荐</button>
						<button class="type btn btn-outline btn-primary" type="button"
								id="typeButton" value="4">置顶</button>
						<div class="btn-group">
							<button data-toggle="dropdown"
									class="btn btn-outline btn-warning dropdown-toggle">
								类别 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">

							</ul>
						</div>
						<button class=" btn btn-danger" type="button"
								onclick="getSelectRows(1)">
							<i class="fa fa-remove"></i> 删除
						</button>

						<button class=" btn btn-primary" type="button"
								onclick="getSelectRows(2)">
							<i class="fa fa-cog"></i> 设为置顶
						</button>

						<button class=" btn btn-info" type="button"
								onclick="getSelectRows(3)">
							<i class="fa fa-cog"></i> 设为推荐
						</button>
						<button type="button" onclick="javascript:history.back(2);"
								class="btn btn-danger btn-sm" data-toggle="tooltip"
								data-placement="top" title="返回">
							<i class="fa fa-times"></i> 返回
						</button>
					</div>

				</div>
			</div>

			<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog"
				 aria-hidden="true">
				<div class="modal-dialog" style="width:702px">
					<div class="modal-content animated fadeInUp">
						<button type="button" class="close" style="margin-right:7px"
								data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<div class="infos" style="margin:0px;">
							<div class="newsview" style="padding-top:0px;">
								<h3 class="news_title"></h3>
								<div class="news_author">
									<span class="au01">罗廷方</span><span class="au02"></span><span
										class="au03">共<b></b>人围观
										</span>
								</div>
								<div class="tags"></div>
								<div class="news_about">
									<strong>简介</strong><span class="news_intr"> </span>
								</div>
								<div class="news_infos"></div>
							</div>
						</div>
						<div class="modal-footer">
							<span class="update"></span>
							<button type="button" class="btn btn-primary"
									data-dismiss="modal">关闭</button>
						</div>
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
<script src="/static/js/admin/blog/blogTable.js"></script>

</body>
</html>
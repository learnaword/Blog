<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>查询博客</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="/static/images/favicon.ico">
	<link href="/css/github-gist.css" rel="stylesheet">
	<link href="/css/bootstrap.min.css" rel="stylesheet">
	<link href="/css/font-awesome.css" rel="stylesheet">
	<link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="/css/animate.css" rel="stylesheet">
	<link href="/css/style.css" rel="stylesheet">
	<link href="/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
	<link href="/css/index.css" rel="stylesheet">
	<link href="/css/base.css" rel="stylesheet">
	<link href="/css/fakeLoader.css" rel="stylesheet">
	<style>
		.news_infos span{
			font-size:13px;
		}

	</style>
</head>
<body class="white-bg" style="opacity:0">
<div id="fakeloader"></div>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-content mailbox-content">
					<div class="file-manager">
						<a class="btn btn-block btn-primary compose-mail"
						   href="javascript:void(0);">博客查询</a>
						<div class="space-25"></div>
						<h5 class="tag-title">搜索</h5>
						<form role="form" class="form-inline">
							<div class="form-group">
								<input type="text" onkeyup="findBlogByKey()"
									   placeholder="请输入关键字" id="blogname" class="form-control"
									   style="width:76%;">
								<button id="findBlog" onclick="findBlogByKey()"
										class="btn btn-white pull-right" type="button">搜索</button>
							</div>
						</form>

						<h5>博客列表</h5>
						<div class="paihang">
							<ul class="category-list">

							</ul>
							<div  style="padding-top: 0px;">
								<div class="pageNav "></div>
								<p style="margin:15px auto;text-align:center">
										<span class="allTotal">共<b></b>条记录
										</span>&nbsp;&nbsp; <span class="cPage">第<b></b>页
										</span>&nbsp;&nbsp; <span class="allPage">共<b></b>页
										</span>
								</p>
							</div>

						</div>

						<div class="clearfix"></div>

					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<div class="ibox float-e-margins ">
				<div class="mail-box-header">
					<h2>博客详情</h2>
					<div class="infos" style="margin:0px auto;width: 91.2%;">
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
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 全局js -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/fakeLoader.min.js"></script>
<!-- 自定义js -->
<script src="/js/content.js"></script>
<script src="/js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- iCheck -->
<script src="/js/plugins/iCheck/icheck.min.js"></script>

<!-- jQuery Validation plugin javascript-->
<script src="/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/js/plugins/validate/messages_zh.min.js"></script>
<script src="/js/plugins/validate/form-validate-demo.js"></script>
<script src="/js/highlight.pack.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<!-- Bootstrap table -->
<script src="/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/js/admin/blog/findBlog.js"></script>
</body>
</html>

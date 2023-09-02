<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>博客类别</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="/static/images/favicon.ico">
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/font-awesome.css" rel="stylesheet">
	<link href="/static/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="/static/css/animate.css" rel="stylesheet">
	<link href="/static/css/style.css" rel="stylesheet">
	<link href="/static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="/static/css/plugins/sweetalert/sweetalert2.min.css" rel="stylesheet">
	<link href="/static/css/fakeLoader.css" rel="stylesheet">
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
						   href="javascript:void(0);">博客类别</a>
						<div class="space-25"></div>
						<h5 class="tag-title">增加类别</h5>
						<form role="form" class="form-inline" id="commentForm1">
							<div class="form-group" >
								<input type="text" placeholder="请输入类别名称" id="typename" required="" aria-required="true"
									   class="form-control" style="width:76%;">
								<span class="tip"
									  style="color:#a94442;font-size:12px;padding: 0px;"></span>
							</div>
							<span class="tip"
								  style="color:#a94442;font-size:12px;padding: 0px;"></span>
							<div class="form-group" >
								<select class="form-control m-b" id="type">
									<option value="1">网赚类型</option>
									<option value="2">软件类型</option>
								</select>
								<button id="addType" class="btn btn-white pull-right" type="button"
								>提交</button>
								<span class="tip"
									  style="color:#a94442;font-size:12px;padding: 0px;"></span>
							</div>
						</form>

						<h5>所有类别</h5>
						<ul class="category-list" style="padding: 0">

						</ul>

						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<div class="ibox float-e-margins">
				<div class="mail-box-header">
					<h2>博客类别</h2>

					<table id="allBlogType" data-mobile-responsive="true">

					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="modal-form" class="modal fade" aria-hidden="true">
	<div class="modal-dialog" style="width: 25%;">
		<div class="modal-content">
			<div class="modal-body">
				<div class="row">
					<h3 class="m-t-none m-b">修改类别名称</h3>

					<form role="form" id="commentForm2">
						<div class="form-group">
							<label>原始名称：</label><span id="oldTypeName"></span>
						</div>
						<div class="form-group">
							<input type="text" placeholder="新名称" required="" aria-required="true" class="form-control" id="newTypeName">
						</div>
						<div class="form-group" >
							<select class="form-control m-b" id="newType">
								<option value="1">网赚类型</option>
								<option value="2">软件类型</option>
							</select>
						</div>
						<span class="tip2" style="color:#a94442"></span>
						<div id="update">

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 全局js -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>

<!-- 自定义js -->
<script src="/static/js/content.js"></script>
<script src="/static/js/plugins/sweetalert/sweetalert2.min.js"></script>
<script src="/static/js/fakeLoader.min.js"></script>
<!-- iCheck -->
<script src="/static/js/plugins/iCheck/icheck.min.js"></script>
<!-- jQuery Validation plugin javascript-->
<script src="/static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/static/js/plugins/validate/messages_zh.min.js"></script>
<script src="/static/js/plugins/validate/form-validate-demo.js"></script>

<!-- Bootstrap table -->
<script src="/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/static/js/admin/token.js"></script>
<script src="/static/js/admin/blog/blogType.js"></script>
</body>

</html>
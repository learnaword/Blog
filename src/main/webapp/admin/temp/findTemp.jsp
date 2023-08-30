<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>查找</title>
	<meta name="keywords" content="">
	<meta name="description" content="">

	<link rel="shortcut icon" href="favicon.ico">
	<link href="${pageContext.request.contextPath}/css/github-gist.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/base.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/font-awesome.css"
		  rel="stylesheet">
	<link
			href="${pageContext.request.contextPath}/css/plugins/iCheck/custom.css"
			rel="stylesheet">
	<link
			href="${pageContext.request.contextPath}/css/plugins/summernote/summernote.css"
			rel="stylesheet">
	<link
			href="${pageContext.request.contextPath}/css/plugins/summernote/summernote-bs4.css"
			rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/animate.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/style.css"
		  rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/index.css"
		  rel="stylesheet">
	<link
			href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css"
			rel="stylesheet">
	<link rel="stylesheet"
		  href="${pageContext.request.contextPath}/css/plugins/webuploader/webuploader.css">
	<link rel="stylesheet"
		  href="${pageContext.request.contextPath}/css/plugins/webuploader/webuploader-demo.css">
	<link href="${pageContext.request.contextPath}/css/fakeLoader.css"
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
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="mail-box-header">
					<div class="pull-right tooltip-demo">
						<button id="find_temp" type="button" class="btn btn-white btn-sm"
								data-toggle="tooltip" data-placement="top" title="提交">
							<i class="fa fa-pencil"></i> 提交
						</button>
					</div>
					<div class="mail-box">
						<div class="mail-body">
							<form class="form-horizontal" method="post" id="commentForm">
								<div class="form-group">
								<div class="form-group">
									<label class="col-sm-2 control-label">标题：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="title" name="title">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">内容：</label>
									<div class="col-sm-10">
										<textarea type="text" class="form-control" id="content" name="content"></textarea>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
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
			<!-- 全局js -->
			<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

			<script src="${pageContext.request.contextPath}/js/highlight.pack.js"></script>
			<script>hljs.initHighlightingOnLoad();
			</script>
			<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

			<!-- 自定义js -->
			<script src="${pageContext.request.contextPath}/js/content.js"></script>
			<script
					src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>

			<!-- Web Uploader -->
			<script type="text/javascript">
                // 添加全局站点信息
                var BASE_URL = '${pageContext.request.contextPath}/js/plugins/webuploader';
			</script>
			<script
					src="${pageContext.request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
			<script
					src="${pageContext.request.contextPath}/js/plugins/webuploader/webuploader-demo2.js"></script>

			<!-- jQuery Validation plugin javascript-->
			<script
					src="${pageContext.request.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
			<script
					src="${pageContext.request.contextPath}/js/plugins/validate/messages_zh.min.js"></script>
			<script
					src="${pageContext.request.contextPath}/js/plugins/validate/form-validate-demo.js"></script>

			<!-- iCheck -->
			<script
					src="${pageContext.request.contextPath}/js/plugins/iCheck/icheck.min.js"></script>
			<script src="${pageContext.request.contextPath}/js/fakeLoader.min.js"></script>
			<!-- SUMMERNOTE -->
			<script
					src="${pageContext.request.contextPath}/js/plugins/summernote/summernote.min.js"></script>
			<script
					src="${pageContext.request.contextPath}/js/plugins/summernote/summernote-zh-CN.js"></script>
			<script
					src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
			<script
					src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
			<script
					src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
			<script
					src="${pageContext.request.contextPath}/js/admin/temp/findTemp.js"></script>
</body>
</html>

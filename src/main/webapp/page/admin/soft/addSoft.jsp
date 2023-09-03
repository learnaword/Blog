<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>添加</title>c
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="/static/images/favicon.ico">
	<link href="/static/css/github-gist.css" rel="stylesheet">
	<link href="/static/css/base.css" rel="stylesheet">
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/font-awesome.css" rel="stylesheet">
	<link href="/static/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="/static/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="/static/css/plugins/summernote/summernote-bs4.css" rel="stylesheet">
	<link href="/static/css/animate.css" rel="stylesheet">
	<link href="/static/css/style.css" rel="stylesheet">
	<link href="/static/css/index.css" rel="stylesheet">
	<link href="/static/plugins/sweetalert2/sweetalert2.min.css" rel="stylesheet">
	<link rel="stylesheet" href="/static/css/plugins/webuploader/webuploader.css">
	<link rel="stylesheet" href="/static/css/plugins/webuploader/webuploader-demo.css">
	<link href="/static/css/fakeLoader.css" rel="stylesheet">
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
		<div class="col-sm-9">
			<div class="ibox float-e-margins">
				<div class="mail-box-header">
					<div class="pull-right tooltip-demo">
						<button id="add_soft" type="button" class="btn btn-white btn-sm"
								data-toggle="tooltip" data-placement="top" title="提交">
							<i class="fa fa-pencil"></i> 提交
						</button>
					</div>
					<div class="mail-box">
						<div class="mail-body">
							<form class="form-horizontal" method="post" id="commentForm">
								<div class="form-group">
									<label class="col-sm-2 control-label">封面：</label>
									<div class="col-sm-10">
										<a class="fancybox picPath" href="#pic" data-toggle="modal"
										   onclick="findPicList()"> <img
												class="picPath animated fadeInRight"
												style="width: 190px; height: 115px;" alt="封面" title="点击更换封面"
												src="" />
										</a>
									</div>
								</div>
								<input type="hidden" value="" class="imagePath">
								<div class="form-group">
									<label class="col-sm-2 control-label">标题：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="title"
											   name="title" value="" required="" maxlength="50"
											   aria-required="true">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">摘要：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="introduction"
											   name="introduction" value="" required="" maxlength="400"
											   aria-required="true">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">网赚类型：</label>
									<div class="col-sm-10">
										<select class="form-control m-b" id="typeName" name="typeName">

										</select>
									</div>
								</div>
							</form>
						</div>
					</div>


					<div class="modal inmodal" id="pic" tabindex="-1" aria-hidden="true">
						<div class="modal-dialog" style="width:79.3%;margin-top:-1%;">
							<div class="modal-content animated fadeInUp">
								<button type="button" class="close" style="margin-right:7px"
										data-dismiss="modal">
									<span aria-hidden="true">&times;</span>
								</button>
								<div id="uploader" class="wu-example"
									 style="margin:0px 5.6% 0  5.6%;">
									<div class="queueList">
										<div id="dndArea" class="placeholder"
											 style="min-height: 110px;padding-top: 0px;background:none">
											<div id="filePicker"></div>
											<p>或将照片拖到这里，单次最多可选300张</p>
										</div>
									</div>
									<div class="statusBar" style="display:none;">
										<div class="progress">
											<span class="text">0%</span> <span class="percentage"></span>
										</div>
										<div class="info"></div>
										<div class="btns">
											<div id="filePicker2"></div>
											<div class="uploadBtn">开始上传</div>
										</div>
									</div>
								</div>
								<div class="modal-body picsList"
									 style="height:260px;overflow:scroll; padding: 0px 30px 0px 30px;">
								</div>
							</div>
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
<script src="/static/plugins/sweetalert2/sweetalert2.min.js"></script>
<!-- Web Uploader -->
<script type="text/javascript">
	// 添加全局站点信息
	var BASE_URL = '/js/plugins/webuploader';
</script>
<script src="/static/js/plugins/webuploader/webuploader.min.js"></script>
<script src="/static/js/plugins/webuploader/webuploader-demo2.js"></script>

<!-- jQuery Validation plugin javascript-->
<script src="/static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/static/js/plugins/validate/messages_zh.min.js"></script>
<script src="/static/js/plugins/validate/form-validate-demo.js"></script>

<!-- iCheck -->
<script src="/static/js/plugins/iCheck/icheck.min.js"></script>
<script src="/static/js/fakeLoader.min.js"></script>

<!-- SUMMERNOTE -->
<script src="/static/js/plugins/summernote/summernote.min.js"></script>
<script src="/static/js/plugins/summernote/summernote-zh-CN.js"></script>
<script src="/static/js/admin/token.js"></script>
<script src="/static/js/admin/soft/addSoft.js"></script>
</body>
</html>

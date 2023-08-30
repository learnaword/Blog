<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
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

<title>自动生成</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon" href="favicon.ico">
<link href="/css/github-gist.css"
	rel="stylesheet">
<link href="/css/base.css"
	rel="stylesheet">
<link href="/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/css/font-awesome.css"
	rel="stylesheet">
<link
	href="/css/plugins/iCheck/custom.css"
	rel="stylesheet">
<link
	href="/css/plugins/summernote/summernote.css"
	rel="stylesheet">
<link
	href="/css/plugins/summernote/summernote-bs4.css"
	rel="stylesheet">
<link href="/css/animate.css"
	rel="stylesheet">
<link href="/css/style.css"
	rel="stylesheet">
<link href="/css/index.css"
	rel="stylesheet">
<link
	href="/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="/css/plugins/webuploader/webuploader.css">
<link rel="stylesheet"
	href="/css/plugins/webuploader/webuploader-demo.css">
<link href="/css/fakeLoader.css"
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
			<div class="col-sm-9">
				<div class="ibox float-e-margins">
					<div class="mail-box-header">
						<div class="pull-right tooltip-demo">
                                                       <button type="button" onclick="javascrtpt:window.location.href='https://www.bangmangma.com/admin/blog/blogTable.jsp'"
                                                                class="btn btn-white btn-sm" data-toggle="tooltip"
                                                                data-placement="top" title="列表">
                                                                </i> 列表
                                                        </button>
							<button id="add_blog" type="button" class="btn btn-white btn-sm"
								data-toggle="tooltip" data-placement="top" title="提交">
								<i class="fa fa-pencil"></i> 提交
							</button>
					</div>
					<div class="mail-box">
						<div class="mail-body">
							<form class="form-horizontal" method="post" id="commentForm">
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
                                                                        <label class="col-sm-2 control-label">ID：</label>
                                                                        <div class="col-sm-10">
                                                                                <input type="text" class="form-control" id="id"
                                                                                        name="title" value="0" required="" maxlength="50"
                                                                                        aria-required="true">
                                                                        </div>
                                                                </div>
							</form>
						</div>
			       </div>

		</div>
	</div>

	<!-- 全局js -->
	<script src="/js/jquery.min.js"></script>

	<script src="/js/highlight.pack.js"></script>
	<script>hljs.initHighlightingOnLoad();
	</script>
	<script src="/js/bootstrap.min.js"></script>

	<!-- 自定义js -->
	<script src="/js/content.js"></script>
	<script
		src="/js/plugins/sweetalert/sweetalert.min.js"></script>

	<!-- Web Uploader -->
	<script type="text/javascript">
		// 添加全局站点信息
		var BASE_URL = '/js/plugins/webuploader';
	</script>
	<script
		src="/js/plugins/webuploader/webuploader.min.js"></script>
	<script
		src="/js/plugins/webuploader/webuploader-demo2.js"></script>

	<!-- jQuery Validation plugin javascript-->
	<script
		src="/js/plugins/validate/jquery.validate.min.js"></script>
	<script
		src="/js/plugins/validate/messages_zh.min.js"></script>
	<script
		src="/js/plugins/validate/form-validate-demo.js"></script>

	<!-- iCheck -->
	<script
		src="/js/plugins/iCheck/icheck.min.js"></script>
	<script src="/js/fakeLoader.min.js"></script>
	<script
		src="/js/admin/blog/autoBlog.js"></script>
</body>
</html>

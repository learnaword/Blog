<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>写微头条</title>
	<link rel="shortcut icon" href="/static/images/favicon.ico">
	<link href="/static/css/github-gist.css" rel="stylesheet">
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/font-awesome.css" rel="stylesheet">
	<link href="/static/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="/static/css/fakeLoader.css" rel="stylesheet">
	<link href="/static/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="/static/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
	<link href="/static/css/animate.css" rel="stylesheet">
	<link href="/static/css/style.css" rel="stylesheet">
	<link href="/static/css/index.css" rel="stylesheet">
	<link href="/static/css/base.css" rel="stylesheet">
	<link href="/static/css/info.css" rel="stylesheet">
	<link href="/static/plugins/sweetalert2/sweetalert2.min.css" rel="stylesheet">
	<link rel="stylesheet" href="/static/css/plugins/webuploader/webuploader.css">
	<link rel="stylesheet"href="/static/css/plugins/webuploader/webuploader-demo.css">
	<style>
		.news_infos span{
			font-size:13px;
		}
	</style>
</head>

<body class="white-bg" style="opacity:0">
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="mail-box-header">
					<div class="pull-right tooltip-demo">
						<button type="button" onclick="javascrtpt:window.location.href='https://www.bangmangma.com/admin/blog/blogTable.jsp'"
								class="btn btn-white btn-sm" data-toggle="tooltip"
								data-placement="top" title="列表">
							</i> 列表
						</button>
						</button>
						<button type="button" onclick="javascrtpt:window.location.href='https://www.bangmangma.com/admin/blog/autoBlog.jsp'"
								class="btn btn-white btn-sm" data-toggle="tooltip"
								data-placement="top" title="自动">
							</i>自动
						</button>

						<button type="button" onclick="javascript:history.back(2);"
								class="btn btn-danger btn-sm" data-toggle="tooltip"
								data-placement="top" title="返回">
							<i class="fa fa-times"></i> 返回
						</button>
					</div>
					<h2>写微头条</h2>
				</div>
				<div class="mail-box">
					<div class="mail-body">
						<form class="form-horizontal" method="post" id="commentForm">
							<div class="form-group">
								<label class="col-sm-2 control-label">标题：</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="title" required="" aria-required="true"
										   name="title" value="" maxlength="50">
								</div>
							</div>
						</form>
					</div>
					<div class="mail-text h-200" style="width:84%;margin:0 auto;">
						<div id="summernote"></div>
						<div class="clearfix"></div>
					</div>
					<div class="mail-body text-right tooltip-demo">
						<button id="fb_draft3" type="button"
								class="btn btn-white btn-sm" data-toggle="tooltip"
								data-placement="top" title="发表">
							<i class="fa fa-pencil"></i>发表
						</button>
						<button id="prev1" type="button"
								class="btn btn-sm btn-primary"
								data-placement="top" title="预览">
							<i class="fa fa-reply"></i> 预览
						</button>
						<button id="prev2"  type="button" style="display:none"
								onclick="prevBlog()" data-target="#myModal" data-toggle="modal">
						</button>
						<button id="del_draft2" type="button"
								class="btn btn-white btn-sm" data-toggle="tooltip"
								data-placement="top" title="删除">
							<i class="fa fa-pencil"></i>删除
						</button>
						<button id="add_draft2" type="button"
								class="btn btn-white btn-sm" data-toggle="tooltip"
								data-placement="top" title="存为草稿">
							<i class="fa fa-pencil"></i> 存为草稿
						</button>
					</div>
					<div class="clearfix"></div>
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
								<span class="au01">house</span><span class="au02"></span><span
									class="au03">共<b></b>人围观
									</span>
							</div>
							<div class="tags"></div>
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
</body>
<!-- 全局js -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/highlight.pack.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<script src="/static/js/bootstrap.min.js"></script>


<!-- 自定义js -->
<script src="/static/js/content.js"></script>
<script
		src="/static/plugins/sweetalert2/sweetalert2.min.js"></script>

<!-- iCheck -->
<script
		src="/static/js/plugins/iCheck/icheck.min.js"></script>

<!-- jQuery Validation plugin javascript-->
<script src="/static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/static/js/plugins/validate/messages_zh.min.js"></script>
<script src="/static/js/plugins/validate/form-validate-demo.js"></script>
<script src="/static/js/fakeLoader.min.js"></script>
<!-- Web Uploader -->
<script type="text/javascript">
	// 添加全局站点信息
	var BASE_URL = '/js/plugins/webuploader';
</script>
<script src="/static/js/plugins/webuploader/webuploader.min.js"></script>
<script src="/static/js/plugins/webuploader/webuploader-demo2.js"></script>
<!-- SUMMERNOTE -->
<script src="/static/js/plugins/summernote/summernote.min.js"></script>
<script src="/static/js/plugins/summernote/summernote-zh-CN.js"></script>
<script src="/static/js/admin/token.js"></script>
<script src="/static/js/admin/blog/media.js"></script>
</body>

</html>

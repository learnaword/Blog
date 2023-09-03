<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>更新博客</title>
	<link rel="shortcut icon" href="https://www.bangmangma.com/images/favicon.ico">
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
		.form1{display:none;}
	</style>
</head>
<body class="white-bg">
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-content mailbox-content">
					<div class="file-manager">
						<a class="btn btn-block btn-primary compose-mail"
						   href="javascript:void(0);">修改博客</a>
						<h5>数据信息</h5>
						<ul class="folder-list m-b-md" style="padding: 0">
							<li><a href="javascript:void(0);"> <i class="fa fa-level-up"></i>
								&nbsp;置顶<span class="label label-danger pull-right" id="istop"></span>
							</a></li>
							<li><a href="javascript:void(0);"> <i
									class="fa fa-hand-pointer-o"></i> 博主推荐 <span
									class="label label-warning pull-right" id="isrecommend"></span>
							</a></li>
						</ul>

						<h5 class="tag-title">封面</h5>
						<a class="fancybox picPath" href="#pic" data-toggle="modal" onclick="findPicList()">
						</a>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<div class="ibox float-e-margins">
				<div class="mail-box-header">
					<div class="pull-right tooltip-demo">
						<button id="zs" type="button"
								class="btn btn-white btn-sm" data-toggle="tooltip"
								data-placement="top" title="展示">
							<i class="fa fa-pencil"></i>展示
						</button>
						<button type="button" onclick="javascript:window.location.href='https://www.bangmangma.com/admin/blog/blogTable.jsp'"
								class="btn btn-white btn-sm" data-toggle="tooltip"
								data-placement="top" title="列表">
							<i class="fa fa-pencil"></i>列表
						</button>
						<button type="button" onclick="javascript:window.location.href='https://www.bangmangma.com/admin/blog/autoBlog.jsp'"
								class="btn btn-white btn-sm" data-toggle="tooltip"
								data-placement="top" title="自动">
							<i class="fa fa-pencil"></i>自动
						</button>
						<button type="button" onclick="javascript:history.back(2);"
								class="btn btn-danger btn-sm" data-toggle="tooltip"
								data-placement="top" title="返回">
							<i class="fa fa-times"></i> 返回
						</button>
					</div>
					<h2>修改博客</h2>
				</div>
				<div class="mail-box">
					<div class="mail-body">
						<form class="form-horizontal" method="post" id="commentForm">
							<input type="hidden" value="" class="imagePath">
							<div class="form-group form1">
								<label class="col-sm-2 control-label">标题：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="title" required="" aria-required="true"
										   name="title" value="" maxlength="50">
								</div>
							</div>
							<div class="form-group form1">
								<label class="col-sm-2 control-label">摘要：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="introduction" required="" aria-required="true"
										   name="introduction" value="" maxlength="400">
								</div>
							</div>
							<div class="form-group hidden">
								<label class="col-sm-2 control-label">网赚类型：</label>
								<div class="col-sm-10">
									<select class="form-control m-b" id="typeName" name="typeName">

									</select>
								</div>
							</div>
							<div class="form-group form1">
								<label class="col-sm-2 control-label">软件类型：</label>
								<div class="col-sm-10">
									<select class="form-control m-b" id="soft" name="soft">

									</select>
								</div>
							</div>
							<div class="form-group form1">
								<label class="col-sm-2 control-label">专区分类：</label>
								<div class="col-sm-10">
									<select class="form-control m-b" id="commentnum" name="commentnum">
										<option value="1">介绍</option>
										<option value="2">经验</option>
										<option value="3">问答</option>
									</select>
								</div>
							</div>
							<div class="form-group hidden">
								<label class="col-sm-2 control-label">推荐：</label>
								<div class="col-sm-10">
									<select class="form-control m-b" id="agreenum" name="agreenum">

									</select>
								</div>
							</div>
							<div class="form-group form1">
								<label class="col-sm-2 control-label">排名指数：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="clicknum" name="clicknum" value="0">
								</div>
							</div>
							<div class="form-group form1">
								<label class="col-sm-2 control-label">关键字：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="keyword" required="" aria-required="true"
										   name="keyword" value="" maxlength="30"> <p class="help-block m-b-none"><i class="fa fa-info-circle"></i> 多个关键字之间用“;”分隔</p>
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
								data-placement="top" title="草稿">
							<i class="fa fa-pencil"></i> 草稿
						</button>
						<button id="prev1" type="button"
								class="btn btn-sm btn-primary"
								data-placement="top" title="预览">
							<i class="fa fa-reply"></i> 预览
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


		<div class="modal inmodal" id="pic" tabindex="-1"
			 aria-hidden="true">
			<div class="modal-dialog" style="width:79.3%;margin-top:-1%;">
				<div class="modal-content animated fadeInUp">
					<button type="button" class="close" style="margin-right:7px"
							data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
					<div id="uploader" class="wu-example" style="margin:0px 5.6% 0  5.6%;">
						<div class="queueList">
							<div id="dndArea" class="placeholder" style="min-height: 110px;padding-top: 0px;background:none">
								<div id="filePicker"></div>
								<p>或将照片拖到这里，单次最多可选300张</p>
							</div>
						</div>
						<div class="statusBar" style="display:none;">
							<div class="progress">
								<span class="text">0%</span>
								<span class="percentage"></span>
							</div>
							<div class="info"></div>
							<div class="btns">
								<div id="filePicker2"></div>
								<div class="uploadBtn">开始上传</div>
							</div>
						</div>
					</div>
					<div class="modal-body picsList" style="height:260px;overflow:scroll; padding: 0px 30px 0px 30px;">
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
<script src="/static/js/bootstrap.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>

<!-- 自定义js -->
<script src="/static/js/content.js"></script>
<script src="/static/plugins/sweetalert2/sweetalert2.min.js"></script>
<!-- iCheck -->
<script src="/static/js/plugins/iCheck/icheck.min.js"></script>

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
<script src="/static/js/admin/blog/updateBlog.js"></script>
</body>

</html>

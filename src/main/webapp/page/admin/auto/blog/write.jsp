<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>自动生成</title>
	<link rel="shortcut icon" href="/images/favicon.ico">
	<link href="/upload/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/layui/2.8.4/css/layui.min.css" rel="stylesheet"/>
	<link href="/upload/css/style.css" rel="stylesheet">
	<link href="/upload/css/sweetalert2.min.css" rel="stylesheet">
	<link href="/upload/css/summernote.css" rel="stylesheet">
	<link href="/upload/css/summernote-bs3.css" rel="stylesheet">
	<link href="/static/css/admin/font-awesome.css" rel="stylesheet">
	<link href="/upload/css/info.min.css" rel="stylesheet">
	<link href="/upload/css/base.css" rel="stylesheet">

</head>
<style>
	.layui-layer-page .layui-layer-content {
		position: relative;
		overflow: visible !important;
	}
</style>

<body class="white-bg">
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-content mailbox-content">
					<div class="file-manager">
						<a class="btn btn-block btn-primary compose-mail"
						   href="javascript:void(0);">生成博客</a>
						<div class="space-25"></div>
						<div class="clearfix"></div>
					</div>
					<div class="layui-upload-drag" style="display: block;" id="ID-upload-demo-drag">
						<i class="layui-icon layui-icon-upload"></i>
						<div>点击上传封面</div>
						<div class="layui-hide" id="ID-upload-demo-preview">
							<hr> <img src="" id ="images" alt="上传成功后渲染" style="max-width: 100%">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<div class="ibox float-e-margins">
				<div class="mail-box-header">
					<div class="layui-form-item">
						<div class="layui-input-group">
							<div class="layui-input-suffix">
								<button type="button"  lay-verify="required" class="layui-btn layui-btn-warm layui-btn-radius">文章标题</button>
							</div>
							<input type="text" name="title" style="width: 600px;border-color: #ff9900;" placeholder="输入标题" class="layui-input">
						</div>
					</div>
				</div>
				<div class="mail-text h-200" style="width:100%;margin:0 auto;">
					<div id="summernote"></div>
					<div class="clearfix"></div>
				</div>
				<div class="mail-body text-right tooltip-demo">
					<button id="draft_submit" type="button" class="btn btn-sm btn-primary" data-placement="top" title="发表" data-toggle="tooltip">
						<i class="fa"></i> 存到草稿
					</button>
					<button id="refresh_submit" type="button" class="btn btn-sm btn-primary" data-placement="top" title="发表" data-toggle="tooltip">
						<i class="fa"></i> 刷新发布
					</button>
					<button id="default_submit" type="button" class="btn btn-sm btn-primary" data-placement="top" title="发表" data-toggle="tooltip">
						<i class="fa"></i> 默认发布
					</button>
					<button id="prev1" type="button" class="btn btn-sm btn-primary" data-placement="top" title="发表" lay-on="page" data-toggle="tooltip">
						<i class="fa"></i> 选择模版
					</button>
					<button type="button" onclick="javascript:window.location.href='/page/admin/blog/table.jsp'"
							class="btn layui-btn-primary layui-border-blue btn-sm" data-toggle="tooltip"
							title="返回列表">
						<i class="fa"></i>返回列表
					</button>
				</div>

				<div class="clearfix"></div>
			</div>
		</div>
		<div id="configBlog" style="margin-right:15px;" class="layui-hide">
			<form class="layui-form layui-form-pane" style="padding:10px;" action="xxx">
				<div class="layui-form-item">
					<label class="layui-form-label">模板选择</label>
					<div class="layui-input-inline">
						<select name="autoConfig">
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button type="button" class="layui-btn" id="submit_btn">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!--全局js -->
<script src="https://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>
<script src="/upload/js/bootstrap.min.js"></script>
<!--request携带token-->
<script src="/upload/js/axios.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/layui/2.8.4/layui.min.js"></script>
<!--Swal弹框 -->
<script src="/upload/js/sweetalert2.min.js"></script>
<script src="/upload/css/sweetalert2.min.css"></script>
<!--上传图片-->
<!--SUMMERNOTE -->
<script src="/upload/js/summernote.min.js"></script>
<script src="/upload/js/summernote-zh-CN.js"></script>
<!--自定义js-->
<script src="/upload/js/admin/token.js"></script>
<script type="module" src="/static/js/admin/auto/blog/write.js"></script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改句子</title>
	<link rel="shortcut icon" href="/images/favicon.ico">
	<link href="/static/css/admin/bootstrap.min.css" rel="stylesheet">
	<link href="/static/plugins/layui/css/layui.css" rel="stylesheet"/>
	<link href="/static/css/admin/style.css" rel="stylesheet">
	<link href="/static/plugins/sweetalert2/sweetalert2.min.css" rel="stylesheet">
	<link href="/static/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="/static/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
	<link href="/static/css/admin/font-awesome.css" rel="stylesheet">
	<link href="/static/css/info2.css" rel="stylesheet">
	<link href="/static/css/admin/base.css" rel="stylesheet">
</head>

<body class="white-bg">
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-content mailbox-content">
					<div class="file-manager">
						<a class="btn btn-block btn-primary compose-mail"
						   href="javascript:void(0);">修改句子</a>
						<div class="space-25"></div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<div class="ibox float-e-margins">
				<div class="mail-box-header">
					<form class="layui-form layui-form-pane" style="padding:10px;" action="xxx">
						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">句子内容</label>
							<div class="layui-input-block">
								<textarea name="content" placeholder="请输入内容" class="layui-textarea"></textarea>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">使用次数：</label>
							<div class="layui-input-inline layui-input-wrap">
								<input type="text" name="usages" lay-verify="required" placeholder="请输入" autocomplete="off" lay-affix="clear" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item" pane>
							<label class="layui-form-label">是否删除：</label>
							<div class="layui-input-block">
								<input type="radio" name="status" value="0" title="否" checked>
								<input type="radio" name="status" value="1" title="是">
							</div>
						</div>
					</form>
					<div class="mail-body text-right tooltip-demo">
						<button id="submit_btn" type="button" class="btn btn-sm btn-primary" data-placement="top" title="发表" data-toggle="tooltip">
							<i class="fa"></i> 提交
						</button>
						<button type="button" onclick="javascript:window.location.href='../../auto/sentence/table.jsp'"
								class="btn layui-btn-primary layui-border-blue btn-sm" data-toggle="tooltip"
								title="返回列表">
							<i class="fa"></i>返回列表
						</button>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<!--全局js -->
		<script src="/static/js/jquery.min.js"></script>
		<script src="/static/js/bootstrap.min.js"></script>
		<!--request携带token-->
		<script src="/static/js/axios/axios.min.js"></script>
		<script src="/static/plugins/layui/layui.js"></script>
		<!--Swal弹框 -->
		<script src="/static/plugins/sweetalert2/sweetalert2.min.js"></script>
		<script src="/static/plugins/sweetalert2/sweetalert2.min.css"></script>
		<!--上传图片-->
		<!--SUMMERNOTE -->
		<script src="/static/js/plugins/summernote/summernote.min.js"></script>
		<script src="/static/js/plugins/summernote/summernote-zh-CN.js"></script>
		<!--自定义js-->
		<script src="/static/js/admin/token.js"></script>
		<script type="module" src="/static/js/admin/auto/sentence/update.js"></script>
</body>
</html>

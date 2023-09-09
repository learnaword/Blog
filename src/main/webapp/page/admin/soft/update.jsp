<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>添加专区</title>
	<link rel="shortcut icon" href="/images/favicon.ico">
	<link href="/static/css/admin/bootstrap.min.css" rel="stylesheet">
	<link href="/static/plugins/layui/css/layui.css" rel="stylesheet"/>
	<link href="/static/css/admin/style.css" rel="stylesheet">
	<link href="/static/plugins/sweetalert2/sweetalert2.min.css" rel="stylesheet">
	<link href="/static/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="/static/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
	<link href="/static/css/admin/font-awesome.css" rel="stylesheet">
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
						   href="javascript:void(0);">写配置</a>
						<div class="space-25"></div>
						<div class="clearfix"></div>
					</div>
					<div class="layui-upload-drag" style="display: block;" id="ID-upload-demo-drag">
						<i class="layui-icon layui-icon-upload"></i>
						<div>封面配置</div>
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
								<button type="button"  lay-verify="required" class="layui-btn layui-btn-warm layui-btn-radius">名称</button>
							</div>
							<input type="text" name="title" style="width: 500px;border-color: #ff9900;" placeholder="输入名称" class="layui-input">
						</div>
					</div>
					<form class="layui-form layui-form-pane" style="padding:10px;" action="xxx">
						<div class="layui-form-item">
							<label class="layui-form-label">描述：</label>
							<div class="layui-input-block">
								<input type="text" name="introduction" autocomplete="off" placeholder="请输入" lay-verify="required" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">类型：</label>
							<div class="layui-input-inline">
								<select name="typeId" lay-filter="aihao">
									<option value="1">暂时没有</option>
								</select>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">token：</label>
							<div class="layui-input-inline layui-input-wrap">
								<input type="text" name="token" lay-verify="required" placeholder="请输入" autocomplete="off" lay-affix="clear" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">状态：</label>
							<div class="layui-input-inline">
								<select name="status">
									<option value="0" selected>发布</option>
									<option value="1">删除</option>
								</select>
							</div>
						</div>
					</form>
					<div class="mail-body text-right tooltip-demo">
						<button id="submit_btn" type="button" class="btn btn-sm btn-primary" data-placement="top" title="发表" data-toggle="tooltip">
							<i class="fa"></i> 发布
						</button>
						<button type="button" onclick="javascript:window.location.href='table.jsp'"
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
		<script type="module" src="/static/js/admin/soft/update.js"></script>
</body>
</html>

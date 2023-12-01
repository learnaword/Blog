<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>添加推荐</title>
	<link rel="shortcut icon" href="/images/favicon.ico">
	<link href="/static/plugins/cron/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/layui/2.8.4/css/layui.min.css" rel="stylesheet"/>
	<link href="/upload/css/style.css" rel="stylesheet">
	<link href="/upload/css/sweetalert2.min.css" rel="stylesheet">
	<link href="/upload/css/summernote.css" rel="stylesheet">
	<link href="/upload/css/summernote-bs3.css" rel="stylesheet">
	<link href="/upload/css/base.css" rel="stylesheet">
	<link href="/static/plugins/cron/font/font-awesome.min.css" rel="stylesheet">
	<script src="/static/plugins/cron/cronGen.css"></script>
</head>
<body class="white-bg">
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-2">
			<div class="ibox float-e-margins">
			</div>
		</div>
		<div class="col-sm-8">
			<div class="ibox float-e-margins">
				<div class="mail-box-header">
					<div class="layui-form-item">
						<div class="layui-input-group">
							<div class="layui-input-suffix">
								<button type="button"  lay-verify="required" class="layui-btn layui-btn-warm layui-btn-radius">名称</button>
							</div>
							<input type="text" name="name" style="width: 432px;border-color: #ff9900;" placeholder="输入名称" class="layui-input">
						</div>
					</div>

					<form class="layui-form-pane" style="padding:10px;" action="xxx">
						<div class="layui-form-item">
							<label class="layui-form-label">处理器名字：</label>
							<div class="layui-input-inline layui-input-wrap">
								<input type="text" name="handlerName" style="width: 400px" lay-verify="required" placeholder="处理器名字" autocomplete="off" lay-affix="clear" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">cron：</label>
							<div class="layui-input-inline layui-input-wrap">
								<input type="text" id="cron" style="width: 400px" name="cronExpression" lay-verify="required" placeholder="cron表达式" autocomplete="off" lay-affix="clear" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">重试次数：</label>
							<div class="layui-input-inline layui-input-wrap">
								<input type="text" style="width: 400px" name="retryCount" lay-verify="required" placeholder="重试次数" autocomplete="off" lay-affix="clear" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">重试间隔：</label>
							<div class="layui-input-inline layui-input-wrap">
								<input type="text" name="retryInterval" style="width: 400px" lay-verify="required" placeholder="重试间隔" autocomplete="off" lay-affix="clear" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">监控超时时间：</label>
							<div class="layui-input-inline layui-input-wrap">
								<input type="text" name="monitorTimeout" style="width: 400px" lay-verify="required" placeholder="监控超时时间" autocomplete="off" lay-affix="clear" class="layui-input">
							</div>
						</div>
					</form>
					<div class="mail-body text-right tooltip-demo">
						<button id="submit_btn" type="button" class="btn btn-sm btn-primary" data-placement="top" title="发表" data-toggle="tooltip">
							<i class="fa"></i> 发布
						</button>
						<button type="button" onclick="javascript:window.location.href='../job/table.jsp'"
								class="btn layui-btn-primary layui-border-blue btn-sm" data-toggle="tooltip"
								title="返回列表">
							<i class="fa"></i>返回列表
						</button>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
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
<script src="/static/plugins/cron/cronGen.js"></script>

<!--自定义js-->
<script src="/upload/js/admin/token.js"></script>
<script type="module" src="/static/js/admin/job/update.js"></script>
</body>
</html>

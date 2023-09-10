<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>登录</title>
	<link rel="shortcut icon" href="/images/favicon.ico">
	<link href="/upload/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/admin/font-awesome.css" rel="stylesheet">
	<link href="/static/css/admin/animate.css" rel="stylesheet">
	<link href="/upload/css/style.css" rel="stylesheet">
	<link href="/static/css/admin/login.css" rel="stylesheet">
</head>
<body class="signin">
<div class="signinpanel">
	<div class="row">
		<div class="col-sm-7">
			<div class="signin-info">
				<div class="logopanel m-b">
					<h1>[ www.bangmangma.com ]</h1>
				</div>
				<div class="m-b"></div>
				<h4>
					欢迎进入 <strong> 后台管理网站</strong>
				</h4>
				<ul class="m-b">
					<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>
						权限分配、操作日志</li>
					<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>
						访客记录、IP剔除</li>
					<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>
						分页数据、动态图表</li>
					<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 所有操作都异步</li>
					<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>
						注：访问者无删除、修改操作</li>
				</ul>
				<div class=" tooltip-demo ">
					<strong>还没有账号？
						<a href="#" style="color:#3c3a39" data-toggle="tooltip" data-placement="top" title="QQ:875657453">联系站长&raquo;</a>
						<a href="/" style="color:#3c3a39;margin-left:15px;">首页&raquo;</a>
					</strong>
				</div>
			</div>
		</div>
		<div class="col-sm-5">
			<form id="login">
				<h3 class="no-margins">登录</h3>
				<p class="m-t-md">登录访问 www.bangmangma 后台管理网站</p>
				<input type="text" id="username" name="username" class="form-control uname" placeholder="用户名" />
				<input type="password" name="password" id="password" class="form-control pword m-b" placeholder="密码" />
				<a style="color:#101010de" href="javascript:void(0);">忘记密码了？</a>
				<span class="msg" style="color:#6722228a;margin-left:20px;" href="javascript:void(0);">${message}</span>
				<button type="button" onclick="login()" class="btn btn-success btn-block">登录</button>
			</form>
		</div>
	</div>
	<div class="signup-footer">
		<div class="pull-left">
			<strong>&copy; 2018 house blog</strong>
		</div>
	</div>
</div>
<script src="https://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>
<script src="/static/js/admin/login.js"></script>
</body>
</html>

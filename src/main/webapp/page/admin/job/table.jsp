<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>推荐管理</title>
	<link rel="shortcut icon" href="/images/favicon.ico">
	<link rel="stylesheet" href="/static/plugins/layui/css/layui.css" media="all" />
	<link href="/upload/css/sweetalert2.min.css" rel="stylesheet"></link>
	<script src="/upload/js/sweetalert2.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/layui/2.8.4/layui.min.js"></script>
	<script src="/upload/js/axios.min.js"></script>
	<script src="https://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>
	<script src="/upload/js/admin/token.js"></script>
	<script type="module" src="/static/js/admin/job/table.js"></script>
</head>
<body>
<div style="padding: 16px;">
	<table class="layui-hide" id="test" lay-filter="test"></table>
</div>
<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" lay-event="refresh">刷新</button>
		<button class="layui-btn layui-btn-sm" id="dropdownButton">
			操作
			<i class="layui-icon layui-icon-down layui-font-12"></i>
		</button>
		<button class="layui-btn layui-btn-sm layui-bg-blue" id="reloadTest">
			筛选
			<i class="layui-icon layui-icon-down layui-font-12"></i>
		</button>
		<button class="layui-btn layui-btn-sm layui-btn-primary" id="rowMode">
			<span>{{= d.lineStyle ? '多行' : '单行' }}模式</span>
			<i class="layui-icon layui-icon-down layui-font-12"></i>
		</button>
	</div>
</script>
<script type="text/html" id="barDemo">
	<div class="layui-clear-space">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs" lay-event="more">
			更多
			<i class="layui-icon layui-icon-down"></i>
		</a>
	</div>
</script>
<script>
</script>
</body>
</html>
</body>
</html>

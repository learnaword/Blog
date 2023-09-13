<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>文件管理</title>
	<link rel="shortcut icon" href="/images/favicon.ico">
	<link rel="stylesheet" href="/static/plugins/layui/css/layui.css" media="all" />
	<link href="/upload/css/sweetalert2.min.css" rel="stylesheet"></link>
	<script src="/upload/js/sweetalert2.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/layui/2.8.4/layui.min.js"></script>
	<script src="/static/js/plugins/axios/axios.min.js"></script>
	<script src="https://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>
	<script src="/upload/js/admin/token.js"></script>
	<script type="module" src="/static/js/admin/file/table.js"></script>
</head>
<body>
<div style="padding: 16px;">
	<table class="layui-hide" id="test" lay-filter="test"></table>
	<button style="display: none" type="button" id ="upload" class="layui-btn layui-btn-sm" lay-options="{accept: 'file'}">
		<i class="layui-icon layui-icon-upload"></i>
		文件生成
	</button>
</div>
<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" lay-event="refresh">刷新</button>
		<button type="button" class="layui-btn layui-btn-sm demo-class-accept" lay-event="upload">
			<i class="layui-icon layui-icon-upload"></i>
			上传文件
		</button>
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
	layui.use(function(){
		var upload = layui.upload;
		var $ = layui.$;
		var element = layui.element;
		var token = localStorage.getItem("accessToken")
		// 渲染
		upload.render({
			elem: '#upload',
			url: '/admin/file/upload', // 此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
			accept: 'file',
			headers: {
				'Authorization': 'Bearer ' + token // 设置Token请求头
			},
			progress: function(n, elem, res, index){
				Swal.fire({
					title: '执行中',
					text: "请等待！", //显示内容
					showConfirmButton: false, // 隐藏确认按钮
					allowOutsideClick: false, // 防止用户点击外部关闭窗口
					onBeforeOpen: () => {
						Swal.showLoading(); // 显示加载指示器
					}
				});
			},
			done: function(res){
				Swal.fire({
					type: 'success', // 弹框类型
					title: '上传文件', //标题
					text: '上传成功！', //显示内容
					confirmButtonText: '确定',
				}).then(function(isConfirm) {
					location.reload();
				})
			}
		});
	});
</script>
</body>
</html>
</body>
</html>

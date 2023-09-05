<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>句子管理</title>
	<link rel="shortcut icon" href="/static/images/favicon.ico">
	<link rel="stylesheet" href="/static/plugins/layui/css/layui.css" media="all" />
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/plugins/sweetalert2/sweetalert2.min.css" rel="stylesheet"></link>
	<script src="/static/plugins/sweetalert2/sweetalert2.min.js"></script>
	<link href="/static/css/style.css" rel="stylesheet">
	<link href="/static/css/info.css" rel="stylesheet">
	<link href="/static/css/base.css" rel="stylesheet">
	<script src="/static/plugins/layui/layui.js"></script>
	<script src="/static/js/axios/axios.min.js"></script>
	<script src="/static/js/jquery.min.js"></script>
	<script src="/static/js/admin/token.js"></script>
	<script type="module" src="/static/js/admin/auto/sentence/table.js"></script>
</head>
<body class="white-bg">
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-content mailbox-content">
					<div class="file-manager">
						<a class="btn btn-block btn-primary compose-mail"
						   href="javascript:void(0);">句子管理</a>
						<div class="space-25"></div>
						<div class="clearfix"></div>
					</div>
					<div class="layui-upload-drag" style="display: block;" id="upload_file">
						<i class="layui-icon layui-icon-upload"></i>
						<div>文件生成</div>
						<div class="layui-hide" id="ID-upload-demo-preview">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<div class="ibox float-e-margins">
				<div class="mail-box-header">
					<div style="padding: 16px;">
						<table class="layui-hide" id="test" lay-filter="test"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
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
	layui.use(function(){
		var upload = layui.upload;
		var $ = layui.$;
		var element = layui.element;
		var token = localStorage.getItem("accessToken")
		// 渲染
		upload.render({
			elem: '#upload_file',
			url: '/admin/auto-sentence/fileCreate', // 此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
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
					title: '文件执行', //标题
					text: "执行成功！", //显示内容
					confirmButtonText: '确定',
				}).then(function(isConfirm) {
				})
			}
		});
	});
</script>
</body>
</html>
</body>
</html>

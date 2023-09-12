<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>添加配置</title>
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
					<div class="layui-upload-drag" style="display: block;margin-top:10px" id="content-image-drag">
						<i class="layui-icon layui-icon-upload"></i>
						<div>内容图片配置</div>
						<div class="layui-hide" id="content-image-preview">
							<hr> <img src="" id ="contentImages" alt="上传成功后渲染" style="max-width: 100%">
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
							<input type="text" name="title" style="width: 600px;border-color: #ff9900;" placeholder="输入名称" class="layui-input">
						</div>
					</div>
					<form class="layui-form layui-form-pane" style="padding:10px;" action="xxx">
						<div class="layui-form-item">
							<label class="layui-form-label">专区</label>
							<div class="layui-input-inline">
								<select name="softId">
								</select>
							</div>
							<div class="layui-input-inline">
								<select name="softSection">
									<option value="1" selected>介绍</option>
									<option value="2">经验</option>
									<option value="3">问答</option>
								</select>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">广告推荐</label>
							<div class="layui-input-inline">
								<select name="adType" lay-filter="aihao">
									<option value="1">暂时没有</option>
								</select>
							</div>
						</div>
						<div class="layui-form-item" pane>
							<label class="layui-form-label">是否置顶：</label>
							<div class="layui-input-block">
								<input type="radio" name="isTop" value="0" title="否" checked>
								<input type="radio" name="isTop" value="1" title="是">
							</div>
						</div>
						<div class="layui-form-item" pane>
							<label class="layui-form-label">是否推荐：</label>
							<div class="layui-input-block">
								<input type="radio" name="isRecommend" value="0" title="否" checked>
								<input type="radio" name="isRecommend" value="1" title="是">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">排名指数：</label>
							<div class="layui-input-inline layui-input-wrap">
								<input type="text" name="rankScore" lay-verify="required" placeholder="请输入" autocomplete="off" lay-affix="clear" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">博客状态：</label>
							<div class="layui-input-inline">
								<select name="blogStatus">
									<option value="1" selected>发布</option>
									<option value="-1">储备</option>
									<option value="-2">草稿</option>
									<option value="2">删除</option>
								</select>
							</div>
						</div>
						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">软件推荐html</label>
							<div class="layui-input-block">
								<textarea name="recommendHtml" style="height:300px;"placeholder="请输入内容" class="layui-textarea"></textarea>
							</div>
						</div>
					</form>
					<div class="mail-body text-right tooltip-demo">
						<button id="submit_btn" type="button" class="btn btn-sm btn-primary" data-placement="top" title="发表" data-toggle="tooltip">
							<i class="fa"></i> 发布
						</button>
						<button type="button" onclick="javascript:window.location.href='../../blog/table.jsp'"
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
<!--自定义js-->
<script src="/upload/js/admin/token.js"></script>
<script type="module" src="/static/js/admin/auto/config/add.js"></script>
</body>
</html>

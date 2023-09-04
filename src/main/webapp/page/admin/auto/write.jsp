<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>自动生成</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" href="/static/images/favicon.ico">
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/style.css" rel="stylesheet">
	<link href="/static/css/index.css" rel="stylesheet">
	<link href="/static/plugins/sweetalert2/sweetalert2.min.css" rel="stylesheet">
</head>
<body class="white-bg">
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<div class="ibox-content mailbox-content">
					<div class="file-manager">
						<a class="btn btn-block btn-primary compose-mail"
						   href="javascript:void(0);">自动生成</a>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-9">
			<div class="ibox float-e-margins">
				<div class="mail-box-header">
					<div class="pull-right tooltip-demo">
						<button type="button" onclick="javascrtpt:window.location.href='https://www.bangmangma.com/admin/blog/blogTable.jsp'"
								class="btn btn-white btn-sm" data-toggle="tooltip"
								data-placement="top" title="列表">
							</i> 列表
						</button>
						<button id="add_blog" type="button" class="btn btn-white btn-sm"
								data-toggle="tooltip" data-placement="top" title="提交">
							<i class="fa fa-pencil"></i> 提交
						</button>
					</div>
					<div class="mail-box">
						<div class="mail-body">
							<form class="form-horizontal" method="post" id="commentForm">
								<input type="hidden" value="" class="imagePath">
								<div class="form-group">
									<label class="col-sm-2 control-label">标题：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="title"
											   name="title" value="" required="" maxlength="50"
											   aria-required="true">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">ID：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="id"
											   name="title" value="0" required="" maxlength="50"
											   aria-required="true">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/admin/token.js"></script>
<!-- 全局js -->
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/plugins/sweetalert2/sweetalert2.min.js"></script>
<script src="/static/js/admin/blog/autoBlog.js"></script>
</body>
</html>

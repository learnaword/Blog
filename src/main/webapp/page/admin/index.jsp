<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>博客后台 - 主页</title>
	<link rel="shortcut icon" href="/images/favicon.ico">
	<link href="/upload/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="/upload/css/sweetalert2.min.css" rel="stylesheet">
	<link href="/upload/css/animate.css" rel="stylesheet">
	<link href="/static/css/admin/style.css" rel="stylesheet">
	<link href="/upload/css/fakeLoader.css" rel="stylesheet">
</head>
<body class="fixed-sidebar full-height-layout white-bg" style="overflow:hidden;">
<div id="wrapper">
	<!--左侧导航开始-->
	<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="nav-close">
			<i class="fa fa-times-circle"></i>
		</div>
		<div class="sidebar-collapse">
			<ul class="nav" id="side-menu">
				<li class="nav-header">
					<div class="dropdown mediaCard profile-element">
						<span class="mediaRecSpan">帮忙么网</span>
						<img style="height:50px;" src="${baseUrl}/images/image_.jpg"  alt="帮忙么网">
						<div class="mediaRecDiv">
							<span class="mediaRecSpan2">管理员</span>
						</div>
					</div>
				</li>
				<li>
					<a href="/page/admin/index_v1.jsp" class="J_menuItem">
					<i class="fa fa-home"></i>
					<span class="nav-label">首页</span></a>
				</li>
				<li><a href="#"><i class="fa fa-cog"></i>
					<span class="nav-label">系统管理</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li>
							<a class="J_menuItem" href="/page/admin/charts/eCharts.jsp">用户管理</a>
						</li>
						<li>
							<a class="J_menuItem" href="/page/admin/charts/eCharts.jsp">菜单管理</a>
						</li>
						<li>
							<a class="J_menuItem" href="/page/admin/charts/eCharts.jsp">敏感词管理</a>
						</li>
					</ul>
				</li>
				<li><a href="#"><i class="fa fa-inbox"></i>
					<span class="nav-label">基础设施</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li>
							<a class="J_menuItem" href="/page/admin/file/table.jsp">文件管理</a>
						</li>
						<li>
							<a class="J_menuItem" href="${baseUrl}/doc.html" >系统接口</a>
						</li>
						<li>
							<a class="J_menuItem" href="/page/admin/db/db.html" >数据库文档</a>
						</li>
                        <li>
                            <a class="J_menuItem" href="https://www.bangmangma.com/boot_admin" >JAVA监控</a>
                        </li>
						<li><a href="#">
							<span class="nav-label">定时任务</span><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li>
									<a class="J_menuItem" href="/page/admin/job/table.jsp">&nbsp;&nbsp;&nbsp;&nbsp;任务管理</a>
								</li>
								<li>
									<a class="J_menuItem" href="/page/admin/job/add.jsp">&nbsp;&nbsp;&nbsp;&nbsp;任务创建</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li><a href="#"><i class="fa fa-film"></i>
					<span class="nav-label">自媒体</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li>
							<a class="J_menuItem" href="/page/admin/media/table.jsp">博客管理</a>
						</li>
						<li>
							<a class="J_menuItem" href="/page/admin/media/add.jsp">写博客</a>
						</li>
					</ul>
				</li>
				<li><a href="#"><i class="fa fa-book"></i>
					<span class="nav-label">博客模块</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li>
							<a class="J_menuItem" href="/page/admin/blog/add.jsp">写博客</a>
						</li>
						<li>
							<a class="J_menuItem" href="/page/admin/blog/table.jsp">博客管理</a>
						</li>
						<li><a href="#">
							<span class="nav-label">自动生成</span><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li>
									<a class="J_menuItem" href="/page/admin/auto/config/table.jsp">&nbsp;&nbsp;&nbsp;&nbsp;配置管理</a>
								</li>
								<li>
									<a class="J_menuItem" href="/page/admin/auto/config/add.jsp">&nbsp;&nbsp;&nbsp;&nbsp;创建配置</a>
								</li>
								<li>
									<a class="J_menuItem" href="/page/admin/auto/sentence/table.jsp">&nbsp;&nbsp;&nbsp;&nbsp;句子管理</a>
								</li>
								<li>
									<a class="J_menuItem" href="/page/admin/auto/blog/write.jsp">&nbsp;&nbsp;&nbsp;&nbsp;生成博客</a>
								</li>
							</ul>
						</li>
						<li><a href="#">
							<span class="nav-label">专区模块</span>
							<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li>
									<a class="J_menuItem" href="/page/admin/soft/add.jsp">&nbsp;&nbsp;&nbsp;&nbsp;添加专区</a>
								</li>
								<li>
									<a class="J_menuItem" href="/page/admin/soft/table.jsp">&nbsp;&nbsp;&nbsp;&nbsp;专区管理</a>
								</li>
							</ul>
						</li>
						<li><a href="#">
							<span class="nav-label">推荐管理</span>
							<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li>
									<a class="J_menuItem" href="/page/admin/recommend/add.jsp">&nbsp;&nbsp;&nbsp;&nbsp;添加推荐</a>
								</li>
								<li>
									<a class="J_menuItem" href="/page/admin/recommend/table.jsp">&nbsp;&nbsp;&nbsp;&nbsp;推荐管理</a>
								</li>
							</ul>
						</li>
						<li>
							<a class="J_menuItem" href="/page/admin/type/table.jsp">类型管理</a>
						</li>
					</ul>
				</li>
				<li><a href="#"><i class="fa fa-line-chart"></i>

					<span class="nav-label">数据统计</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="#">
							<span class="nav-label">平台统计</span><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li>
									<a class="J_menuItem" href="/page/admin/data/charts/eCharts.jsp">&nbsp;&nbsp;&nbsp;&nbsp;图表统计</a>
								</li>
								<li>
									<a class="J_menuItem" href="/page/admin/data/button/table.jsp">&nbsp;&nbsp;&nbsp;&nbsp;按钮事件统计</a>
								</li>
							</ul>
						</li>
						<li>
							<a  href="https://tongji.baidu.com/web/10000123625/homepage/index" target="_blank">百度统计</a>
						</li>
					</ul>
				</li>
				<li><a class="J_menuItem" href="/page/admin/log/log.jsp">
					<i class="fa fa-file-text-o"></i>
					<span class="nav-label">操作日志</span></a>
				 </li>
				<li><a href="/" target= "_blank"><i class="fa fa-desktop"></i> <span
						class="nav-label">前台页面 </span></a>
				</li>
			</ul>
		</div>
	</nav>
	<!--左侧导航结束-->
	<!--右侧部分开始-->
	<div id="page-wrapper" class="white-bg dashbard-1">
		<div class="row border-bottom">
			<nav class="navbar navbar-static-top" role="navigation"
				 style="margin-bottom: 0">
				<div class="navbar-header">
					<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
					   href="javascript:void(0)"><i class="fa fa-bars"></i> </a>
				</div>
				<ul class="nav navbar-top-links navbar-right">
					<li class="dropdown hidden-xs"><a
							class="right-sidebar-toggle" aria-expanded="false"> <i
							class="fa fa-tasks"></i> 主题
					</a></li>
				</ul>
			</nav>
		</div>
		<div class="row content-tabs">
			<button class="roll-nav roll-left J_tabLeft">
				<i class="fa fa-backward"></i>
			</button>
			<nav class="page-tabs J_menuTabs">
				<div class="page-tabs-content">
					<a href="javascript:void(0)" class="active J_menuTab"
					   data-id="/page/admin/index_v1.jsp">主页</a>
				</div>
			</nav>
			<button class="roll-nav roll-right J_tabRight">
				<i class="fa fa-forward"></i>
			</button>
			<div class="btn-group roll-nav roll-right">
				<button class="dropdown J_tabClose" data-toggle="dropdown">
					关闭操作<span class="caret"></span>
				</button>
				<ul role="menu" class="dropdown-menu dropdown-menu-right">
					<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
					<li class="divider"></li>
					<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
					<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
				</ul>
			</div>
			<a href="/page/admin/logout" class="roll-nav roll-right J_tabExit"><i
					class="fa fa fa-sign-out"></i> 退出</a>
		</div>
		<div class="row J_mainContent" id="content-main">
			<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="/page/admin/index_v1.jsp"
					frameborder="0" data-id="/page/admin/index_v1.jsp" seamless></iframe>
		</div>
		<div class="footer">
			<div class="pull-right">
				&copy; 2018 <a href="http://www.bangmangma.com/" target="_blank">house
				blog</a>
			</div>
		</div>
	</div>
	<!--右侧部分结束-->
	<!--右侧边栏开始-->
	<div id="right-sidebar">
		<div class="sidebar-container">
			<ul class="nav nav-tabs navs-3">
				<li class="active"><a data-toggle="tab" href="#tab-1"> <i
						class="fa fa-gear"></i> 主题
				</a></li>
			</ul>
			<div class="tab-content">
				<div id="tab-1" class="tab-pane active">
					<div class="sidebar-title">
						<h3>
							<i class="fa fa-comments-o"></i> 主题设置
						</h3>
						<small><i class="fa fa-tim"></i>
							你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
					</div>
					<div class="skin-setttings">
						<div class="title">主题设置</div>
						<div class="setings-item">
							<span>收起左侧菜单</span>
							<div class="switch">
								<div class="onoffswitch">
									<input type="checkbox" name="collapsemenu"
										   class="onoffswitch-checkbox" id="collapsemenu"> <label
										class="onoffswitch-label" for="collapsemenu"> <span
										class="onoffswitch-inner"></span> <span
										class="onoffswitch-switch"></span>
								</label>
								</div>
							</div>
						</div>
						<div class="setings-item">
							<span>固定顶部</span>

							<div class="switch">
								<div class="onoffswitch">
									<input type="checkbox" name="fixednavbar"
										   class="onoffswitch-checkbox" id="fixednavbar"> <label
										class="onoffswitch-label" for="fixednavbar"> <span
										class="onoffswitch-inner"></span> <span
										class="onoffswitch-switch"></span>
								</label>
								</div>
							</div>
						</div>
						<div class="setings-item">
							<span> 固定宽度 </span>
							<div class="switch">
								<div class="onoffswitch">
									<input type="checkbox" name="boxedlayout"
										   class="onoffswitch-checkbox" id="boxedlayout"> <label
										class="onoffswitch-label" for="boxedlayout"> <span
										class="onoffswitch-inner"></span> <span
										class="onoffswitch-switch"></span>
								</label>
								</div>
							</div>
						</div>
						<div class="title">皮肤选择</div>
						<div class="setings-item default-skin nb">
								<span class="skin-name "> <a href="javascript:void(0)" class="s-skin-0">
										默认皮肤 </a>
								</span>
						</div>
						<div class="setings-item blue-skin nb">
								<span class="skin-name "> <a href="javascript:void(0)" class="s-skin-1">
										蓝色主题 </a>
								</span>
						</div>
						<div class="setings-item yellow-skin nb">
								<span class="skin-name "> <a href="javascript:void(0)" class="s-skin-3">
										黄色/紫色主题 </a>
								</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<!-- 全局js -->
<script src="https://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>
<script src="/upload/js/bootstrap.min.js"></script>
<script src="/upload/js/jquery.metisMenu.js"></script>
<script src="/upload/js/jquery.slimscroll.min.js"></script>
<script src="/upload/js/sweetalert2.min.js"></script>
<script src="/upload/js/axios.min.js"></script>
<!-- 自定义js -->
<script src="/upload/js/admin/token.js"></script>
<script src="/upload/js/hplus.js"></script>
<script src="/upload/js/contabs.js"></script>
<script src="/upload/js/fakeLoader.min.js"></script>
</body>
</html>

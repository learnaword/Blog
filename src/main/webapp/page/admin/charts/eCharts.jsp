<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>数据图表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="/images/favicon.ico">
    <link href="/static/css/gruvbox-light.css" rel="stylesheet">
    <link href="/upload/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/admin/font-awesome.css" rel="stylesheet">
    <link href="/static/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="/static/css/admin/animate.css" rel="stylesheet">
    <link href="/upload/css/style.css" rel="stylesheet">
    <link href="/upload/css/sweetalert2.min.css" rel="stylesheet">
    <link href="/static/css/plugins/datapicker/datepicker3.css"rel="stylesheet">
    <link href="/static/css/page/index.min.css" rel="stylesheet">
    <link href="/upload/css/base.css" rel="stylesheet">
    <link href="/static/css/admin/fakeLoader.css" rel="stylesheet">
    <style type="text/css">
        .input-group-addon{
            color: #c23531;
        }
    </style>
</head>
<body class="gray-bg" style="opacity:1">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-6" style="clear:both">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5> <i class="fa line-chart"></i> 用户操作记录</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="javascript:void(0)">选项1</a>
                            </li>
                            <li><a href="javascript:void(0)">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="echarts" id="echarts-line-userLogClick"></div>
                    <div class="col-sm-8 input-daterange input-group" style="margin: 20px auto 0px;" id="datepicker">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <input type="text" class="input-sm form-control" id="startUserLogClick" />
                        <span class="input-group-addon">到 </span>
                        <input type="text" class="input-sm form-control" id="endUserLogClick" />
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5> <i class="fa fa-bar-chart"></i> 发表量</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="javascript:void(0)">选项1</a>
                            </li>
                            <li><a href="javascript:void(0)">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="echarts" id="echarts-line-blog"></div>
                    <div class="col-sm-8 input-daterange input-group" style="margin: 20px auto 0px;" id="datepicker">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <input type="text" class="input-sm form-control" id="startBlog" />
                        <span class="input-group-addon">到 </span>
                        <input type="text" class="input-sm form-control" id="endBlog" />
                    </div>
                </div>
            </div>
        </div>


        <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-dialog" style="width:702px">
                <div class="modal-content animated fadeInUp">
                    <button type="button" class="close" style="margin-right:7px" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <div class="infos" style="margin:0px;">
                        <div class="newsview" style="padding-top:0px;">
                            <h3 class="news_title"></h3>
                            <div class="news_author">
                                <span class="au01"></span><span class="au02"></span><span
                                    class="au03">共<b></b>人围观
										</span>
                            </div>
                            <div class="tags">

                            </div>
                            <div class="news_about">
                                <strong>简介</strong><span class="news_intr">
									</span>
                            </div>
                            <div class="news_infos">

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>
        <a style="display:none" class=" btn-sm btn-info" id="btn" href="#" data-toggle="modal" data-target="#myModal">查看</a>
    </div>
</div>

<!-- 全局js -->
<script src="https://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>
<script src="/static/js/common/highlight.pack.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<script src="/upload/js/bootstrap.min.js"></script>
<script src="/static/js/plugins/peity/jquery.peity.min.js"></script>
<script src="/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<!-- 自定义js -->
<script src="/static/js/common/content.js"></script>
<script src="/upload/js/sweetalert2.min.js"></script>
<script src="/static/js/plugins/echarts/echarts.min.js"></script>
<script src="/static/js/common/fakeLoader.min.js"></script>
<script src="/upload/js/admin/token.js"></script>
<script src="/static/js/admin/charts/charts.js"></script>

</body>

</html>

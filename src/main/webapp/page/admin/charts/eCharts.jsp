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
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/static/css/plugins/common/gruvbox-light.css" rel="stylesheet">
    <link href="/static/css/admin/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/admin/font-awesome.css" rel="stylesheet">
    <link href="/static/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="/upload/css/sweetalert2.min.css" rel="stylesheet">
    <link href="/static/css/admin/style.css" rel="stylesheet">
    <link href="/upload/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="/static/css/plugins/datapicker/datepicker3.css"rel="stylesheet">
    <link href="/upload/css/base.css" rel="stylesheet">
    <style type="text/css">
        .input-group-addon{
            color: #c23531;
        }
    </style>
</head>
<body class="white-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5> <i class="fa line-chart"></i> 访问量</h5>
                    <div class="ibox-tools ">
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
                    <div class="echarts" id="echarts-line-visit" ></div>
                    <div class="col-sm-8 input-daterange input-group" style="margin: 20px auto 0px;" id="datepicker">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <input type="text" class="input-sm form-control" id="startTime" />
                        <span class="input-group-addon">到 </span> <input type="text"
                                                                          class="input-sm form-control" id="endTime" />
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

</div>

<!-- 全局js -->
<script src="/static/js/common/jquery.min.js"></script>
<script src="/static/js/common/bootstrap.min.js"></script>
<script src="/static/js/plugins/peity/jquery.peity.min.js"></script>
<script src="/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="/upload/js/sweetalert2.min.js"></script>
<!-- 自定义js -->
<script src="/static/js/common/content.js"></script>
<script src="/upload/js/admin/token.js"></script>
<script src="/upload/js/axios.min.js"></script>
<script src="/static/js/plugins/echarts/echarts.min.js"></script>
<script type="module" src="/static/js/admin/charts/charts.js"></script>
</body>
</html>

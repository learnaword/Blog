<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>封面图库</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <link rel="shortcut icon" href="/static/images/favicon.ico">
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <link href="/css/font-awesome.css" rel="stylesheet">
  <link href="/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
  <link href="/css/animate.css" rel="stylesheet">
  <link href="/css/style.css" rel="stylesheet">
  <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
  <link rel="stylesheet" href="/css/plugins/webuploader/webuploader.css">
  <link rel="stylesheet" href="/css/plugins/webuploader/webuploader-demo.css">
  <link href="/css/fakeLoader.css" rel="stylesheet">
  <style>
    .del{
      color:#fff00000;
      position:relative;
      line-height:25px;
      width:254px;
      height:25px;
      display:block;
      bottom: 15px;
    }

    .picc:hover .del {
      color: #fff;
      transition:all .5s;
      -webkit-transition:all .5s;
      -ms-transition:all .5s;
      -webkit-transform:translateY(-20px);
      -moz-transform:translateY(-20px);
      -ms-transform:translateY(-20px);
      -o-transform:translateY(-20px);
      transform:translateY(-20px);
      background:#8c848473
    }
  </style>

</head>
<body class="white-bg" style="opacity:0">
<div id="fakeloader"></div>
<div class="wrapper wrapper-content">
  <div class="row">
    <div class="col-sm-12">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>图库上传</h5>
          <div class="ibox-tools">
            <a class="collapse-link"> <i class="fa fa-chevron-up"></i>
            </a> <a class="dropdown-toggle" data-toggle="dropdown"
                    href="#"> <i class="fa fa-wrench"></i>
          </a>
            <ul class="dropdown-menu dropdown-user">
              <li><a href="#">选项1</a></li>
              <li><a href="#">选项2</a></li>
            </ul>
            <a class="close-link"> <i class="fa fa-times"></i>
            </a>
          </div>
        </div>
        <div class="ibox-content">
          <div class="page-container">
            <div id="uploader" class="wu-example">
              <div class="queueList">
                <div id="dndArea" class="placeholder">
                  <div id="filePicker"></div>
                  <p>或将照片拖到这里，单次最多可选300张</p>
                </div>
              </div>
              <div class="statusBar" style="display:none;">
                <div class="progress">
                  <span class="text">0%</span> <span
                        class="percentage"></span>
                </div>
                <div class="info"></div>
                <div class="btns">
                  <div id="filePicker2"></div>
                  <div class="uploadBtn">开始上传</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


  <div class="row">
    <div class="col-sm-12">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>
            服务器图库 <small>https://www.bangmangma.com/</small>
          </h5>
          <div class="ibox-tools">
            <a class="collapse-link"> <i class="fa fa-chevron-up"></i>
            </a> <a class="dropdown-toggle" data-toggle="dropdown"
                    href="#"> <i class="fa fa-wrench"></i>
          </a>
            <ul class="dropdown-menu dropdown-user">
              <li><a href="#">选项1</a></li>
              <li><a href="#">选项2</a></li>
            </ul>
            <a class="close-link"> <i class="fa fa-times"></i>
            </a>
          </div>
        </div>
        <div class="ibox-content pics">
          <div class="piccs"></div>
          <div style="clear: both;"></div>
        </div>
      </div>
    </div>

  </div>
</div>

<!-- 全局js -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/plugins/peity/jquery.peity.min.js"></script>
<script src="/js/plugins/fancybox/jquery.fancybox.js"></script>
<script src="/js/fakeLoader.min.js"></script>
<!-- 自定义js -->
<script src="/js/content.js"></script>
<script src="/js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- Web Uploader -->
<script type="text/javascript">
  // 添加全局站点信息
  var BASE_URL = '/js/plugins/webuploader';
</script>
<script src="/js/plugins/webuploader/webuploader.min.js"></script>
<script src="/js/plugins/webuploader/webuploader-demo.js"></script>
<script src="/js/admin/pics/pics.js"></script>
</body>
</html>

/**
 *
 */
var globalCount = 0;
$(".top").click(function() {
  $('body,html').animate({
    scrollTop : 0
  }, 1000);
  return false;
});

var isEnd = false;
var width = window.innerWidth || document.documentElement.clientWidth;
if (width < 660) {
  var pagenav = '<p style="text-align:center;margin:-5px auto 20px;"><a href="javascript:void(0);" onclick="initBlogByClickMore()"><i class="fa fa-arrow-down"></i> 加载更多</a></p>';
  $(".pageMin").html(pagenav);
//$(".top").css("display", "none");
}
$(window).scroll(
  function() {
    if (isEnd == true) {
      return;
    }
    if ($(document).scrollTop() > 2200 && width > 700) {
      $(".guanzhu").css("display", "block");
    } else {
      $(".guanzhu").css("display", "none");
    }
  });

$(document).ready(function() {
  initAllLinks();
});

//初始化每个类别的前N
var initBlogByAllTypeBlog = function() {

  $.ajax({
    url : 'selectBlogByAllType',
    type : 'get',
    dataType : 'json',
    success : function(data) {
      var likeBlog = '';
      var data = data.blogMap;
      var tab_button = "";
      var newsitem = "";
      var indexTab = 0;
      for (var type in data) {
        if (indexTab == 0) {
          tab_button += "<li class='newscurrent'>" + type + "</li>"
        } else {
          tab_button += "<li>" + type + "</li>"
        }
        indexTab++;
      }
      $(".tab_buttons ul").html(tab_button);
      var index = 0;
      for (var type in data) {
        var newspic = "";
        var newslist = "";
        if (index == 0) {
          newsitem += "<div class='newsitem' style='display: block;'><div class='newspic'><ul>";
        } else {
          newsitem += "<div class='newsitem' style='display: none;'><div class='newspic'><ul>";
        }
        index++;
        newslist += "<ul class='newslist'>"
        for (var i = 0; i < data[type].length; i++) {
          var id = data[type][i].id;
          if (i < 2) {
            newspic += "<li><a href=find/" + id + ".html target='_blank'><img src=" + data[type][i].images + "> <span>" + data[type][i].title + "</span></a></li>";
          }
          if (i >= 0) {
            newslist += "<li><i></i><a href=find/" + id + ".html target='_blank'>" + data[type][i].title + "<p>" + data[type][i].introduction + "</p></a></li>";
          }
        }
        newspic += "</ul></div>"
        newsitem += newspic;
        newsitem += newslist;
        newsitem += "</ul></div>"
      }
      $('.newstab').html(newsitem);
    },
    error : function() {
      layer.msg('请求太快，请稍后再试！', {
        icon : 5
      });
    }
  });
};



var initAllLinks = function() {

  $.ajax({
    url : 'selectAllLinks',
    type : 'get',
    data : "",
    dataType : 'json',
    success : function(data) {
      var linksAll = '';
      var data = data.linksList;
      var time = '';
      for (var i = 0; i < data.length; i++) {
        time = i * 0.05;
        linksAll += '<li style="animation-delay:0.'
          + i
          + 's;float:left;margin: 0 1% 10px 0;padding:3px;" class="animated fadeIn"><a href="'
          + data[i].link
          + '" target= "_blank" onclick="clickNum('
          + data[i].id + ')">' + data[i].name
          + '</a></li>';
      }
      // 初始化数据
      $(".link").find("ul").html(linksAll);
      time = time + 0.1;
      var msg = '<h5 style="animation-delay:' + time + 's" class="animated fadeIn" title="QQ:849673404">注：添加友链,请点击&nbsp;&nbsp;&nbsp;<a class="applyLinks" onclick="applyLinks()" href="javascript:void(0);" style="font-size:13px;color:#f8ac59">申请友链</a></h5>';
      $(".msg").find("a").html(msg);
    },
    error : function() {
      layer.msg('请求太快，请稍后再试！', {
        icon : 5
      });
    }
  });
}

var applyLinks = function() {
  swal({
    title : '互换友链',
    text : '注意：请在您的网站友链处添加本站链接后再行申请！！手机兼职-https://www.bangmangma.com！！！添加格式如下：名称&网站首页地址',
    type : 'input',
    showCancelButton : true,
    confirmButtonColor : "#1c84c6",
    confirmButtonText : "提交",
    closeOnConfirm : false
  }, function() {
    //swal("删除成功！", "您已经永久删除了这条信息。", "success");
    checkLinks();
  });
}

var checkLinks = function() {
  var inputLink = new Array();
  inputLink = $("fieldset").find("input").val().split("&");
  var title = '';
  var text = '';
  var type = '';
  if (inputLink.length != 2) {
    title = '格式错误',
    text = '请检查格式是否正确',
    type = 'error'
  } else {
    title = '请核对信息',
    text = '名称：' + inputLink[0].replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;") + "   。" + '链接：' + inputLink[1].replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
    type = 'warning'
  }
  swal({
    title : title,
    text : text,
    type : type,
    showCancelButton : true,
    confirmButtonColor : "#1c84c6",
    confirmButtonText : "确定",
    closeOnConfirm : false
  }, function() {
    if (type == 'warning') {
      addLinks(inputLink[0], inputLink[1]);
    }
  });
};
var addLinks = function(name, link) {
  var params = {
    name : name,
    link : link,
    sort : 0,
    isapply : -1,
    prarm : '有新伙伴申请友链啦！',
  };
  $.ajax({
    url : 'addLinks',
    type : 'post',
    data : params,
    dataType : 'json',
    success : function(data) {
      if (data.status == 200) {
        swal("申请成功", "等待管理审核", "success");
      }
    },
    error : function() {
      swal("申请失败", "请检查格式是否正确", "error");
    }
  });
}


//更新链接点击次数
var clickNum = function(id) {
  var params = {
    id : id,
  };
  $.ajax({
    url : 'selectLinksById',
    type : 'post',
    data : params,
    dataType : 'json',
    success : function(data) {},
    error : function() {}
  });
}

//格式化时间
function Format(datetime, fmt) {
  if (parseInt(datetime) == datetime) {
    if (datetime.length == 10) {
      datetime = parseInt(datetime) * 1000;
    } else if (datetime.length == 13) {
      datetime = parseInt(datetime);
    }
  }
  datetime = new Date(datetime);
  var o = {
    "M+" : datetime.getMonth() + 1, //月份   
    "d+" : datetime.getDate(), //日   
    "h+" : datetime.getHours(), //小时   
    "m+" : datetime.getMinutes(), //分   
    "s+" : datetime.getSeconds(), //秒   
    "q+" : Math.floor((datetime.getMonth() + 3) / 3), //季度   
    "S" : datetime.getMilliseconds()
  //毫秒   
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "")
      .substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(RegExp.$1,
        (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
          .substr(("" + o[k]).length)));
  return fmt;
}

var globalCount = 0;
$(".top").click(function () {
    $('body,html').animate({
        scrollTop: 0
    }, 1000);
    return false;
});

$(function () {
    //查找h1-h6
    var i = 1;
    var navToc = "";
    var navToc = "";
    $(".news_infos :header").each(function () {
        navToc += '<li class="nav-item nav-level-4" id="#nav1_' + i + '"><a class="nav-link" ><span class="nav-number">' + i + '.</span> <span class="nav-text">' + $(this).text() + '</span></a></li>';
        $("#nav1_" + i).css({
            'border-left': '5px solid #FF926F',
            'background-color': ' #f6f6f6',
            padding: '7px 20px',
            margin: '12px 0'
        });
        i++;
    });
    $(".nav1").html(navToc);
});
var count = 1;
var width = window.innerWidth || document.documentElement.clientWidth;
(function () {
    var appid = 'cytzg9rLH';
    var conf = 'prod_230eb23e872ad7a4302e5802e6f91bf9';
    if (width < 960) {
    } else {
        var loadJs = function (d, a) {
            var c = document.getElementsByTagName("head")[0] || document.head || document.documentElement;
            var b = document.createElement("script");
            b.setAttribute("type", "text/javascript");
            b.setAttribute("charset", "UTF-8");
            b.setAttribute("src", d);
            if (typeof a === "function") {
                if (window.attachEvent) {
                    b.onreadystatechange = function () {
                        var e = b.readyState;
                        if (e === "loaded" || e === "complete") {
                            b.onreadystatechange = null;
                            a()
                        }
                    }
                } else {
                    b.onload = a
                }
            }
            c.appendChild(b)
        };
    }
})();

if (width < 700) {
    $(".tool-box").css("display", "none");
}
$(window).scroll(function () {
    if ($(document).scrollTop() > 20 && count == 1) {
        $(".dj").css("display", "block");
        count++;
    }
    if ($(document).scrollTop() > 1200 && width > 700) {
        $(".top").addClass('cd-is-visible fadeIn');
        $(".fixed-menu-list").css("display", "block");
    } else {
        $(".top").removeClass('cd-is-visible fadeOut');
        $(".fixed-menu-list").css("display", "none");
    }
});


$(document).ready(function () {
    if ($(".newsview h1").text() != "404") {
        FormatBlogTime();
    }
    setTimeout(function () {
        $(".ds").css("opacity", "1");
    }, 1000);
    var topId = $(".nav-item");
        $(".dj").css("display", "block");

    /*获取目录点击区域*/
    topId.click(function () {
        var topId = $(this).attr("id");
        $('body,html').animate({
            scrollTop: $(topId).offset().top
        }, 800);
        return false;
    });
});

var selectPrevBlog = function () {
    var id = $(".id").val();
    var params = {
        id: id,
        soft_id: $(".softId").val(),
    };
    $.ajax({
        url: '../selectPrevBlog',
        type: 'get',
        data: params,
        dataType: 'json',
        success: function (data) {
            var preTitle = "";
            if (data.status == 200) {
                var id = data.blog.id;
                preTitle = '<a href="../find/' + id + '.html">' + data.blog.title + '</a>';
            } else {
                preTitle = '<span>无</span>';
            }
            $(".pre").html(preTitle);
        },
        error: function () {
            layer.msg('加载的太快啦', {
                icon: 2
            });
        }
    });

};

var selectNextBlog = function () {
    var vid = $(".id").val();
    var id = parseInt(vid);
    var params = {
        id: id,
        soft_id: $(".softId").val(),
    };
    $.ajax({
        url: '../selectNextBlog',
        type: 'get',
        data: params,
        dataType: 'json',
        success: function (data) {
            var nextTitle = '';
            if (data.status == 200) {
                var sid = data.blog.id;
                nextTitle = '<a href="../find/' + sid + '.html">' + data.blog.title + '</a>';
            } else {
                nextTitle = '<span>无</span>';
            }
            $(".next2").html(nextTitle);
        },
        error: function () {
            layer.msg('加载的太快啦', {
                icon: 2
            });
        }
    });

};

//初始化点击排行
var initBlogByClick = function () {
    //设置参数
    var params = {
        pageSize: 3,
        page: 1,
        sort: "clickNum", //按点击量排序,默认按时间
    };
    $.ajax({
        url: '../selectGroupLikeBlogListByPage',
        type: 'get',
        data: params,
        dataType: 'json',
        success: function (data) {
            var clickBlog = '';
            var data = data.blogList;
            var time = '';
            for (var i = 0; i < data.length; i++) {

                var id = data[i].id;
                time = i * 0.05;
                clickBlog += '<li style="animation-delay:0.' + i + 's" class="animated fadeIn"><b><a href="../find/' + id + '.html">'
                    + data[i].title
                    + '</a></b><p><a href="../find/' + id + '.html"></a><span>'
                    + data[i].introduction + '</span></p></li>'
            }
            // 初始化数据
            $(".paihang").find(".click").html(clickBlog);
        },
        error: function () {
            layer.msg('加载的太快啦', {
                icon: 2
            });
        }
    });
};

function FormatBlogTime(){
    datetime = $(".createTime").val();
    dateTime = Format(datetime, "yyyy-MM-dd");
    $(".au02").html(dateTime);
}

//格式化时间
function Format(datetime, fmt) {
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime);
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


//格式化时间
function Format(datetime, fmt) {
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime);
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

var initBlogType = function() {
    //查询出文章类别
    //设置参数，表示查询所有的类别
    var params = {
        "data" : "all"
    };
    $.ajax({
        url : '/selectBlogType',
        type : 'post',
        data : params,
        dataType : 'json',
        success : function(data) {
            var typeName = '';
            for (var i = 0; i < data.length; i++) {
                var time = i * 0.03;
                // <li style="animation-delay:'+time+'s" class="animated fadeIn" style="animation-delay:'+time+'s" class="animated fadeIn"><a style="padding: 5px;margin-right: 3px;border: none; background-color: #f1f1f1;" onclick="searchType('+data[i].id+',\''+data[i].typename+'\')" href="javascript:void(0);"> <i class="fa fa-tag"></i>'+data[i].typename+'</a></li>
                typeName += '<a style="animation-delay:' + time + 's" class="animated fadeIn" onclick="searchType(' + data[i].id + ',\'' + data[i].typename + '\')" href="javascript:void(0);">' + data[i].typename + '</a> '
            }
            var length = '';
            var keyTitle = '';
            if (data.length > 5) {
                length = 5;
            } else {
                length = data.length;
            }
            for (var i = 0; i < length; i++) {
                keyTitle += '<a href="javascript:void(0);" onclick="searchType(' + data[i].id + ',\'' + data[i].typename + '\')">' + data[i].typename + '</a>'
            }

            // 初始化数据
            $(".cloud").find('ul').html(typeName);
            $(".tagTitle").html(keyTitle);
        },
        error : function() {
            layer.msg('请求太快，请稍后再试！', {
                icon : 5
            });
        }
    });
}

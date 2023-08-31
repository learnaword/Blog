var globalCount = 0;

$(document).ready(function() {
    selectTempById();
});

var selectTempById = function() {
    var url = window.location.href;
    var url_param = url.split("=")[1];
    //编辑博客
    var param = {
        id : url_param
    }
    $.ajax({
        url : '../selectTempById',
        type : 'post',
        data : param,
        dataType : 'json',
        success : function(data) {
            //查询成功
            if (data.status == 200) {
                $("#title").val(data.temp.title);
                $("#content").val(data.temp.content);
                $("#replace").val(data.temp.content);
            }
            selectBlog();
        },
        error : function() {
            swal("查询错误", "请重试操作", "error");
        }
    });
}

//初始化表格数据
var selectBlog = function() {
    $('#allBlog').bootstrapTable({
        method : 'post',
        url : "../selectGroupLikeBlogTempListByPage",
        dataType : "json",
        striped : false, //使表格带有条纹
        pagination : true, //在表格底部显示分页工具栏
        pageSize : 10,
        pageNumber : 1,
        sortStable : true,
        sortable : true,
        pageList : [ 10, 20, 50 ],
        idField : "id", //标识哪个字段为id主键
        showToggle : false, //名片格式
        cardView : false, //设置为True时显示名片（card）布局
        showColumns : true, //显示隐藏列
        showRefresh : true, //显示刷新按钮
        //singleSelect: true,//复选框只能选择一条记录
        search : true, //是否显示搜索框
        searchOnEnterKey : true, //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
        //clickToSelect: true,//点击行即可选中单选/复选框
        queryParams : queryParams, //参数
        //showFullscreen:true,  //全屏按钮
        //queryParamsType: "limit", //查询参数组织方式
        sidePagination : "server", //服务端处理分页
        silent : true, //刷新事件必须设置
        searchTimeOut : 500, //设置搜索超时时间
        toolbarAlign : 'left', //工具栏对齐方式
        buttonsAlign : 'right', //按钮对齐方式
        toolbar : '#toolbar', //指定工作栏
        searchAlign : 'right',
        // singleSelect : true,
        contentType : "application/x-www-form-urlencoded",
        formatLoadingMessage : function() {
            return "请稍等，正在加载中...";
        },
        formatNoMatches : function() { //没有匹配的结果
            return "无符合条件的记录";
        },
        responseHandler : function(res) {
            return {
                "total" : res.pageInfo.total, //总页数
                "rows" : res.pageInfo.list //数据
            };
        },
        columns : [
            {
                checkbox : true,
                width : '3%',
                align : 'center',
                valign : 'middle',
            },
            {
                title : '序号',
                align : 'center',
                valign : 'middle',
                width : '4%',
                formatter : function(value, row, index) {
                    var index1 = index + 1;
                    var id = '<span title="ID:' + row.id + '">' + index1 + '</span>';
                    return id;
                }
            },
            {
                title : '标题',
                field : 'title',
                align : 'center',
                valign : 'middle',
                width : '13%',
                cellStyle : formatTableUnit,
                formatter : operateOpinionFormatter
            },
            {
                title : '类别',
                field : 'type.typename',
                align : 'center',
                width : '8%',
            },
            {
                title : '关键字',
                field : 'keyword',
                align : 'center',
                width : '13%',
                cellStyle : formatTableUnit,
                formatter : operateOpinionFormatter
            },

            {
                title : '浏览量',
                field : 'clicknum',
                align : 'center',
                width : '5%',
            },

            {
                title : '博主推荐',
                field : 'isrecommend',
                align : 'center',
                width : '3%',
                formatter : function(value, row, index) {
                    if (row.isrecommend == 0) {
                        return '<button class="btn-xs">否</button> ';
                    } else if (row.isrecommend == 1) {
                        return '<button class="btn-xs btn-success">是</button>';
                    }

                }
            },
            {
                title : '状态',
                field : 'status',
                align : 'center',
                width : '4%',
                formatter : function(value, row, index) {
                    if (row.status == -1) {
                        return '<button class="btn-xs btn-primary">草稿</button>';
                    } else if (row.status == 1) {
                        return '<button class="btn-xs btn-info">发布</button>';
                    } else if (row.status == 2) {
                        return '<button class="btn-xs btn-danger">垃圾</button>';
                    }
                }
            },
            {
                title : '发表时间',
                field : 'createTime',
                align : 'center',
                width : '17%',
                formatter : function(value, row, index) {
                    return Format(row.createTime, "yyyy-MM-dd hh:mm:ss");
                }
            },
            {
                title : '操作',
                field : 'id',
                align : 'center',
                width : '15%',
                formatter : function(value, row, index) {
                    //查看
                    var a = '<a  class=" btn-sm btn-info" href="#" data-toggle="modal" data-target="#myModal" onclick="selectBlogById(\'' + row.id + '\')">查看</a> ';
                    //编辑
                    var b = '<a  class=" btn-sm btn-primary"  href="../blog/updateBlog.jsp?id=' + row.id + '" target="_blank"><i class="fa fa-edit" ></i> 编辑</a> ';
                    //从回收站还原
                    var c = '<a  class=" btn-sm btn-danger"  onclick="operation(' + row.id + ',\'还原\')"><i class="fa fa-share-square-o" ></i> 还原</a> ';
                    //取消推荐
                    var d = '<a  class=" btn-sm btn-primary"  onclick="operation(' + row.id + ',\'取推荐\')">取 推荐</a> ';
                    //从草稿还原
                    var e = '<a  class=" btn-sm btn-danger"  onclick="operation(' + row.id + ',\'发表\')"><i class="fa fa-share-square-o" ></i> 发表</a> ';
                    //取消置顶
                    var f = '<a  class=" btn-sm btn-success"  onclick="operation(' + row.id + ',\'取置顶\')">取 置顶</a> ';
                    if (row.status == 2) {
                        return a + c;
                    } else if (row.status == 1) {
                        if (row.isrecommend == 1) {
                            return a + d;
                        } else if (row.istop == 1) {
                            return a + f;
                        } else {
                            return a + b;
                        }
                    } else if (row.status == -1 || row.status == -2)
                        return a + e;
                }
            }
        ]
    });
    globalCount++;
    returnAllCount();
}

//传参数到后台
function queryParams(params) {
    return {
        pageSize : params.limit,
        page : (params.offset) / params.limit + 1,
        content : $("#content").val(),

    }
}

//设置 字数不超过宽度限制
var operateOpinionFormatter = function(value, row, index) {
    var title = "";
    var content = $(".form-control").val();
    var contentLow = $(".form-control").val().toLowerCase();
    var strStartIndex = '';
    var strEndIndex = '';
    if (value.search(content) != -1 || value.toLowerCase().search(contentLow) != -1) {
        var strs = new Array();
        strs = value.split("");
        strStartIndex = value.indexOf(content);
        strEndIndex = strStartIndex + content.length - 1;
        if (value.toLowerCase().search(contentLow) != -1) {
            strStartIndex = value.toLowerCase().indexOf(contentLow);
            strEndIndex = strStartIndex + contentLow.length - 1;
        }
        for (var i = 0; i < strs.length; i++) {
            if (i >= strStartIndex && i <= strEndIndex) {
                title += '<span style="color:#000;font-weight:bold;">' + strs[i] + '</span>';
            } else {
                title += '<span >' + strs[i] + '</span>';
            }
        }
    } else {
        title = value;
    }
    if (value.length > 15) {
        var num = strEndIndex - strStartIndex;
        var index = strStartIndex - 4;
        if (index < 0) {
            index = 0;
        }
        if (content == "") {
            return "<span title='" + value + "'>" + title.substring(0, 150) + "..." + "</span>";
        } else {
            return "<span title='" + value + "'>" + title.substring(15 * index, (15 * index) + (num + 1) * 51 + (9 - num) * 15) + "..." + "</span>";
        }
    } else {
        return "<span  title='" + value + "'>" + title.substring(0, title.length) + "</span>";
    }
}

var formatTableUnit = function(value, row, index) {
    return {
        css : {
            "overflow" : 'hidden',
            "text-overflow" : 'ellipsis',
            "white-space" : 'nowrap'
        }
    };
};

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
        "S" : datetime.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

var updateTemp = function() {
    var url = window.location.href;
    var url_param = url.split("=")[1];
    var params = {
        'id' : url_param,
        'type' : $("#type").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
        'title' : $("#title").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
        'content' : $("#content").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
        'replace' : $("#replace").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
        'limitNum' : $("#limitNum").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
    };
    $.ajax({
        url : '../updateTemp',
        type : 'post',
        data : params,
        dataType : 'json',
        success : function(data) {
            if (data.status == 200) {
                var title = "更新成功";
                var text = "博客已在前端展示";
                swal({
                    title : title,
                    text : text,
                    type : "success",
                    timer : 1500,
                });
                setTimeout(function() {
                    window.location.href = "../temp/tempTable.jsp";
                }, 1500);
            } else {
                swal("更新失败", data.msg, "error");
            }
        },
        error : function() {
            swal("更新错误", "请重新操作", "error");
        }
    });

}


//只有验证通过才能执行 添加
$("#add_temp").click(function() {
    updateTemp();
});

//查看博客内容
function selectBlogById(blogId) {
    var param = {
        id : blogId
    }
    $.ajax({
        url : '../selectBlogById',
        type : 'post',
        data : param,
        dataType : 'json',
        success : function(data) {
            //查询成功
            if (data.status == 200) {
                $(".newsview").find(".news_title").html(data.blog.title);
                $(".newsview").find(".au02").html(Format(data.blog.creatTime, "yyyy-MM-dd hh:mm:ss"));
                $(".au03").find('b').html(data.blog.clicknum);
                $(".news_about").find(".news_intr").html(data.blog.introduction);
                var keyword = '';
                $(".newsview").find(".tags").html("");
                if (data.blog.keyword != '' && data.blog.keyword != null) {
                    if (data.blog.keyword.search(';') != -1) {
                        var strs = new Array();
                        strs = data.blog.keyword.split(";");
                        for (var i = 0; i < strs.length && strs[i] != ''; i++) {
                            keyword += '<a href="#">' + strs[i] + '</a>';
                        }
                    } else {
                        keyword = '<a href="#">' + data.blog.keyword + '</a>';
                    }
                }
                $(".newsview").find(".tags").append(keyword);
                $(".newsview").find(".news_infos").html(data.blog.content);
                var update = '<a  class="J_menuItem btn btn-white" href="../blog/updateBlog.jsp?id=' + data.blog.id + '">编辑</a>';
                $(".modal-footer").find(".update").html(update);
            }
            $('pre').each(function(i, block) {
                hljs.highlightBlock(block);
            });
        },
        error : function() {
            swal("查询错误", "请重新操作", "error");
        }
    });
}

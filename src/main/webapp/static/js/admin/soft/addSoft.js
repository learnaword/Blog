var globalCount = 0;

$(document).ready(function() {
    initBlogType();
});

var returnAllCount = function() {
    if (globalCount == 2) {
        setTimeout(function() {
            $('#fakeloader').css('display', 'none');
        }, 500);
    }
}

var initBlogType = function() {
    //查询出文章类别
    //设置参数，表示查询所有的类别
    var params = {
        "data" : "all"
    };
    $.ajax({
        url : '../selectBlogType',
        type : 'post',
        data : params,
        dataType : 'json',
        success : function(data) {
            $(".form-horizontal").find(
                'select[name=typeName]')
                .html("");
            var typeName = '';
            var typeNameAndNum = '';
            var circle = new Array("text-navy",
                "text-danger",
                " text-info",
                "text-primary",
                "text-warning");
            var label = new Array(
                "label-primary",
                "label-danger",
                " label-info",
                "label-success",
                "label-warning");
            for (var i = 0; i < data.length; i++) {
                typeName += '<option value="' + data[i].id + '">'
                    + data[i].typename
                    + '</option>';
            }
            // 初始化数据
            $(".form-horizontal").find(
                'select[name=typeName]')
                .append(typeName);
            $(".category-list").html(
                typeNameAndNum);
        },
        error : function() {
            swal("初始化类别错误", "请重新操作", "error");
        }
    });
    globalCount++;
    returnAllCount();
}

//图片上传  
function sendFile(file, editor, $editable) {
    var filename = false;
    try {
        filename = file['name'];
    } catch (e) {
        filename = false;
    }
    if (!filename) {
        $(".note-alarm").remove();
    }

    //以上防止在图片在编辑器内拖拽引发第二次上传导致的提示错误
    data = new FormData();
    data.append("file", file);
    data.append("key", filename); //唯一性参数

    $.ajax({
        data : data,
        type : "POST",
        url : "../uploadBlogImages",
        dataType : "json",
        cache : false,
        contentType : false,
        processData : false,
        success : function(date) {
            if (date == '') {
                swal("上传失败", "请重试操作", "error");
            } else {
                var path = '图片地址  ' + date.path;
                swal("上传成功", path, "success");
            }
            editor.insertImage($editable, date.path);
        },
        error : function() {
            swal("上传错误", "请重试操作", "error");
        }
    });
}

//弹出模态框 选择图片
var selectImgPath = function(img) {
    $("#pic").modal('hide');
    var imgPath = '<img class="picPath animated fadeInRight"  style="width: 190px; height: 115px;" alt="封面" title="点击更换封面" src="' + img.src + '" />';
    $(".picPath").html(imgPath)
    $(".imagePath").val(img.src);
};

//查找服务器图库
var findPicList = function() {
    $.ajax({
        //此处使用的是自己封装的JAVA类
        url : "../getFileList",
        type : "POST",
        success : function(data) {
            if (data.status == 0) {
                swal("服务器图库为空", "请上传", "error");
            } else {
                var pics = '';
                for (var i = 0; i < data.fileList.length; i++) {
                    pics += '<a class="fancybox" href="javascript:void(0);"><img onclick="selectImgPath(this)" style="width: 190px; height: 115px;float:left;margin-right:3px;" alt="image" src="' + data.fileList[i] + '" /></a>'
                }
            }
            $(".picsList").html(pics);
        },
        error : function(e) {
            swal("获取图片错误", "请检查接口服务", "error");
        }
    });
};

var addBlog = function() {
    var params = {
        'title' : $("#title").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
        'introduction' : $("#introduction").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
        'type.id' : $("#typeName").val(),
        'images' : $(".imagePath").val(),
    };
    $.ajax({
        url : "../addSoft",
        type : "POST",
        data : params,
        dataType : 'json',
        success : function(data) {
            if (data.status == 200) {
                initBlogType();
                swal("发布成功", "博客已在前端展示", "success");
                $("#title").val("");
                $("#introduction").val("");
            } else {
                    swal("发布失败", "请重新操作", "error");
            }
        },
        error : function() {
                swal("发布错误", "请重新操作", "error");
        }
    });
};

//只有验证通过才能执行 添加
$("#add_soft").click(function() {
    addBlog();
});
;
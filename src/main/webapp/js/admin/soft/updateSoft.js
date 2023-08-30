var globalCount = 0;

$(document).ready(function() {
	var url = window.location.href;
	var url_param = url.split("=")[1];
	//编辑博客
	var param = {
		id : url_param
	}
	$.ajax({
		url : '../selectSoftById',
		type : 'post',
		data : param,
		dataType : 'json',
		success : function(data) {
			//查询成功
			if (data.status == 200) {
				$("#title").val(data.soft.title);
				$("#introduction").val(data.soft.introduction);
				var imgPath = '<img class="picPath animated fadeInRight"  style="width:100%" alt="封面" title="点击更换封面" src="' + data.soft.images + '" />';
				$(".imagePath").val(data.soft.images);
				$(".picPath").html(imgPath)
				var typeName = '';
				typeName += '<option style="color:#000;font-weight:bold;" value="' + data.soft.type.id + '" selected="selected">' + data.soft.type.typename + '</option>';
				$(".form-horizontal").find('select[name=typeName]').append(typeName);
			}
		},
		error : function() {
			swal("查询错误", "请重试操作", "error");
		}
	});

	var p = {
		client_id : 'cytzg9rLH',
		topic_source_id : url_param
	};
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
			var typeName = '';
			for (var i = 0; i < data.length; i++) {
				typeName += '<option value="' + data[i].id + '">' + data[i].typename + '</option>';
			}
			// 初始化数据
			$(".form-horizontal").find('select[name=typeName]').append(typeName);
		},
		error : function() {
			swal("加载类别错误", "请重试操作", "error");
		}
	});
	globalCount++;
	returnAllCount();
});

var returnAllCount = function() {
	if (globalCount == 1) {
		setTimeout(function() {
			$('#fakeloader').css('display', 'none');
		}, 500);
	}
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
	var imgPath = '<img class="picPath animated fadeInRight"  style="width:100%" alt="封面" title="点击更换封面" src="' + img.src + '" />';
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

var updateBlog = function() {
	var url = window.location.href;
	var url_param = url.split("=")[1];
	var params = {
		'id' : url_param,
		'title' : $("#title").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
		'introduction' : $("#introduction").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
		'type.id' : $("#typeName").val(),
		'images' : $(".imagePath").val(),
	};
	$.ajax({
		url : '../updateSoft',
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
					window.location.href = "../soft/softTable.jsp";
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

//只有验证通过才能执行 添加
$("#add_soft").click(function() {
		updateBlog();
});

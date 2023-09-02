$("#fakeloader").fakeLoader({
	timeToHide : 10000, //Time in milliseconds for fakeLoader disappear
	zIndex : 999, // Default zIndex
	spinner : "spinner6", //Options: 'spinner1', 'spinner2', 'spinner3', 'spinner4', 'spinner5', 'spinner6', 'spinner7'
	bgColor : "#fff", //Hex, RGB or RGBA colors
});
setTimeout(function() {
	$('body').css('opacity', '1');
	$('body').attr("class", "gray-bg") //添加样式
}, 100);

$(document).ready(function() {
	//初始化富文本
	$('#summernote').summernote({
      fontSizes: ['12', '14', '16','18','20','24', '36','500'],
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['fontname', ['fontname']],
            ['color', ['color']],
            ['height', ['height']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']],
            ['fontsize', ['fontsize']] ,// 鏈夊瓧鍙凤紝浣嗘槸榛樿娌℃湁鏄剧ず
        ],
		height : 400, //初始化默认高度
		minHeight : null, //最小高度
		maxHeight : null, //最大高度
		lang : 'zh-CN', //注意这里，若要设置语言，则需要引入该语言配置js
		placeholder : "请在这里写下您的内容",
		onImageUpload : function(files, editor, $editable) {
			sendFile(files[0], editor, $editable);
		}
	});
});

var returnAllCount = function() {
	if (globalCount == 2) {
		setTimeout(function() {
			$('#fakeloader').css('display', 'none');
		}, 500);
	}
}

var prevBlog = function() {
	$(".newsview").find(".news_title").html($("#title").val());
	$(".news_about").find(".news_intr").html($("#introduction").val());
	var keyword = '';
	var inputKeyword = $("#keyword").val();
	$(".newsview").find(".tags").html("");
	if (inputKeyword != '' && inputKeyword != null) {
		if (inputKeyword.search(';') != -1 || inputKeyword.search('；') != -1) {
			if (inputKeyword.search('；') != -1) {
				inputKeyword = inputKeyword.replace(/；/g, ";");
			}
			var strs = new Array();
			strs = inputKeyword.split(";");
			for (var i = 0; i < strs.length && strs[i] != ''; i++) {
				keyword += '<a href="javascript:void(0);">' + strs[i] + '</a>';
			}
		} else {
			keyword = '<a href="javascript:void(0);">' + inputKeyword + '</a>';
		}
	}
	$(".newsview").find(".tags").append(keyword);
	$(".newsview").find(".news_infos").html($("#summernote").code());
	var topNum = 1;
	$(".news_infos :header").each(function() {
		$(this).attr("id", 'nav1_' + topNum + '');
		topNum++;
	});
	var add = '<a  class="btn btn-white" href="javascript:void(0);" onclick="addBlog(1)">发表</a>';
	$(".modal-footer").find(".add").html(add);
	$('pre').each(function(i, block) {
		hljs.highlightBlock(block);
	});

};

var addBlog = function(status) {
	var prarm = '新增了一篇博客';
	if (status == -1) {
		prarm = '将博客放入<span class="text-navy">草稿箱</span>';
	}
	var params = {
		'title' : $("#title").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
		'content' : $(".news_infos").html(),
		'status' : status,
	};
	$.ajax({
		url : "../addMedia",
		type : "POST",
		data : params,
		dataType : 'json',
		success : function(data) {
			if (data.status == 200) {
				$("#myModal").modal('hide');
				if (status == 1) {
					swal("发布成功", "博客已在前端展示", "success");
				} else if (status == -1) {
					swal("放入草稿成功", "你可以前往草稿箱查看", "success");
				}
				$("#title").val("");
			} else {
				if (status == 1) {
					swal("发布失败", "请重新操作", "error");
				} else if (status == -1) {
					swal("放入草稿失败", "请重新操作", "error");
				}
			}
		},
		error : function() {
			if (status == 1) {
				swal("发布错误", "请重新操作", "error");
			} else if (status == -1) {
				swal("放入草稿错误", "请重新操作", "error");
			}
		}
	});
};

//存到草稿
$("#add_draft,#add_draft2").click(function() {
    if ($("#commentForm").valid()) {
       $("#prev2").click();
       addBlog(-1);
      }
});

//发表文章
$("#fb_draft3").click(function() {
    if ($("#commentForm").valid()) {
       $("#prev2").click();
       addBlog(1);
      }
});

//改为删除
$("#del_draft2").click(function() {
    if ($("#commentForm").valid()) {
       $("#prev2").click();
         addBlog(2);
      }
});

//只有验证通过才能执行 预览
$("#prev1").click(function() {
	if ($("#commentForm").valid()) {
		$("#prev2").click();
	}
});

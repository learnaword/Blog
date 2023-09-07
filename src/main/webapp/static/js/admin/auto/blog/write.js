import request from "../../../axios/axios-config.js";

$(document).ready(function() {
	renderAutoConfig();
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

	layui.use(['table','laytpl'], function () {
		var layer = layui.layer;
		var util = layui.util;
		var laytpl = layui.laytpl;
		var render = '';
		// 点击提交
		util.on('lay-on', {
			page: function () {
				$('#configBlog').removeClass("layui-hide")
				//通过id向后端传递数据
				layer.zIndex = 1000
				// 页面层
				layer.open({
					type: 1,
					area: ['620px', 'auto'],
					content: $('#configBlog'),
					fixed: true,
					zIndex: 200, // 重点 1 --- 初始设置当前最高层叠顺序，
					end: function () {
						//对弹出层的内容进行初始化。
						$('#configBlog').addClass("layui-hide");
						//刷新当前页面
					}
				});
			}
		})

		$('#submit_btn, #default_submit').on('click', function () {
			var formData = {
				title: $('input[name="title"]').val(),
				autoConfig: $('select[name="autoConfig"]').val(),
				content: $("#summernote").code(),
				images : $('#images').attr('src'),
			};
			request.post("/admin/auto-blog/create", formData).then(function(data){
				alert(data.data.code)
				if(data.data.code == "0"){
					Swal.fire({
						type: 'success', // 弹框类型
						title: '发布文章', //标题
						text: "发布成功！", //显示内容
						confirmButtonText: '确定',
					}).then(function(isConfirm) {
					})
				}else{
					Swal.fire({
						type: 'file', // 弹框类型
						title: '失败信息', //标题
						text: data.data.msg, //显示内容
						confirmButtonText: '确定',
					}).then(function(isConfirm) {
					})
				}
			});
		});
	});
});

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
	data.append("module",3)

	request.post("/admin/file/upload",data).then(function(data) {
		if (data == '') {
			swal("上传失败", "请重试操作", "error");
		} else {
			var path = '图片地址  ' + date.path;
			swal("上传成功", path, "success");
		}
		editor.insertImage($editable, date.path);
	})
}

function renderAutoConfig() {
	let url = "/admin/auto-config/list";
	request.get(url).then(function (data) {
		if (data != null) {
			//数据渲染到html
			var responseData = data.data.data;
			$('select[name="autoConfig"]').html("");
			var typeName =  '<option value="0">默认</option>';
			for (var i = 0; i < responseData.length; i++) {
				typeName += '<option value="' + responseData[i].id + '">' + responseData[i].title + '</option>';
			}
			// 初始化数据
			$('select[name="autoConfig"]').append(typeName);
			layui.form.render();
		}
	});
}



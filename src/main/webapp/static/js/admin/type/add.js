import request from "/upload/js/axios-config.js";

$(document).ready(function() {
	layui.use(function(){
		var upload = layui.upload;
		var $ = layui.$;
		var token = localStorage.getItem("accessToken")
		// 渲染
		upload.render({
			elem: '#ID-upload-demo-drag', // 单图片上传
			headers: {
				'Authorization': 'Bearer ' + token // 设置Token请求头
			},
			url: '/admin/file/upload',
			done: function(res){
				layer.msg('上传成功');
				$('#ID-upload-demo-preview').removeClass('layui-hide')
					.find('img').attr('src', res.data);
				console.log(res)
			}
		});
	});
})

$('#submit_btn').on('click', function () {
	var formData = {
		title: $('input[name="title"]').val(),
		typeId: $('select[name="typeId"]').val(),
		introduction: $('input[name="introduction"]').val(),
		images : $('#images').attr('src'),
		token: $('input[name="token"]').val(),
		status: $('select[name="status"]').val(),
	};
	request.post("/admin/soft/create", formData).then(function(data){
		if(data.data.code == "0"){
			Swal.fire({
				type: 'success', // 弹框类型
				title: '创建专区', //标题
				text: "创建成功！", //显示内容
				confirmButtonText: '确定',
			}).then(function(isConfirm) {
			})
		}else{
			Swal.fire({
				type: 'warning', // 弹框类型
				title: '失败信息', //标题
				text: data.data.msg, //显示内容
				confirmButtonText: '确定',
			}).then(function(isConfirm) {
			})
		}
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

function renderSoft() {
	let url = "/admin/soft/softList";
	request.get(url).then(function (data) {
		if (data != null) {
			//数据渲染到html
			var responseData = data.data.data;
			$('select[name="typeId"]').html("");
			var typeName = '';
			for (var i = 0; i < responseData.length; i++) {
				typeName += '<option value="' + responseData[i].id + '">' + responseData[i].title + '</option>';
			}
			// 初始化数据
			$('select[name="typeId"]').append(typeName);
			//初始化博客信息。
			//为什么放这里，因为我也不知道为什么，不放这里$('select[name="softId"]').val(data.softId)选不上。
			//可能是和加载顺序有关系，放在这里可以保证先初始化soft信息，再来初始化博客。
			layui.form.render();
		}
	});
}


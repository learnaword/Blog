import request from "/upload/js/axios-config.js";

$(document).ready(function() {

	renderSoft();
	layui.use(function(){
		var upload = layui.upload;
		var $ = layui.$;
		var token = localStorage.getItem("accessToken")
		// 渲染
		upload.render({
			elem: '#content-image-drag', // 单图片上传
			headers: {
				'Authorization': 'Bearer ' + token // 设置Token请求头
			},
			url: '/admin/file/upload',
			done: function(res){
				layer.msg('上传成功');
				$('#content-image-preview').removeClass('layui-hide')
					.find('img').attr('src', res.data);
				console.log(res)
			}
		});
	});

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
	const id = getURLParameter('id');
	var formData = {
		id: id,
		title: $('input[name="title"]').val(),
		typeId: $('select[name="typeId"]').val(),
		introduction: $('input[name="introduction"]').val(),
		images : $('#images').attr('src'),
		token: $('input[name="token"]').val(),
		status: $('select[name="status"]').val(),
	};
	request.put("/admin/soft/update", formData).then(function(data){
		if(data.data.code == "0"){
			Swal.fire({
				type: 'success', // 弹框类型
				title: '更新配置', //标题
				text: "更新成功！", //显示内容
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

function getURLParameter(id) {
	const urlParams = new URLSearchParams(window.location.search);
	return urlParams.get(id);
}

function initConfig() {
	const id = getURLParameter('id');
	let url = "/admin/soft/get?id=" + id;
	request.get(url).then(function (res) {
		var data = res.data.data;
		$('input[name="title"]').val(data.title),
		$('select[name="typeId"]').val(data.typeId),
		$('input[name="introduction"]').val(data.introduction),
		$('#images').attr('src',data.images),
		$('input[name="token"]').val(data.token),
		$('select[name="status"]').val(data.status),
		$('#ID-upload-demo-preview').removeClass('layui-hide')
		layui.form.render();
	})
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

	request.post("/admin/file/upload",data).then(function(data) {
		if(res.data.code == "0"){
			Swal.fire({
				type: 'success', // 弹框类型
				title: '上传图片', //标题
				text: "上传成功！", //显示内容
				confirmButtonText: '确定',
			}).then(function(isConfirm) {
				editor.insertImage($editable, res.data.data);
			})
		}else{
			Swal.fire({
				type: 'file', // 弹框类型
				title: '失败信息', //标题
				text: res.data.msg, //显示内容
				confirmButtonText: '确定',
			}).then(function(isConfirm) {
			})
		}
	})
}

function renderSoft() {
	let url = "/admin/soft/list";
	request.get(url).then(function (data) {
		if (data != null) {
			//数据渲染到html
			var responseData = data.data.data;
			$('select[name="softId"]').html("");
			var typeName = '';
			for (var i = 0; i < responseData.length; i++) {
				typeName += '<option value="' + responseData[i].id + '">' + responseData[i].title + '</option>';
			}
			// 初始化数据
			$('select[name="softId"]').append(typeName);
			//初始化博客信息。
			//为什么放这里，因为我也不知道为什么，不放这里$('select[name="softId"]').val(data.softId)选不上。
			//可能是和加载顺序有关系，放在这里可以保证先初始化soft信息，再来初始化博客。
			initConfig()
			layui.form.render();
		}
	});
}


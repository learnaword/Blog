import request from "../../plugins/axios/axios-config.js";
$(document).ready(function() {
	initFile();
	layui.use(function(){
		const id = getURLParameter('id');
		var upload = layui.upload;
		var $ = layui.$;
		var token = localStorage.getItem("accessToken")
		// 渲染
		upload.render({
			elem: '#upload-file-drag', // 单图片上传
			headers: {
				'Authorization': 'Bearer ' + token // 设置Token请求头
			},
			accept: 'file',
			data:{
				id: id,
			},
			url: '/admin/file/update-content',
			done: function(res){
				if(res.code== "0"){
					Swal.fire({
						type: 'success', // 弹框类型
						title: '更新文件', //标题
						text: "更新成功！", //显示内容
						confirmButtonText: '确定',
					}).then(function(isConfirm) {
					})
				}else{
					Swal.fire({
						type: 'warning', // 弹框类型
						title: '失败信息', //标题
						text: res.msg, //显示内容
						confirmButtonText: '确定',
					}).then(function(isConfirm) {
					})
				}
			}
		});
	});
})

$('#submit_btn').on('click', function () {
	const id = getURLParameter('id');
	var formData = {
		id: id,
		name: $('input[name="name"]').val(),
		path: $('input[name="path"]').val(),
		module: $('select[name="module"]').val(),
		status: $('select[name="status"]').val(),
	};
	request.put("/admin/file/update", formData).then(function(data){
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

function initFile() {
	const id = getURLParameter('id');
	let url = "/admin/file/get?id=" + id;
	request.get(url).then(function (res) {
		var data = res.data.data;
		$('input[name="url"]').val(data.url),
		$('input[name="name"]').val(data.name),
		$('input[name="path"]').val(data.path),
		$('select[name="module"]').val(data.module),
		$('select[name="status"]').val(data.status),
		layui.form.render();
	})
}

$('#clearCache_btn').on('click', function () {
	// 使用 JavaScript 发送 HTTP 请求到 Nginx purge URL
	var xhr = new XMLHttpRequest();
	var url = $('input[name="url"]').val()
	xhr.open("PURGE", "https://www.bangmangma.com/purge" + url, true);
	xhr.onreadystatechange = function () {
			// 缓存清理成功的处理逻辑
			Swal.fire({
				type: 'success', // 弹框类型
				title: '清理缓存', //标题
				text: "清理成功！", //显示内容
				confirmButtonText: '确定',
			}).then(function(isConfirm) {
			})
		}
	xhr.send();
})



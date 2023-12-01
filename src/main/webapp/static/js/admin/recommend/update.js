import request from "/upload/js/axios-config.js";

$(document).ready(function() {

	initConfig()
	layui.use(function () {
		var upload = layui.upload;
		var $ = layui.$;
		var token = localStorage.getItem("accessToken")
		// 渲染

		layui.use(function () {
			var upload = layui.upload;
			var $ = layui.$;
			var token = localStorage.getItem("accessToken")
			// 渲染
			upload.render({
				elem: '#ID-upload-demo-drag', // 单图片上传
				headers: {
					'Authorization': 'Bearer ' + token // 设置Token请求头
				}, data: {
					module: 4,
				},
				url: '/admin/file/upload',
				done: function (res) {
					if (res.code == "0") {
						Swal.fire({
							type: 'success', // 弹框类型
							title: '上传图片', //标题
							text: "上传成功！", //显示内容
							confirmButtonText: '确定',
						}).then(function (isConfirm) {
							$('#ID-upload-demo-preview').removeClass('layui-hide')
								.find('img').attr('src', res.data);
						})
					} else {
						Swal.fire({
							type: 'file', // 弹框类型
							title: '失败信息', //标题
							text: res.msg, //显示内容
							confirmButtonText: '确定',
						}).then(function (isConfirm) {
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
			title: $('input[name="title"]').val(),
			smallTitle: $('input[name="small_title"]').val(),
			introduction: $('input[name="introduction"]').val(),
			images : $('#images').attr('src'),
			buttonInfo: $('input[name="button_info"]').val(),
			buttonBottom: $('input[name="button_bottom"]').val(),
			buttonLink: $('input[name="button_link"]').val(),
			status: $('select[name="status"]').val(),
		};
		request.put("/admin/recommend/update", formData).then(function (data) {
			if (data.data.code == "0") {
				Swal.fire({
					type: 'success', // 弹框类型
					title: '更新推荐', //标题
					text: "更新成功！", //显示内容
					confirmButtonText: '确定',
				}).then(function (isConfirm) {
				})
			} else {
				Swal.fire({
					type: 'warning', // 弹框类型
					title: '失败信息', //标题
					text: data.data.msg, //显示内容
					confirmButtonText: '确定',
				}).then(function (isConfirm) {
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
		let url = "/admin/recommend/get?id=" + id;
		request.get(url).then(function (res) {
			var data = res.data.data;
			$('input[name="title"]').val(data.title),
				$('input[name="introduction"]').val(data.introduction),
				$('#images').attr('src', data.images),
				$('input[name="button_info"]').val(data.buttonInfo),
				$('input[name="button_bottom"]').val(data.buttonBottom),
				$('input[name="button_link"]').val(data.buttonLink),
				$('input[name="small_title"]').val(data.smallTitle),
				$('select[name="status"]').val(data.status),
				$('#ID-upload-demo-preview').removeClass('layui-hide')
			layui.form.render();
		})
	}

});


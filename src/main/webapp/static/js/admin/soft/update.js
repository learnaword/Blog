import request from "../../axios/axios-config.js";

$(document).ready(function() {

	renderType();
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
			typeId: $('select[name="typeId"]').val(),
			introduction: $('input[name="introduction"]').val(),
			images: $('#images').attr('src'),
			token: $('input[name="token"]').val(),
			status: $('select[name="status"]').val(),
		};
		request.post("/admin/soft/update", formData).then(function (data) {
			if (data.data.code == "0") {
				Swal.fire({
					type: 'success', // 弹框类型
					title: '更新配置', //标题
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
		let url = "/admin/soft/get?id=" + id;
		request.get(url).then(function (res) {
			var data = res.data.data;
			$('input[name="title"]').val(data.title),
				$('select[name="typeId"]').val(data.typeId),
				$('input[name="introduction"]').val(data.introduction),
				$('#images').attr('src', data.images),
				$('input[name="token"]').val(data.token),
				$('select[name="status"]').val(data.status),
				$('#ID-upload-demo-preview').removeClass('layui-hide')
			layui.form.render();
		})
	}

	function renderType() {
		let url = "/admin/type/list";
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
				initConfig()
				layui.form.render();
			}
		});
	}
});


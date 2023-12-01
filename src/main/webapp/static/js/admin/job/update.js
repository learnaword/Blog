import request from "/upload/js/axios-config.js";

$(document).ready(function() {
	$("#cron").cronGen({
		direction : 'right'
	});

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
			name: $('input[name="name"]').val(),
			handlerName: $('input[name="handlerName"]').val(),
			cronExpression: $('input[name="cronExpression"]').val(),
			retryCount: $('input[name="retryCount"]').val(),
			retryInterval: $('input[name="retryInterval"]').val(),
			monitorTimeout: $('input[name="monitorTimeout"]').val(),
		};
		request.put("/admin/job/update", formData).then(function (data) {
			if (data.data.code == "0") {
				Swal.fire({
					type: 'success', // 弹框类型
					title: '更新任务', //标题
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
		let url = "/admin/job/get?id=" + id;
		request.get(url).then(function (res) {
			var data = res.data.data;
			$('input[name="name"]').val(data.name),
				$('input[name="handlerName"]').val(data.handlerName),
				$('input[name="cronExpression"]').val(data.cronExpression),
				$('input[name="retryCount"]').val(data.retryCount),
				$('input[name="retryInterval"]').val(data.retryInterval),
				$('input[name="monitorTimeout"]').val(data.monitorTimeout),
				$('#ID-upload-demo-preview').removeClass('layui-hide')
			layui.form.render();
		})
	}

});


import request from "../../axios/axios-config.js";

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
			},data:{
				module: 4,
			},
			url: '/admin/file/upload',
			done: function(res){
				if(res.code == "0"){
					Swal.fire({
						type: 'success', // 弹框类型
						title: '上传图片', //标题
						text: "上传成功！", //显示内容
						confirmButtonText: '确定',
					}).then(function(isConfirm) {
						$('#ID-upload-demo-preview').removeClass('layui-hide')
							.find('img').attr('src', res.data);
					})
				}else{
					Swal.fire({
						type: 'file', // 弹框类型
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



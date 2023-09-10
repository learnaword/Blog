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
						$('#content-image-preview').removeClass('layui-hide')
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
	const id = getURLParameter('id');
	var formData = {
		id: id,
		title: $('input[name="title"]').val(),
		softId: $('select[name="softId"]').val(),
		softSection: $('select[name="softSection"]').val(),
		adType: $('select[name="adType"]').val(),
		isTop: $('input[name="isTop"]:checked').val(),
		isRecommend: $('input[name="isRecommend"]:checked').val(),
		rankScore: $('input[name="rankScore"]').val(),
		recommendHtml: $('textarea[name="recommendHtml"]').val(),
		blogStatus: $('select[name="blogStatus"]').val(),
		images : $('#images').attr('src'),
		contentImages : $('#contentImages').attr('src'),
	};
	request.post("/admin/auto-config/update", formData).then(function(data){
		if(data.data.code == "0"){
			Swal.fire({
				type: 'success', // 弹框类型
				title: '保存配置', //标题
				text: "保存成功！", //显示内容
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
	let url = "/admin/auto-config/get?id=" + id;
	request.get(url).then(function (res) {
		var data = res.data.data;
		//渲染图表
		$('input[name="title"]').val(data.title);
		$('input[name="rankScore"]').val(data.rankScore);
		$('select[name="softSection"]').val(data.softSection);
		$('select[name="softId"]').val(data.softId);
		$('select[name="blogStatus"]').val(data.blogStatus);
		$('textarea[name="recommendHtml"]').val(data.recommendHtml);
		$('#images').attr('src', data.images);
		$('#contentImages').attr('src', data.contentImages);
		$('input[name="adType"][value="'+ data.adType + '"]').prop('checked', true);
		$('input[name="isTop"][value="'+ data.isTop + '"]').prop('checked', true);
		$('input[name="isRecommend"][value="'+ data.isRecommend + '"]').prop('checked', true);
		$('#ID-upload-demo-preview').removeClass('layui-hide')
		$('#content-image-preview').removeClass('layui-hide')
		layui.form.render();
	})
}

function renderSoft() {
	let url = "/admin/soft/softList";
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


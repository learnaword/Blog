import request from "/upload/js/axios-config.js";

$(document).ready(function() {

	renderSoft();
	renderAd();

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

function renderAd() {
	let url = "/admin/recommend/list";
	request.get(url).then(function (data) {
		if (data != null) {
			//数据渲染到html
			var responseData = data.data.data;
			$('select[name="adType1"]').html("");
			$('select[name="adType2"]').html("");
			$('select[name="adType3"]').html("");
			$('select[name="adType4"]').html("");

			var typeName = '';
			for (var i = 0; i < responseData.length; i++) {
				typeName += '<option value="' + responseData[i].id + '">' + responseData[i].title + '</option>';
			}

			// 初始化数据
			$('select[name="adType1"]').append(typeName);
			$('select[name="adType2"]').append(typeName);
			$('select[name="adType3"]').append(typeName);
			$('select[name="adType4"]').append(typeName);

			//初始推荐信息
			var selectedAd = [127,128,126,129];
			setAdSelectedDefault(selectedAd);

			//初始化博客信息。
			//为什么放这里，因为我也不知道为什么，不放这里$('select[name="softId"]').val(data.softId)选不上。
			//可能是和加载顺序有关系，放在这里可以保证先初始化soft信息，再来初始化博客。
			initConfig();
			layui.form.render();
		}
	});
}

function setAdSelectedDefault(selectedAd){
	$('select[name="adType1"]').val(selectedAd[0]);
	$('select[name="adType2"]').val(selectedAd[1]);
	$('select[name="adType3"]').val(selectedAd[2]);
	$('select[name="adType4"]').val(selectedAd[3]);
}

$('#submit_btn').on('click', function () {
	var selectedAd = [
		$('select[name="adType1"]').val(),
		$('select[name="adType2"]').val(),
		$('select[name="adType3"]').val(),
		$('select[name="adType4"]').val()
	];

	var formData = {
		title: $('input[name="title"]').val(),
		softId: $('select[name="softId"]').val(),
		softSection: $('select[name="softSection"]').val(),
		adType: $('select[name="adType"]').val(),
		isTop: $('input[name="isTop"]:checked').val(),
		adTypes: selectedAd,
		isRecommend: $('input[name="isRecommend"]:checked').val(),
		rankScore: $('input[name="rankScore"]').val(),
		recommendHtml: $('textarea[name="recommendHtml"]').val(),
		blogStatus: $('select[name="blogStatus"]').val(),
		images : $('#images').attr('src'),
		contentImages : $('#contentImages').attr('src'),
	};
	request.post("/admin/auto-config/create", formData).then(function(data){
		if(data.data.code == "0"){
			Swal.fire({
				type: 'success', // 弹框类型
				title: '创建文章', //标题
				text: "保存成功！", //显示内容
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
			layui.form.render();
		}
	});
}


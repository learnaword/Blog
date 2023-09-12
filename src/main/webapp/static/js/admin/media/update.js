import request from "/upload/js/axios-config.js";

$(document).ready(function() {
	initBlog();
	// 初始化富文本
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

		$('#submit_btn').on('click', function () {
			const id = getURLParameter('id');
			var formData = {
				id: id,
				title: $('input[name="title"]').val(),
				introduction: $('input[name="introduction"]').val(),
				adType: $('select[name="adType"]').val(),
				status: $('select[name="status"]').val(),
				isTop: $('input[name="isTop"]:checked').val(),
				rankScore: $('input[name="rankScore"]').val(),
				keyword: $('input[name="keyword"]').val(),
				content: $("#summernote").code(),
				images : $('#images').attr('src'),
			};
			request.put("/admin/media/update", formData).then(function(data){
				if(data.data.code == "0"){
					Swal.fire({
						type: 'success', // 弹框类型
						title: '更新文章', //标题
						text: "更新成功！", //显示内容
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
});

function getURLParameter(id) {
	const urlParams = new URLSearchParams(window.location.search);
	return urlParams.get(id);
}

function initBlog() {
	const id = getURLParameter('id');
	let url = "/admin/media/get?id=" + id;
	request.get(url).then(function (res) {
		var data = res.data.data;
		//渲染图表
		$('input[name="title"]').val(data.title);
		$('input[name="rankScore"]').val(data.rankScore);
		$('input[name="introduction"]').val(data.introduction);
		$('input[name="keyword"]').val(data.keyword);
		$('select[name="status"]').val(data.status);
		$('#images').attr('src', data.images);
		$('input[name="adType"][value="'+ data.adType + '"]').prop('checked', true);
		$('input[name="isTop"][value="'+ data.isTop + '"]').prop('checked', true);
		$("#summernote").code(data.content)
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
	var data = new FormData();
	data.append("file", file);
	data.append("key", filename); //唯一性参数
	data.append("module",3)
	request.post("/admin/file/upload",data).then(function(res) {
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

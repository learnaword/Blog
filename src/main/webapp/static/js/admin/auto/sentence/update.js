import request from "../../../axios/axios-config.js";

$(document).ready(function() {
	initSentence();
})

$('#submit_btn').on('click', function () {
	const id = getURLParameter('id');
	var formData = {
		id: id,
		status: $('input[name="status"]:checked').val(),
		usages: $('input[name="usages"]').val(),
		content: $('textarea[name="content"]').val(),
	};
	request.post("/admin/auto-sentence/update", formData).then(function(data){
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
				type: 'file', // 弹框类型
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

function initSentence() {
	const id = getURLParameter('id');
	let url = "/admin/auto-sentence/get?id=" + id;
	request.get(url).then(function (res) {
		var data = res.data.data;
		//渲染图表
		$('input[name="usages"]').val(data.usages);
		$('textarea[name="content"]').val(data.content);
		$('input[name="status"][value="'+ data.status + '"]').prop('checked', true);
		layui.form.render();
	})
}





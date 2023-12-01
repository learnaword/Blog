import request from "/upload/js/axios-config.js";

$(document).ready(function() {
	$("#cron").cronGen({
		direction : 'right'
	});

})

$('#submit_btn').on('click', function () {
	var formData = {
		name: $('input[name="name"]').val(),
		handlerName: $('input[name="handlerName"]').val(),
		cronExpression: $('input[name="cronExpression"]').val(),
		retryCount: $('input[name="retryCount"]').val(),
		retryInterval: $('input[name="retryInterval"]').val(),
		monitorTimeout: $('input[name="monitorTimeout"]').val(),
	};
	request.post("/admin/job/create", formData).then(function(data){
		if(data.data.code == "0"){
			Swal.fire({
				type: 'success', // 弹框类型
				title: '创建任务', //标题
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




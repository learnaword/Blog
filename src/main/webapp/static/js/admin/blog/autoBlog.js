
var addBlog = function() {
	var params = {
		'title' : $("#title").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
                'id' : $("#id").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
	};
	$.ajax({
		url : "../addAutoBlog",
		type : "POST",
		data : params,
		dataType : 'json',
		success : function(data) {
			if (data.status == 200) {
				swal("发布成功", "请查看", "success");
				$("#title").val("");
			} else {
				swal("发布失败", "请重新操作", "error");
			}
			setTimeout(function() {
				window.location.href="../blog/updateBlog.jsp?id="+(data.blog.id);
			}, 1000);
		},
		error : function() {
			swal("发布错误", "请重新操作", "error");
		}
	});
};

//只有验证通过才能执行 添加
$("#add_blog").click(function() {
	addBlog();
});
;

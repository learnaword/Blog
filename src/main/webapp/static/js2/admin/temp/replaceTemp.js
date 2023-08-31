
var replaceTemp = function() {
    var url = window.location.href;
    var url_param = url.split("=")[1];
    var params = {
        'title' : $("#title").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
        'content' : $("#content").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
        'replace' : $("#replace").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
    };
    $.ajax({
        url : '../replaceTemp',
        type : 'post',
        data : params,
        dataType : 'json',
        success : function(data) {
            if (data.status == 200) {
                var title = "更新成功";
                var text = "博客已在前端展示";
                swal({
                    title : title,
                    text : text,
                    type : "success",
                    timer : 1500,
                });

            } else {
                swal("更新失败", data.msg, "error");
            }
        },
        error : function() {
            swal("更新错误", "请重新操作", "error");
        }
    });

}


//只有验证通过才能执行 添加
$("#replace_temp").click(function() {
    replaceTemp();
});

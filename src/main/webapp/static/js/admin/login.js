function login(){
    var formData = {
        username: $('input[name="username"]').val(),
        password: $('input[name="password"]').val()
    };
    $.post("/admin/checkLogin",formData, function (data){
        if(data.code != 0){
            $(".msg").text(data.msg);
        }else{
            //存储token
            localStorage.setItem("accessToken",data.accessToken);
            localStorage.setItem("refreshToken",data.refreshToken);
            //页面跳转
            window.location.href = "/admin/index";
        }
    })
}

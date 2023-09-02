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
            localStorage.setItem("accessToken",data.data.accessToken);
            localStorage.setItem("accessTokenExpires",data.data.expires);
            localStorage.setItem("refreshToken",data.data.refreshToken);
            window.location.href = "/page/admin/index.jsp";
        }
    })
}

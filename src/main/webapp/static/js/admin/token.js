
var ua = window.navigator.userAgent;
var isChrome = ua.indexOf("Chrome") && window.chrome;
if (!isChrome) {
    //浏览器后退刷新
    function reload() {
        setInterval(function() {   //这个定时器返回A页面会继续执行
            if (localStorage.reload == 'true' ) {  //判断是否刷新页面
                localStorage.setItem('reload','false');
                location.reload()
            }
        }, 500)
    };
    reload();
}
$(document).ready(function() {

    // 获取token
    const accessToken = localStorage.getItem('accessToken');
    const expires = localStorage.getItem('accessTokenExpires');
    if (!accessToken) {
        Swal.fire({
            type: 'warning', // 弹框类型
            title: '请登录', //标题
            text: "没有登录账号！", //显示内容
            confirmButtonText: '确定',
        }).then(function(isConfirm) {
            localStorage.setItem('reload','true');
            window.location.href = '/admin/login'; // 示例：重定向到登录页面
        })
    }else if (!isTokenValid(expires)) {
        Swal.fire({
            type: 'warning', // 弹框类型
            title: '登录失效', //标题
            text: "登录已过期，请重新登录！", //显示内容
            confirmButtonText: '确定',
        }).then(function(isConfirm) {
            localStorage.setItem('reload','true');
            window.location.href = '/admin/login'; // 示例：重定向到登录页面
        })
    }else{

    }
});

function isTokenValid(expirationTime) {
    if (!expirationTime) {
        // 如果过期时间不存在，令牌无效
        return false;
    }

    const expirationDate = new Date(expirationTime); // 将后端传递的过期时间转换为JavaScript的Date对象
    const currentTime = new Date();

    if (currentTime >= expirationDate) {
        // 当前时间超过了过期时间，令牌已经过期
        return false;
    }

    // 令牌在有效期内
    return true;
}


$(document).ready(function() {
    // 为类名为 "fdownload-button" 的元素添加点击事件监听器
    $('.fdownload-button').click(function() {
        var info = $(this).data('info');
        handleButtonClick(info,'文章底部');
    });

    // 为类名为 "mdownload-button" 的元素添加点击事件监听器
    $('.mdownload-button').click(function() {
        var info = $(this).data('info');
        handleButtonClick(info,'文章内部');
    });

    // 共享的点击事件处理函数
    function handleButtonClick(info,position) {
        var currentUrl = window.location.href;
        var match = currentUrl.match(/\/(\d+)\.html$/);
        // 获取文章ID
        var blogId = match ? match[1] : null;

        // 发送数据到后端（用您的后端端点替换 URL）
        $.ajax({
            url: '/data/button-info',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                buttonInfo: info, // 使用提取的信息
                blogId: blogId,
                position: position
            }),
        });
    }
});

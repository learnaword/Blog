$(document).ready(function() {
    $('.fdownload-button').click(function() {
        var info = $(this).data('info').split(",");
       // navigator.clipboard.writeText(info[1]);
        handleButtonClick(info[0],'文章底部');
    });

    $('.mdownload-button').click(function() {
        var info = $(this).data('info').split(",");
        handleButtonClick(info[0],'文章内部');
    });

    function handleButtonClick(info,position) {
        var currentUrl = window.location.href;
        var match = currentUrl.match(/\/(\d+)\.html$/);
        // 获取文章ID
        var blogId = match ? match[1] : null;

        $.ajax({
            url: '/data/button-info',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                recommendId: info, // 使用提取的信息
                blogId: blogId,
                position: position
            }),
        });
    }
});

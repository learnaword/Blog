$(document).ready(function() {
    $('.fdownload-button').click(function() {
        var info = $(this).data('info');
        handleButtonClick(info,'文章底部');
    });

    $('.mdownload-button').click(function() {
        var info = $(this).data('info');
        handleButtonClick(info,'文章内部');
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
                buttonInfo: info, // 使用提取的信息
                blogId: blogId,
                position: position
            }),
        });
    }
});

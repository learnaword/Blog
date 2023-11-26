import request from "/upload/js/axios-config.js";
$(document).ready(function(){
    layui.use(['table', 'dropdown'], function(){
        var table = layui.table;
        var dropdown = layui.dropdown;
        var util = layui.util;
        var token = localStorage.getItem("accessToken")
        // 创建渲染实例
        table.render({
            elem: '#test'
            ,url:'/data/button-table' // 此处为静态模拟数据，实际使用时需换成真实接口
            ,toolbar: '#toolbarDemo'
            ,headers: {
                'Authorization': 'Bearer ' + token // 设置 Authorization 头，通常用于传递 token
            }
            ,defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,css: [ // 重设当前表格样式
                '.layui-table-tool-temp{padding-right: 145px;}'
            ].join('')
            ,cellMinWidth: 80
            ,totalRow: true // 开启合计行
            ,page: true
            ,limits: [20, 50, 100]
            ,limit: 20
            ,request: {
                pageName: 'pageNo', // 页码的参数名称，默认：page
                limitName: 'pageSize' // 每页数据条数的参数名，默认：limit
            }
            ,parseData: function (res) { // 解析返回的数据
                return {
                    code: res.code,
                    data: res.data.list,
                    count: res.data.total
                };
            }
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', fixed: 'left', width:80, title: 'ID', sort: true, total: '合计：'}
                ,{field:'blogId', width: 240, title: '文章ID'}
                ,{field:'blogTitle', width: 240, title: '文章标题'}
                ,{field:'buttonInfo', title:'按钮信息', width: 120}
                ,{field:'position', title:'位置', width: 120}
                ,{field:'ip', title:'IP', width: 120}
                ,{field:'createTime', title:'发布时间', width: 190, templet: function (d) {return util.toDateString(d.createTime,"yyyy-MM-dd HH:mm:ss")}}
            ]]
            ,error: function(res, msg){
                console.log(res, msg)
            }
        });
        table.on('toolbar(test)', function(obj){
            var id = obj.config.id;
            switch(obj.event){
                case 'refresh':
                    location.reload();
                    break;
            };
        });
    });
})



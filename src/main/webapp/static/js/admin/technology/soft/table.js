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
            ,url:'/admin/technology/soft/table' // 此处为静态模拟数据，实际使用时需换成真实接口
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
                ,{field:'title', width: 240, title: '标题'}
                ,{field:'introduction', title:'介绍', width: 120}
                ,{field:'typeTitle', title:'类型', width: 80}
                ,{field:'token', title:'token', width: 80}
                ,{
                    field: 'status', title: '状态', align: 'center',width: 120, templet: function (d) {
                        var str="";
                        if (d.status == '0') {
                            str = str + '<span class="layui-badge layui-bg-blue">发布</span>'
                        } else if (d.status == '1'){
                            str = str + '<span style="background-color: #cccccc" class="layui-badge">删除</span>'
                        }
                        return str;
                    }
                }
                ,{field:'updateTime', title:'更新时间', width: 120, templet: function (d) {return util.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss")}}
                ,{field:'createTime', title:'发布时间', width: 120, templet: function (d) {return util.toDateString(d.createTime,"yyyy-MM-dd HH:mm:ss")}}
                ,{fixed: 'right', title:'操作', width: 160, minWidth: 125, toolbar: '#barDemo'}
            ]]
            ,done: function(){
                var id = this.id;
                // 下拉按钮测试
                dropdown.render({
                    elem: '#dropdownButton' // 可绑定在任意元素中，此处以上述按钮为例
                    ,data: [{
                        id: 'SPAM',
                        title: '移到删除'
                    },{
                        id: 'PUBLISHED',
                        title: '移到发布'
                    }]
                    // 菜单被点击的事件
                    ,click: function(obj){
                        var checkStatus = table.checkStatus(id)
                        var data = checkStatus.data; // 获取选中的数据
                        switch(obj.id){
                            case 'SPAM':
                                var selectedIds = data.map(function(item) {
                                    return item.id;
                                });
                                updateStatus(selectedIds,1,"移到删除")
                                break;
                            case 'PUBLISHED':
                                var selectedIds = data.map(function(item) {
                                    return item.id;
                                });
                                updateStatus(selectedIds,0,"移到发布")
                                break;
                        }
                    }
                });

                // 重载测试
                dropdown.render({
                    elem: '#reloadTest' // 可绑定在任意元素中，此处以上述按钮为例
                    ,data: [{
                        id: 'PUBLISHED',
                        title: '查看发布'
                    },{
                        id: 'SPAM',
                        title: '查看删除'
                    }]
                    // 菜单被点击的事件
                    ,click: function(obj){
                        switch(obj.id){
                            case 'PUBLISHED':
                                // 发布
                                table.reload('test', {
                                    where: {
                                        status: 0
                                    }
                                });
                                break;
                            case 'SPAM':
                                // 已删除
                                table.reload('test', {
                                    where: {
                                        status: 1
                                    }
                                }, true);
                                break;
                        }
                    }
                });

                // 行模式
                dropdown.render({
                    elem: '#rowMode'
                    ,data: [{
                        id: 'default-row',
                        title: '单行模式（默认）'
                    },{
                        id: 'multi-row',
                        title: '多行模式'
                    }]
                    // 菜单被点击的事件
                    ,click: function(obj){
                        var checkStatus = table.checkStatus(id)
                        var data = checkStatus.data; // 获取选中的数据
                        switch(obj.id){
                            case 'default-row':
                                table.reload('test', {
                                    lineStyle: null // 恢复单行
                                });
                                layer.msg('已设为单行');
                                break;
                            case 'multi-row':
                                table.reload('test', {
                                    // 设置行样式，此处以设置多行高度为例。若为单行，则没必要设置改参数 - 注：v2.7.0 新增
                                    lineStyle: 'height: 95px;'
                                });
                                layer.msg('即通过设置 lineStyle 参数可开启多行');
                                break;
                        }
                    }
                });
            }
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

        // 触发单元格工具事件
        table.on('tool(test)', function(obj){ // 双击 toolDouble
            var data = obj.data; // 获得当前行数据
            // console.log(obj)
            if(obj.event === 'edit'){
                window.location.href = "/page/admin/technology/soft/update.jsp?id="+data.id;
            } else if(obj.event === 'more'){
                // 更多 - 下拉菜单
                dropdown.render({
                    elem: this, // 触发事件的 DOM 对象
                    show: true, // 外部事件触发即显示
                    data: [{
                        id: 'SPAM',
                        title: '移到删除'
                    },{
                        id: 'PUBLISHED',
                        title: '移到发布'
                    },{
                        id: 'SPAM',
                        title: '彻底删除'
                    }],
                    click: function(menudata){
                        if(menudata.id === 'SPAM'){
                            layer.confirm('将 [id: '+ data.id +'] 移到删除', function(index){
                                updateStatus([data.id],1,"移到删除");
                                layer.close(index);
                            })
                        }else if(menudata.id === 'PUBLISHED'){
                            layer.confirm('将 [id: '+ data.id +'] 移到发布', function(index){
                                updateStatus([data.id],0,"移到发布");
                                layer.close(index);
                            })
                        }
                    },
                    align: 'right', // 右对齐弹出
                    style: 'box-shadow: 1px 1px 10px rgb(0 0 0 / 12%);' // 设置额外样式
                })
            }
        });

    });
})
export function updateStatus(selectedIds,status,msg){
    request.put("/admin/technology/soft/update-status", {ids:selectedIds,status: status}).then(function(data){
        Swal.fire({
            type: 'warning', // 弹框类型
            title: msg + '操作', //标题
            text: msg + "成功！", //显示内容
            confirmButtonText: '确定',
        }).then(function(isConfirm) {
            var table = layui.table;
            table.reloadData('test', {
                where: {
                    status: status
                }
            });
        })
    })
}



window.updateStatus = updateStatus


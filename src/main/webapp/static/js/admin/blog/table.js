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
            ,url:'/admin/blog/table' // 此处为静态模拟数据，实际使用时需换成真实接口
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
            ,limits: [10, 20, 25]
            ,limit: 10
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
                ,{field:'keyword', title:'关键词', width: 120}
                ,{field:'introduction', title: '描述', edit: 'textarea', minWidth: 120}
                ,{field:'updateTime', title:'更新时间', width: 110, templet: function (d) {return util.toDateString(d.updateTime,"yyyy-MM-dd HH:mm:ss")}}
                ,{field:'createTime', title:'发布时间', width: 110, templet: function (d) {return util.toDateString(d.createTime,"yyyy-MM-dd HH:mm:ss")}}
                ,{
                    field: 'status', title: '状态', align: 'center',width: 170, templet: function (d) {
                        var str="";
                        if (d.status == '-1') {
                            str = str + '<span class="layui-badge layui-bg-orange">储备</span>'
                        } else if (d.status == '1'){
                            str = str + '<span class="layui-badge layui-bg-blue">发布</span>'
                        } else if (d.status == '2') {
                            str = str + '<span style="background-color: #cccccc" class="layui-badge">删除</span>'
                        }else if(d.status == '-2') {
                            str = str + '<span style="background-color: #16b777" class="layui-badge">草稿</span>'
                        }
                        if(d.isTop == '1'){
                            str = str + '<span style="background-color:#a233c6" class="layui-badge">置顶</span>'
                        }
                        if(d.isRecommend=='1'){
                            str = str + '<span class="layui-badge">推荐 </span>'
                        }
                        return str;
                    }
                }
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
                        id: 'DRAFT',
                        title: '移到草稿'
                    },{
                        id: 'RESERVED',
                        title: '移到储备'
                    },{
                        id: 'PUBLISHED',
                        title: '移到发布'
                    },{
                        id: 'updateBlogsTop',
                        title: '设为置顶'
                    },{
                        id: 'updateBlogsRecommend',
                        title: '设为推荐'
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
                                updateBlogsStatus(selectedIds,2,"移到删除")
                                break;
                            case 'DRAFT':
                                var selectedIds = data.map(function(item) {
                                    return item.id;
                                });
                                updateBlogsStatus(selectedIds,-2,"移到草稿")
                                break;
                            case 'RESERVED':
                                var selectedIds = data.map(function(item) {
                                    return item.id;
                                });
                                updateBlogsStatus(selectedIds,-1,"移到储备")
                                break;
                            case 'PUBLISHED':
                                var selectedIds = data.map(function(item) {
                                    return item.id;
                                });
                                updateBlogsStatus(selectedIds,1,"移到发布")
                                break;
                            case 'updateBlogsTop':
                                var selectedIds = data.map(function(item) {
                                    return item.id;
                                });
                                updateBlogsTop(selectedIds,1)
                                break;
                                break;
                            case 'updateBlogsRecommend':
                                var selectedIds = data.map(function(item) {
                                    return item.id;
                                });
                                updateBlogsRecommend(selectedIds,1)
                                break;
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
                    },{
                        id: 'RESERVED',
                        title: '查看储备'
                    },{
                        id: 'DRAFT',
                        title: '查看草稿'
                    }]
                    // 菜单被点击的事件
                    ,click: function(obj){
                        switch(obj.id){
                            case 'PUBLISHED':
                                // 发布
                                table.reload('test', {
                                    where: {
                                        status: 1
                                    }
                                });
                                break;
                            case 'SPAM':
                                // 已删除
                                table.reload('test', {
                                    where: {
                                        status: 2
                                    }
                                }, true);
                                break;
                            case 'RESERVED':
                                //储备
                                table.reloadData('test', {
                                    where: {
                                        status: '-1'
                                    }
                                });
                                break;
                            case 'DRAFT':
                                // 草稿
                                table.reloadData('test', {
                                    where: {
                                        status: '-2'
                                    }
                                });
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

        // 工具栏事件
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
                window.location.href = "/page/admin/blog/update.jsp?id="+data.id;
            } else if(obj.event === 'more'){
                // 更多 - 下拉菜单
                dropdown.render({
                    elem: this, // 触发事件的 DOM 对象
                    show: true, // 外部事件触发即显示
                    data: [{
                        id: 'SPAM',
                        title: '移到删除'
                    },{
                        id: 'DRAFT',
                        title: '移到草稿'
                    },{
                        id: 'RESERVED',
                        title: '移到储备'
                    },{
                        id: 'PUBLISHED',
                        title: '移到发布'
                    },{
                        id: 'TOP',
                        title: '设为置顶'
                    },{
                        id: 'RECOMMEND',
                        title: '设为推荐'
                    }],
                    click: function(menudata){
                        if(menudata.id === 'SPAM'){
                            layer.confirm('将 [id: '+ data.id +'] 移到删除', function(index){
                                updateBlogsStatus([data.id],2,"移到删除");
                                layer.close(index);
                            })
                        }else if(menudata.id === 'DRAFT'){
                            layer.confirm('将 [id: '+ data.id +'] 移到草稿', function(index){
                                updateBlogsStatus([data.id],-2,"移到草稿");
                                layer.close(index);
                            })
                        }else if(menudata.id === 'RESERVED'){
                            layer.confirm('将 [id: '+ data.id +'] 移到储备', function(index){
                                updateBlogsStatus([data.id],-1,"移到储备");
                                layer.close(index);
                            })
                        }else if(menudata.id === 'PUBLISHED'){
                            layer.confirm('将 [id: '+ data.id +'] 移到发布', function(index){
                                updateBlogsStatus([data.id],1,"移到发布");
                                layer.close(index);
                            })
                        }else if(menudata.id === 'TOP'){
                            layer.confirm('将 [id: '+ data.id +'] 设为置顶', function(index){
                                updateBlogsTop([data.id],1);
                                layer.close(index);
                            })
                        }else if(menudata.id === 'RECOMMEND'){
                            layer.confirm('将 [id: '+ data.id +'] 设为推荐', function(index){
                                updateBlogsRecommend([data.id],1);
                                layer.close(index);
                            })
                        };
                    },
                    align: 'right', // 右对齐弹出
                    style: 'box-shadow: 1px 1px 10px rgb(0 0 0 / 12%);' // 设置额外样式
                })
            }
        });
    });
})
export function updateBlogsStatus(selectedIds,status,msg){
    request.put("/admin/blog/update-status", {ids:selectedIds,status: status}).then(function(data){
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

export function updateBlogsRecommend(selectedIds,isRecommend){
    request.put("/admin/blog/update-recommends", {ids:selectedIds,isRecommend: isRecommend}).then(function(data){
        Swal.fire({
            type: 'warning', // 弹框类型
            title: '设置推荐', //标题
            text: "设置成功！", //显示内容
            confirmButtonText: '确定',
        }).then(function(isConfirm) {
            location.reload();
        })
    })
}

export function updateBlogsTop(selectedIds,isTop){
    request.put("/admin/blog/update-tops", {ids:selectedIds,isTop: isTop}).then(function(data){
        Swal.fire({
            type: 'warning', // 弹框类型
            title: '设置置顶', //标题
            text: "设置成功！", //显示内容
            confirmButtonText: '确定',
        }).then(function(isConfirm) {
            location.reload();
        })
    })
}

window.updateBlogsStatus = updateBlogsStatus
window.updateBlogsTop = updateBlogsTop
window.updateBlogsRecommend = updateBlogsRecommend


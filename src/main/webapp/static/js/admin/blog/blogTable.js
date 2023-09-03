import request from "../../axios/axios-config.js";

$(document).ready(function(){
    layui.use(['table', 'dropdown'], function(){
        var table = layui.table;
        var dropdown = layui.dropdown;
        var util = layui.util;
        var token = localStorage.getItem("accessToken")
        // 创建渲染实例
        table.render({
            elem: '#test'
            ,url:'/admin/blogTable' // 此处为静态模拟数据，实际使用时需换成真实接口
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
                ,{field:'createTime', title:'发布时间', width: 120, templet: function (d) {return util.toDateString(d.createTime,"yyyy-MM-dd HH:mm:ss")}}
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
                        if(d.istop == '1'){
                            str = str + '<span style="background-color:#a233c6" class="layui-badge">置顶</span>'
                        }
                        if(d.isrecommend=='1'){
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
                                updateBlogsStatus(selectedIds,2)
                                break;
                            case 'DRAFT':
                                var selectedIds = data.map(function(item) {
                                    return item.id;
                                });
                                updateBlogsStatus(selectedIds,-2)
                                break;
                            case 'RESERVED':
                                var selectedIds = data.map(function(item) {
                                    return item.id;
                                });
                                updateBlogsStatus(selectedIds,-1)
                                break;
                            case 'PUBLISHED':
                                var selectedIds = data.map(function(item) {
                                    return item.id;
                                });
                                updateBlogsStatus(selectedIds,1)
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


        // 触发单元格工具事件
        table.on('tool(test)', function(obj){ // 双击 toolDouble
            var data = obj.data; // 获得当前行数据
            // console.log(obj)
            if(obj.event === 'edit'){
                layer.open({
                    title: '编辑 - id:'+ data.id,
                    type: 1,
                    area: ['80%','80%'],
                    content: '<div style="padding: 16px;">自定义表单元素</div>'
                });
            } else if(obj.event === 'more'){
                // 更多 - 下拉菜单
                dropdown.render({
                    elem: this, // 触发事件的 DOM 对象
                    show: true, // 外部事件触发即显示
                    data: [{
                        title: '查看',
                        id: 'detail'
                    },{
                        title: '删除',
                        id: 'del'
                    }],
                    click: function(menudata){
                        if(menudata.id === 'detail'){
                            layer.msg('查看操作，当前行 ID:'+ data.id);
                        } else if(menudata.id === 'del'){
                            layer.confirm('真的删除行 [id: '+ data.id +'] 么', function(index){
                                obj.del(); // 删除对应行（tr）的DOM结构
                                layer.close(index);
                                // 向服务端发送删除指令
                            });
                        }
                    },
                    align: 'right', // 右对齐弹出
                    style: 'box-shadow: 1px 1px 10px rgb(0 0 0 / 12%);' // 设置额外样式
                })
            }
        });

        // 触发表格复选框选择
        table.on('checkbox(test)', function(obj){
            console.log(obj)
        });

        // 触发表格单选框选择
        table.on('radio(test)', function(obj){
            console.log(obj)
        });

        // 行单击事件
        table.on('row(test)', function(obj){
            //console.log(obj);
            //layer.closeAll('tips');
        });
        // 行双击事件
        table.on('rowDouble(test)', function(obj){
            console.log(obj);
        });

        // 单元格编辑事件
        table.on('edit(test)', function(obj){
            var field = obj.field; // 得到字段
            var value = obj.value; // 得到修改后的值
            var data = obj.data; // 得到所在行所有键值
            // 值的校验
            if(field === 'email'){
                if(!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(obj.value)){
                    layer.tips('输入的邮箱格式不正确，请重新编辑', this, {tips: 1});
                    return obj.reedit(); // 重新编辑 -- v2.8.0 新增
                }
            }
            // 编辑后续操作，如提交更新请求，以完成真实的数据更新
            // …
            layer.msg('编辑成功', {icon: 1});

            // 其他更新操作
            var update = {};
            update[field] = value;
            obj.update(update);
        });
    });
})
export function updateBlogsStatus(selectedIds,status){
    request.post("/admin/updateBlogsStatus", {ids:selectedIds,status: status}).then(function(data){
        Swal.fire({
            type: 'warning', // 弹框类型
            title: '移动操作', //标题
            text: "移动成功！", //显示内容
            confirmButtonText: '确定',
        }).then(function(isConfirm) {
            location.reload();
        })
    })
}

export function updateBlogsRecommend(selectedIds,isRecommend){
    request.post("/admin/updateBlogsRecommend", {ids:selectedIds,isRecommend: isRecommend}).then(function(data){
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
    request.post("/admin/updateBlogsTop", {ids:selectedIds,isTop: isTop}).then(function(data){
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


$(document).ready(function() {
	selectTemp();
});

//初始化表格数据
var selectTemp = function() {
	$('#allBlog').bootstrapTable({
		method : 'post',
		url : "../selectGroupLikeTempListByPage",
		dataType : "json",
		striped : false, //使表格带有条纹
		pagination : true, //在表格底部显示分页工具栏
		pageSize : 10,
		pageNumber : 1,
		sortStable : true,
		sortable : true,
		pageList : [ 10, 20, 50 ],
		idField : "id", //标识哪个字段为id主键
		showToggle : false, //名片格式
		cardView : false, //设置为True时显示名片（card）布局
		showColumns : true, //显示隐藏列
		showRefresh : true, //显示刷新按钮
		//singleSelect: true,//复选框只能选择一条记录
		search : true, //是否显示搜索框
		searchOnEnterKey : true, //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
		//clickToSelect: true,//点击行即可选中单选/复选框
		queryParams : queryParams, //参数
		//showFullscreen:true,  //全屏按钮
		//queryParamsType: "limit", //查询参数组织方式
		sidePagination : "server", //服务端处理分页
		silent : true, //刷新事件必须设置
		searchTimeOut : 500, //设置搜索超时时间
		toolbarAlign : 'left', //工具栏对齐方式
		buttonsAlign : 'right', //按钮对齐方式
		toolbar : '#toolbar', //指定工作栏
		searchAlign : 'right',
		// singleSelect : true,
		contentType : "application/x-www-form-urlencoded",
		formatLoadingMessage : function() {
			return "请稍等，正在加载中...";
		},
		formatNoMatches : function() { //没有匹配的结果
			return "无符合条件的记录";
		},
		responseHandler : function(res) {
			return {
				"total" : res.pageInfo.total, //总页数
				"rows" : res.pageInfo.list //数据
			};
		},
		columns : [
			{
				checkbox : true,
				width : '3%',
				align : 'center',
				valign : 'middle',
			},
			{
				title : '序号',
				align : 'center',
				valign : 'middle',
				width : '4%',
				formatter : function(value, row, index) {
					var index1 = index + 1;
					var id = '<span title="ID:' + row.id + '">' + index1 + '</span>';
					return id;
				}
			},
			{
				title : '标题',
				field : 'title',
				align : 'center',
				valign : 'middle',
				width : '13%',
				cellStyle : formatTableUnit,
				formatter : operateOpinionFormatter
			},
			{
				title : '个数',
				align : 'center',
				width : '8%',
				formatter : function(value, row, index) {
					var sum = row.blogSum + "/" + row.blogAllSum;
					return sum;
				}
			},

			{
				title : '发表时间',
				field : 'createTime',
				align : 'center',
				width : '13%',
				formatter : function(value, row, index) {
					return Format(row.createTime, "yyyy-MM-dd hh:mm:ss");
				}
			},
			{
				title : '操作',
				field : 'id',
				align : 'center',
				width : '18%',
				formatter : function(value, row, index) {
					//编辑
					var b = '<a  class=" btn-sm btn-primary"  href="../temp/updateTemp.jsp?id=' + row.id + '" target="_blank"><i class="fa fa-edit" ></i> 编辑</a> ';
                    return b;
				}
			}
		]
	});
	globalCount++;
	returnAllCount();
}

//传参数到后台
function queryParams(params) {
	return {
		pageSize : params.limit,
		page : (params.offset) / params.limit + 1,
		title : $(".form-control").val(),
	}
}

//设置 字数不超过宽度限制
var operateOpinionFormatter = function(value, row, index) {
	var title = "";
	var content = $(".form-control").val();
	var contentLow = $(".form-control").val().toLowerCase();
	var strStartIndex = '';
	var strEndIndex = '';
	if (value.search(content) != -1 || value.toLowerCase().search(contentLow) != -1) {
		var strs = new Array();
		strs = value.split("");
		strStartIndex = value.indexOf(content);
		strEndIndex = strStartIndex + content.length - 1;
		if (value.toLowerCase().search(contentLow) != -1) {
			strStartIndex = value.toLowerCase().indexOf(contentLow);
			strEndIndex = strStartIndex + contentLow.length - 1;
		}
		for (var i = 0; i < strs.length; i++) {
			if (i >= strStartIndex && i <= strEndIndex) {
				title += '<span style="color:#000;font-weight:bold;">' + strs[i] + '</span>';
			} else {
				title += '<span >' + strs[i] + '</span>';
			}
		}
	} else {
		title = value;
	}
	if (value.length > 20) {
		var num = strEndIndex - strStartIndex;
		var index = strStartIndex - 4;
		if (index < 0) {
			index = 0;
		}
		if (content == "") {
			return "<span title='" + value + "'>" + title.substring(0, 150) + "..." + "</span>";
		} else {
			return "<span title='" + value + "'>" + title.substring(15 * index, (15 * index) + (num + 1) * 51 + (9 - num) * 15) + "..." + "</span>";
		}
	} else {
		return "<span  title='" + value + "'>" + title.substring(0, title.length) + "</span>";
	}
}

var formatTableUnit = function(value, row, index) {
	return {
		css : {
			"overflow" : 'hidden',
			"text-overflow" : 'ellipsis',
			"white-space" : 'nowrap'
		}
	};
};

//格式化时间
function Format(datetime, fmt) {
	if (parseInt(datetime) == datetime) {
		if (datetime.length == 10) {
			datetime = parseInt(datetime) * 1000;
		} else if (datetime.length == 13) {
			datetime = parseInt(datetime);
		}
	}
	datetime = new Date(datetime);
	var o = {
		"M+" : datetime.getMonth() + 1, //月份
		"d+" : datetime.getDate(), //日
		"h+" : datetime.getHours(), //小时
		"m+" : datetime.getMinutes(), //分
		"s+" : datetime.getSeconds(), //秒
		"q+" : Math.floor((datetime.getMonth() + 3) / 3), //季度
		"S" : datetime.getMilliseconds() //毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

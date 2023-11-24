import request from "/upload/js/axios-config.js";

$(document).ready(function() {
	initBlogCountByStatus() //初始化已发表、草稿、储备
	initBlogCountByDate() //初始化今天、明天的文章
	initVisitCountByDate() //初始化访问表格
	//获取今日访客
	initVisitNowCount();
	//获取历史访客
	initVisitCount();
});

function initVisitCountByDate() {
	var currentDate = new Date();
	var start = currentDate.getTime() - 15 * 24 * 60 * 60 * 1000;
	var end = currentDate.getTime();
	var num = Math.abs(parseInt((end - start) / 1000 / 3600 / 24));
	request.get('/admin/echars/log-data?start='+start+"&end="+end).then(function(res){
		initEchartsByVisit(res.data.data.days, res.data.data.counts,num);
	});
}

function initVisitCount(){
	request.get('/admin/log/log-counts').then(function (data) {
		$(".visitors").html(data.data.data);
	})
}

function initVisitNowCount(){
	request.get('/admin/log/log-now').then(function (data) {
		$(".nowVisitors").html(data.data.data);
	})
}

function initEchartsByVisit(days, counts,num){
	var chartDom = document.getElementById('echarts-line-visit');
	var myChart = echarts.init(chartDom);
	var option;

	option = {
		title: {
			text: '网站访问人数'
		},
		tooltip: {
			trigger: 'axis'
		},
		legend: {
			data: ['visit']
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		toolbox: {
			feature: {
				saveAsImage: {}
			}
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: days
		},
		yAxis: {
			type: 'value'
		},
		series: [
			{
				name: 'visit',
				type: 'line',
				stack: 'Total',
				data: counts
			}
		]
	};
	option && myChart.setOption(option);
}

//初始化已发表的文章
function initBlogCountByStatus(){
	//初始化博客数目
	request.get('/admin/blog/type-counts').then(function (data) {
		var responseData = data.data.data;
		//草稿
		$(".draft").html(responseData.draftCounts);
		var draftPercent = Math.round(responseData.allCounts / responseData.allCounts * 100) / 1.00 + '%<i class="fa fa-bolt"></i>';
		$(".draftPercent").html(draftPercent);
		//已发表
		$(".allBlog").html(responseData.publishedCounts);
		var allBlogPercent = Math.round(responseData.allCounts / responseData.allCounts * 100) / 1.00 + '%<i class="fa fa-bolt"></i>';
		$(".allBlogPercent").html(allBlogPercent)
		//垃圾箱
		$(".delete").html(responseData.spmCounts);
		var deletePercent = Math.round(responseData.allCounts / responseData.allCounts * 100) / 1.00 + '%<i class="fa fa-bolt"></i>';
		$(".deletePercent").html(deletePercent);
		//储备
		$(".reserced").html(responseData.resercedCounts);
		var resercedPercent = Math.round(responseData.allCounts / responseData.allCounts * 100) / 1.00 + '%<i class="fa fa-bolt"></i>';
		$(".resercedPercent").html(resercedPercent);
	})
}

function initBlogCountByDate(){
	//初始化博客数目
	request.get('/admin/blog/date-counts',{params:{
			preNum: 3
		}}).then(function (data) {
		var responseData = data.data.data;
		$(".nowBlog").html(responseData.blogDateCounts[0]);
		$(".nowBlogPercent").html(level(responseData.blogDateCounts[0], responseData.blogDateCounts[1]));
		$(".yesBlog").html(responseData.blogDateCounts[1]);
		$(".yesBlogPercent").html(level(responseData.blogDateCounts[1], responseData.blogDateCounts[2]));
	})
}

//根据今天/昨天 计算出 增长率/下降率
var level = function(now, yes) {
	var pm = now - yes;
	var level = '<i class="fa fa-level-up"></i>';
	if (pm < 0) {
		pm = -pm;
		level = '<i class="fa fa-level-down"></i>';
	}
	if (yes == 0) {
		return Math.round(pm * 100) / 1.00 + '%' + level;
	}
	return Math.round(pm / yes * 100) / 1.00 + '%' + level;
}

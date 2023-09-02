import request from "../axios/axios-config.js";

$(document).ready(function() {
	initBlogCountByStatus() //初始化已发表、草稿、储备
	initBlogCountByDate() //初始化今天、明天的文章
});

//初始化已发表的文章
function initBlogCountByStatus(){
	//初始化博客数目
	request.get('/admin/blogTypeCounts').then(function (data) {
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
	request.get('/admin/blogDateCounts',{params:{
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

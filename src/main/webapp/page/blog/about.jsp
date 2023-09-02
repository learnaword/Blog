<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>手机赚钱软件专区</title>
	<link rel="shortcut icon" href="/static/images/favicon.ico">
	<meta name="keywords" content="手机兼职,手机赚钱软件,免费赚钱" />
	<meta name="description" content="手机赚钱软件专区:在这里有大量的手机免费赚钱赚钱软件，软件专区功能，在这里你将充分了解这个软件，如何赚钱，能赚到多少，有什么优点和缺点等等，尽我最大可能让你找到适合自己的网赚方式。" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="/static/css/style.css" rel="stylesheet">
	<link href="/static/css/loaders.css" rel="stylesheet">

	<style type="text/css">
		.loader-inner>div {
			background-color: #907f819e
		}

		.whitebg {
			background: #fff;
			border-radius: 3px;
			padding: 20px;
			overflow: hidden;
		}

		.lanmu img {
			height: 100px;
			float: left;
			margin-right: 20px;
		}


		.lanmu p {
			color: #666;
		}
	</style>
</head>
<body
		style="background:url(/images/bj.png) repeat top left scroll">


<head>
	<link rel="shortcut icon" href="/static/images/favicon.ico">
	<link href="https://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="/static/css/common.min.css" rel="stylesheet">
	<script src="https://hm.baidu.com/hm.js?f655f558c510211e38805f6b586e6b15"></script>
	<script src="https://cdn.staticfile.org/jquery/1.8.3/jquery.min.js"></script>
	<script src="/static/js/comm.js"></script>
	<style>
		* {
			cursor: url('/images/ani/a.cur'), auto;
		}

		a {
			cursor: url('/images/ani/b.cur'), auto;
		}
	</style>
</head>
<header id="header">
	<div class="navbox">
		<h2 id="mnavh">
			<span class="navicon"></span>
		</h2>
		<div class="logo">
			<a href="/">&nbsp;网上赚钱 | <font class="font16">真正想做的事，只要开始就不会晚！</font></a>
		</div>
		<nav>
			<ul id="starlist">
				<li><a href="https://www.bangmangma.com" title="首页" >首页</a></li>
				<li><a href="/list">网赚博客</a> </li>
				<li class="menu"><a href="/list">网赚专栏</a>
					<ul class="sub">
						<li></li>
					</ul> <span></span></li>
				<!-- <li><a href="/resource">资源下载</a></li>-->
				<li><a href="/soft">手机赚钱软件</a></li>
				<li><a href="/find/130.html">网站介绍</a></li>
				<li><a href="/find/61997.html">关于网赚</a></li>
				<li><a href="/message">留言</a></li>
				<li><a href="/login">登录</a></li>
			</ul>
		</nav>
		<div class="searchico"></div>
	</div>
</header>

<div class="searchbox">
	<div class="search">
		<form action="/result" name="searchform" method="post"
			  id="searchform">
			<input name="keyboard" id="keyboard" class="input_text"
				   value="请输入关键字词" style="color: rgb(153, 153, 153);"
				   onfocus="if(value=='请输入关键字词'){this.style.color='#000';value=''}"
				   onblur="if(value==''){this.style.color='#999';value='请输入关键字词'}"
				   type="text"> <input name="Submit" class="input_submit"
									   value="搜索" type="submit">
		</form>
	</div>
	<div class="searchclose"></div>
</div>
<script type="text/javascript" color="255,140,0" opacity='0.7'
		zIndex="-1" count="99"
		src="//cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<script type="text/javascript">
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "https://hm.baidu.com/hm.js?f655f558c510211e38805f6b586e6b15";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();

	//初始化所有类别信息
	var initAllBlogType = function() {
		//查询出文章类别
		//设置参数，表示查询所有的类别
		var params = {
			"data" : "all"
		};
		$.ajax({
			url : '/selectBlogType',
			type : 'get',
			data : params,
			dataType : 'json',
			success : function(data) {
				var typeName = '';
				for (var i = 0; i < data.length; i++) {
					typeName += "<li><a href='/result?keyboard=type_"+data[i].id +"'>"+ data[i].typename + "</a></li>";
					//typeName += '<a onclick="searchType(' + data[i].id + ',\'' + data[i].typename + '\')" href="javascript:void(0);">' + data[i].typename + '</a> '
				}
				$(".sub").html(typeName);
			},
			error : function() {
				layer.msg('请求太快，请稍后再试！', {
					icon : 5
				});
			}
		});
	}
	/*鼠标特效 */
	/*这个方法用来随机一个十六进制颜色代码，让每一次点击浮动文字的杨色不同*/
	function co() {
		var colorElements = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f";
		var colorArray = colorElements.split(",");
		var color = "#";
		for (var i = 0; i < 6; i++) {
			color += colorArray[Math.floor(Math.random() * 16)];
		}
		return color;
	}
	var a_idx = 0;

	jQuery(document).ready(function($) {
		initAllBlogType();
		$("body").click(function(e) {
			/*这个数组中的每一个字符是你要浮动显示的词或句子，每次点击鼠标后按顺序出现*/
			var a = new Array("去软件专区", "快点去", "别点了", "你有本事接着点", "有种你还点", "去软件专区", "听话，去软件专区可以吗");
			var $i = $("<span/>").text(a[a_idx]);
			a_idx = (a_idx + 1) % a.length;
			var x = e.pageX,
					y = e.pageY;
			$i.css({
				"z-index" : 999999,
				"top" : y - 20,
				"left" : x,
				"position" : "absolute",
				"font-weight" : "bold",
				"color" : co()
			});
			$("body").append($i);
			$i.animate({
						"top" : y - 180,
						"opacity" : 0
					},
					1500,
					function() {
						$i.remove();
					});
		});
	});
</script>
</header>

<article>
	<div class="lbox">
		<div class="whitebg lanmu">
			<img src="/static/images/lm01.jpg">
			<h3>秘密与技巧</h3>
			<p>玩网赚你不知道的秘密与技巧，教你如何网赚，让你更了解网赚。</p>
		</div>
	</div>
	<div class="rbox">
		<input name="keyword" id="keyword" value="type_48"
			   type="hidden">
		<div class="tuijian2 animated fadeIn whitebg">
			<h2 class="cloud_hometitle">推荐文章</h2>
			<ul class="tjpic animated fadeIn">
				<i><img src="images/t03.jpg"></i>
			</ul>
			<ul class="sidenews">

			</ul>
		</div>

		<div class="djpaihang dj whitebg dianji animated fadeIn"
			 style="display:none;animation-delay:0.3s">
			<h2 class="cloud_hometitle">点击排行</h2>
			<ul class="click">

			</ul>

		</div>
		<div class="guanzhu gd whitebg animated fadeIn" style="display:none"
			 id="follow-us">
			<h2 class="cloud_hometitle">来波关注</h2>
			<ul>
				<li class="qq"><a href="javascript:void(0)"
								  target="_blank"><span>QQ群号</span>121233155</a></li>
				<li class="email"><a href="javascript:void(0)"
									 target="_blank"><span>邮箱帐号</span>875657453@qq.com</a></li>
				<li class="wxgzh"><a href="javascript:void(0)"
									 target="_blank"><span>微信号</span>gnalnujam</a></li>
			</ul>
		</div>
	</div>
	<a href="#" class="top cd-top animated ">Top</a>
</article>

<footer>
	<div class="container"><p>Design by 网站备案号: <a href="/">京ICP备18055140号-2</a><center><a href="http://qutt.bangmangma.com">趣头条邀请码</a>&nbsp;|&nbsp;<a href="http://bangmangle.cn/MyBlog/detail/27/invite">众人帮邀请码</a>&nbsp;|&nbsp;<a href="http://bangmangle.cn">今日头条极速版邀请码</a>&nbsp;|&nbsp;<a href="http://www.bangmangma.cn">快手极速版邀请码</a><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? "https://" : "http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1277821891'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s96.cnzz.com/z_stat.php%3Fid%3D1277821891%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script><center></p></div>
</footer>

<script src="/static/js/page/about.js"></script>
<script src="/static/js/layer/layer.js"></script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header id="header" style="margin:0px;">
  <div class="navbox">
 <h2 id="mnavh">
      <span class="navicon"></span>
    </h2>
    <div class="logo">
         <a href="https://www.bangmangma.com"><img class="imgTop" src="/images/logo.jpg"></a>
    </div>
    <nav class="leftNav">
      <ul id="starlist">
        <li><a href="https://www.bangmangma.com/" title="首页" >首页</a></li>
        <li><a href="https://www.bangmangma.com/soft.html" rel="nofollow">经验分享</a></li>
        <li><a href="https://www.bangmangma.com/media/media.html" rel="nofollow">自媒体</a></li>
      </ul>
    </nav>
     <div class="searchico"></div>
  </div>
</header>
<div class="searchbox">
  <div class="search">
    <form action="${pageContext.request.contextPath}/result" name="searchform" method="post"
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


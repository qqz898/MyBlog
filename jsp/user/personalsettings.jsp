<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>

    <!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8">
	<title>个人中心</title>
	
    <!-- Mobile Specific Metas
  ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="images/websiteicon.png">
    <!-- CSS
  ================================================== -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zerogrid-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-userself.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsiveslides-userself.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fileinput.css" />	<!--[if lt IE 8]>
       <div style=' clear: both; text-align:center; position: relative;'>
         <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
           <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
      </div>
    <![endif]-->
    <!--[if lt IE 9]>
		<script src="js/html5.js"></script>
		<script src="js/css3-mediaqueries.js"></script>
	<![endif]-->
	
	<link href='${pageContext.request.contextPath}/images/websiteicon.png' rel='icon' type='image/x-icon'/>
	
	
	    
</head>
<body>
<!--------------Header--------------->
<header> 
	<div class="zerogrid">
		<div class="row">
			<div class="col05" style="margin-top:-5px;padding-top:0px;">
				<div id="logo"><a href="${pageContext.request.contextPath}/user/mainpage"><img src="${pageContext.request.contextPath}/images/bloglogo.png"/></a></div>
			</div>
			<div class="col06 offset05">
			   <div id='search-box'>
				  <form actn='' id='search-form' method='get' target='_top'>
					<input id='search-text' name='q' placeholder='type here' type='text'/>
					<button id='search-button' type='submit'><span>Search</span></button>
				  </form>
				</div>
			</div>
		</div>
	</div>
</header>



<!--------------Navigation--------------->

<nav>
	<ul>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/mainpage">回到主页</a></li>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/topersonalsettings">个人资料</a></li>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/tochangepersonalinfo">修改资料</a></li>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/tochangepersonalpwd">修改密码</a></li>
		<li style="float:right;margin-left:0px;margin-right:5px"><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/logout">注销</a></li>
		<li style="float:right;margin-left:5px;margin-right:0px"><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/topersonalsettings">${loginuser.name}</a></li>
		
	</ul>
</nav>
<div style="margin: 0 auto;width: 960px;">
	<div>
		<center>
			<span style="font-size: 30px;">个人资料</span>
		</center>		
	</div><hr>
	<div style="float: left;width: 200px">
		<center>
			<img src="${loginuser.icon }" width="150px" onerror="javascript:this.src='${pageContext.request.contextPath}/images/touxiang.jpg'" height="150px" /><br><br><br>
			<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/user/uploadicon" method="post">
				<input id="personIcon" name="personIcon" type="file" class="file-loading">				
				<br><p style="color: red;">*请不要上传中文命名的图片!</p>
			</form>
			
		</center>
	</div>
	
	<div style="float:left;margin-left: 50px;width: 660px;">
		<span style="font-size: 20px;">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span><span style="font-size: 20px;margin-left: 50px">${loginuser.name}</span><br><br>
		<span style="font-size: 20px;">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span><span style="font-size: 20px;margin-left: 50px">${loginuser.nickname}</span><br><br>
		<span style="font-size: 20px;">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</span><span style="font-size: 20px;margin-left: 50px">${loginuser.email}</span><br><br>
		<span style="font-size: 20px;">个人简介：</span><span style="font-size: 20px;margin-left: 50px;">${loginuser.introduction }</span><br><br>
	</div>
	<div style="height: 300px;"></div>
</div>
<!--------------Footer--------------->


<div id="copyright">
	<p>京ICP备17020361号　｜　Copyright © 2016 - Daniel Qiu</p>
	<p>All rights reserved</p>
</div>

<script src="${pageContext.request.contextPath}/js/lib/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/responsiveslides-userself.js"></script>

<script src="${pageContext.request.contextPath}/js/fileinput.js"></script>
<script>
	$(document).on('ready', function() {
	    $("#personIcon").fileinput({
	    	language: 'zh', //设置语言
	    	showCaption: false,
	    	browseClass: "btn btn-primary", //按钮样式  
	    	allowedFileExtensions: ['jpg', 'gif', 'png', 'jpeg', 'bmp'],//接收的文件后缀	
	    	maxFileSize: 2000,//单位为kb，如果为0表示不限制文件大小
	    	maxFileCount: 1, //表示允许同时上传的最大文件个数
	    	msgFilesTooMany: "最多只能上传一个头像！",
	    	browseLabel: '上传新头像'
	    });
	});
</script>
</body></html>
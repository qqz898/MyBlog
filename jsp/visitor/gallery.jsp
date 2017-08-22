<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>


	<title>${hostuser.name}-相册-${visitor_selected_album.album_name}</title>
	<link href="${pageContext.request.contextPath}/css/gallery/bootstrap.css" rel='stylesheet' type='text/css' />
	<!-- jQuery (necessary JavaScript plugins) -->
	
	<!-- Custom Theme files -->
	 <link href="${pageContext.request.contextPath}/css/gallery/dashboard.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/gallery/style.css" rel='stylesheet' type='text/css' />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/gallery/lightbox.css">
	<!--//Custom Theme files -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Rissner Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
	
	
	<style type="text/css">
	.onephoto{
		float: left;
		position: relative;
		width: 285px;
		height: 230px;
		margin-left: 20px;
		margin-top: 20px; 
	}
	.oneimg{
		position:absolute;
		top:0;
		left:0;
		width:100%;
		height:100%;
		z-index:1;
	}
	.delclass{
		visibility:hidden;
		position:absolute;
		float: right;
		top:0;
		left:0;
		z-index:2;
	}
	.onephoto:hover .delclass{
		background-color：rgba(0,0,0,0.8);
		visibility:visible;
	}
</style>



	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zerogrid-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-userself.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsiveslides-userself.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fileinput.css" />	<!--[if lt IE 8]>
       
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
			<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/visitor/${hostuser.user_id }/mainpage">主页</a></li>
			<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/visitor/${hostuser.user_id }/articlelist/1">查看博客</a></li>
			<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/visitor/${hostuser.user_id }/toalbumpage">相册</a></li>
			<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/visitor/${hostuser.user_id}/tomessageboard">留言版</a></li>
			<li style="float:right;margin-left:0px;margin-right:5px"><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/logout">注销</a></li>
			<li style="float:right;margin-left:5px;margin-right:0px"><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/topersonalsettings">${loginuser.name}</a></li>
			
		</ul>
	</nav>


<div style="margin: 0 auto; min-height:500px; text-align: center;">
	 <div class="gallery">
	 	<h3>Gallery</h3>
	 	<center><span>相册介绍：${visitor_selected_album.album_intro }</span></center><br><br>
		 
		 <div class="gallery-bottom">
				<div class="gallery-1">
					<c:forEach items="${hostphotoList}" var="photo">
						<div class="onephoto">
							<a class="example-image-link" href="${photo.photo_url}" data-lightbox="example-set">
								<img class="oneimg" width="285px" height="230px" src="${photo.photo_url}" onerror="javascript:this.src='${pageContext.request.contextPath}/images/gallery/pic7.jpg'">
							</a>
						</div>												
					</c:forEach>										
				</div>							
		 </div>
	 </div>
	</div>
	<script src="${pageContext.request.contextPath}/js/gallery/lightbox-plus-jquery.min.js"></script>
		
<!--------------Footer--------------->


<div id="copyright" style="margin-top: 100px;">
	<p>京ICP备17020361号　｜　Copyright © 2016 - Daniel Qiu</p>
	<p>All rights reserved</p>
</div>
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<script src="${pageContext.request.contextPath}/js/lib/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/responsiveslides-userself.js"></script>
	<script src="${pageContext.request.contextPath}/js/fileinput.js"></script>	
</body></html>

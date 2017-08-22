<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html class="no-js"> 
	<head>
	
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>相册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" /> 	

  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/websiteicon.png"> 
	<!-- Animate.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/album/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/album/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/album/bootstrap.css">
	<!-- Owl Carousel -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/album/owl.carousel.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/album/owl.theme.default.min.css">
	<!-- Theme style  -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/album/style.css">
	
	
	
	<script type="text/javascript">
	//提交校验
		function dosubmit() {
			  
		     var alintro = document.getElementById('album_intro').value;
		      var alname = document.getElementById('album_name').value;		      
		     
		      if(alname=="") {
		    	  alert("相册名不能为空！");
		    	  return false;
		      }
		      if(alintro==""){
		    	  alert("相册介绍不能为空！");
		    	  return false;
		      }
		      return true;
		           
		  }
	</script>
	</head>
	<body>
	
	
	<!-- 头部JS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zerogrid-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-userself.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsiveslides-userself.css" />
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	
	<!-- 头部 -->
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

	<nav>
		<ul>
			<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/mainpage">主页</a></li>
			<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/${loginuser.name}/articlelist/1">查看博客</a></li>
			<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/${loginuser.name}/towrite">写博客</a></li>
			<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/${loginuser.name}/toalbumpage">相册</a></li>
			<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/${loginuser.name}/tomessageboard">留言版</a></li>
			<li style="float:right;margin-left:0px;margin-right:5px"><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/logout">注销</a></li>
			<li style="float:right;margin-left:5px;margin-right:0px"><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/topersonalsettings">${loginuser.name}</a></li>
			
		</ul>
	</nav>
	
	
	<center>
	<div style="width: 1150px;margin: 0 auto;text-align: center;">	
	<h2 style="color: #83d5d1;"><strong>我的相册</strong></h2>
	<button style="float: right;" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
	新建相册
	</button>
<br><br>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						新建相册
					</h4>
				</div>
				<div class="modal-body">
					<div style="margin-left: 10px;">
					<form method="post" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/${loginuser.name}/buildanalbum">
						<div class="form-group">
							<label style="float: left;" for="firstname">相册名：&nbsp;&nbsp;&nbsp;</label>
							<div style="float: left;">								
								<input name="album_name" type="text" class="form-control" id="album_name" 
									   placeholder="请输入相册名称">
							</div>
						</div>						
						<div class="form-group">
						    <label for="name" style="float: left;">相册简介:&nbsp;&nbsp;</label>
						    <div style="float: left; width: 400px;">
						    	<textarea id="album_intro" name="album_intro" class="form-control" rows="3"></textarea>
						    </div>
					    </div>
						<div class="form-group">							
							<div style="float: left;" class="checkbox">
								<label>
									<input type="checkbox" name="ifsecured" value="yes">私密相册
								</label>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭
							</button>
							<button type="submit" onclick="return dosubmit()" class="btn btn-primary">
								提交
							</button>
						</div>
					</form>
					</div>
				</div>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	
	
	<!-- 内容主体 -->
	<div id="fh5co-page" style="min-height: 500px">
		<div id="fh5co-main">
			<div class="fh5co-gallery">
				<c:forEach items="${albumList}" var="album">					
					<a class="gallery-item" href="${pageContext.request.contextPath}/user/${loginuser.name}/album/${album.album_id}">
						<div style="width: 340px;height: 260px;">
							<img src="${album.album_frontcover}" width="340px;" height="260px;" onerror="javascript:this.src='${pageContext.request.contextPath}/images/album/work_6.jpg'">
						</div>
						<span class="overlay">
							<h2>${album.album_name}</h2>
							<span>${album.album_intro}</span>
						</span>							
					</a>											
				</c:forEach>				
			</div>
		</div>
	</div>
	
	</div>
	</center>
	
	
	<!-- 底部 -->
	<!--------------Footer--------------->


	<div id="copyright">
		<p>京ICP备17020361号　｜　Copyright © 2016 - Daniel Qiu</p>
		<p>All rights reserved</p>
	</div>

<script src="${pageContext.request.contextPath}/js/album/modernizr-2.6.2.min.js"></script>

	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/js/lib/jquery-1.10.2.min.js"></script>
	<!-- jQuery Easing -->
	<script src="${pageContext.request.contextPath}/js/album/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/js/album/bootstrap.min.js"></script>
	<!-- Carousel -->
	<script src="${pageContext.request.contextPath}/js/album/owl.carousel.min.js"></script>
	<!-- Stellar -->
	<script src="${pageContext.request.contextPath}/js/album/jquery.stellar.min.js"></script>
	<!-- Waypoints -->
	<script src="${pageContext.request.contextPath}/js/album/jquery.waypoints.min.js"></script>
	<!-- Counters -->
	<script src="${pageContext.request.contextPath}/js/album/jquery.countTo.js"></script>
	
	
	<!-- MAIN JS -->
	<script src="${pageContext.request.contextPath}/js/album/main.js"></script>
	</body>
</html>


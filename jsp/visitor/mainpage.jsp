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
	<title>${hostuser.name }的主页</title>
	
    <!-- Mobile Specific Metas
  ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/websiteicon.png">
    <!-- CSS
  ================================================== -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zerogrid-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-userself.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsiveslides-userself.css" />
	
	<!--[if lt IE 8]>
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
	
	<script src="${pageContext.request.contextPath}/js/jquery.min-userself.js"></script>
	<script src="${pageContext.request.contextPath}/js/responsiveslides-userself.js"></script>
	<script>
    $(function () {
      $("#slider").responsiveSlides({
        auto: true,
        pager: true,
        nav: true,
        speed: 500,
        maxwidth: 800,
        namespace: "centered-btns"
      });
    });
  </script>
    
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
		<li><a style="text-decoration: none" href="${pageContext.request.contextPath}/visitor/${hostuser.user_id }/mainpage">主页</a></li>
		<li><a style="text-decoration: none" href="${pageContext.request.contextPath}/visitor/${hostuser.user_id }/articlelist/1">查看博客</a></li>
		<li><a style="text-decoration: none" href="${pageContext.request.contextPath}/visitor/${hostuser.user_id }/toalbumpage">相册</a></li>
		<li><a style="text-decoration: none" href="${pageContext.request.contextPath}/visitor/${hostuser.user_id}/tomessageboard">留言版</a></li>
		<li style="float:right;margin-left:0px;margin-right:5px"><a style="text-decoration: none" href="${pageContext.request.contextPath}/logout">注销</a></li>
		<li style="float:right;margin-left:5px;margin-right:0px"><a style="text-decoration: none" href="${pageContext.request.contextPath}/user/topersonalsettings">${loginuser.name}</a></li>
		
	</ul>
</nav>

<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >Website Template</a></div>	
<!--------------Content--------------->
<section id="content">
	<div class="zerogrid">
		<div class="row block">
			<div class="main-content col11">
			
				<div class="rslides_container">
					<ul class="rslides" id="slider">
						<li><img src="${pageContext.request.contextPath}/images/1.jpg"/></li>
						<li><img src="${pageContext.request.contextPath}/images/2.jpg"/></li>
						<li><img src="${pageContext.request.contextPath}/images/3.jpg"/></li>
						<li><img src="${pageContext.request.contextPath}/images/4.jpg"/></li>
					</ul>
				</div>
				
				<c:forEach items="${hostrecentfourblog}" var="mainpageblog">
					<article>
						<div class="heading">
							<h2><a href="${pageContext.request.contextPath}/visitor/${loginuser.name}/articlelist/read/${mainpageblog.blog_id}">${mainpageblog.title }</a></h2>
							<p class="info">>>> Posted by ${hostuser.name } - ${mainpageblog.time } - &nbsp;${mainpageblog.comment_num } Comments</p>
						</div>
						<div class="content">	
							<div style="text-overflow:ellipsis;overflow:hidden;height: 260px;">						
								<p>${mainpageblog.content }</p>
							</div>
						</div>
						<div class="footer">
							<p class="more"><a class="button" href="${pageContext.request.contextPath}/visitor/${loginuser.name}/articlelist/read/${mainpageblog.blog_id}">Read more >></a></p>
						</div>
					</article>
					<hr>
				</c:forEach>	
			</div>
			
			<div class="sidebar col05">
				<section>
					<div class="heading">关于博主</div>
					<div class="content">
						
							<span style="font-size: 15px; width: 280px;">
							<strong>用户ID：</strong>&nbsp;&nbsp;&nbsp;${hostuser.name } <br>
							<strong>昵&nbsp;&nbsp;称：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${hostuser.nickname }<br>
							<strong>Email：</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${hostuser.email }<br>
							<strong>个人简介：</strong>${hostuser.introduction }
							</span>						
					</div>
				</section>
				<section>
					<div class="heading">最近文章</div>
					<div class="content">
						<ul class="list">
							<c:forEach items="${hostrecentfourblog}" var="mainpageblog">
								<li><a href="${pageContext.request.contextPath}/visitor/${loginuser.name}/articlelist/read/${mainpageblog.blog_id}">${mainpageblog.title }</a></li>
							</c:forEach>							
						</ul>
					</div>
				</section>	
				<section>
					<div class="heading">最新相册</div>
					<div class="content">
						<c:forEach items="${hostmainPageAlbum}" var="mainpagealbum">							
							<div style="display: block;">
							<section>
								<img src="${mainpagealbum.album_frontcover}" width="65px;" height="65px;" onerror="javascript:this.src='${pageContext.request.contextPath}/images/album/work_6.jpg'">
								<h4><a href="${pageContext.request.contextPath}/visitor/${hostuser.name}/album/${mainpagealbum.album_id}">${mainpagealbum.album_name }</a></h4>
								<p>${mainpagealbum.album_intro }</p>
							</section>	
							</div>			
						</c:forEach>						
					</div>
				</section>
				<section>
					<div class="heading">站上用户</div>
					<div class="content">
					<ul class="list">
						<c:forEach items="${allUser}" var="otherperson">
							<li><a href="${pageContext.request.contextPath}/visitor/${otherperson.user_id}/mainpage">${otherperson.name}</a></li>
						</c:forEach>
					</ul>
					</div>
				</section>
			</div>
			
		</div>
	</div>
</section>
<!--------------Footer--------------->


<div id="copyright">
	<p>京ICP备17020361号　｜　Copyright © 2016 - Daniel Qiu</p>
	<p>All rights reserved</p>
</div>

</body></html>
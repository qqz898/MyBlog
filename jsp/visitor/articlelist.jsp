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
	<title>${hostuser.name }的博客</title>
	
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

<div style="width: 150px;float: left;margin-left: 10px;">
<!-- 
	<p style="text-align: center;font-size: 18px">博客分类</p>
	 -->
	<ul class="nav nav-pills nav-stacked">
		<li style="text-align: center;" class="active">
			<a href="${pageContext.request.contextPath}/visitor/${hostuser.user_id }/articlelist/1">所有博客</a>		
	    </li>
	    <c:forEach items="${artCatList}" var="artCat">
			<li style="text-align: center;"><a href="${pageContext.request.contextPath}/visitor/${loginuser.name}/articlecatlog/${artCat.art_cat_id}/1"/>${artCat.cat_name }</a></li>
		</c:forEach>
		
	</ul>		
</div>

<!-- 文章列表 -->
<div style="margin: 0 auto;min-height:500px;width: 960px;">
	<ul class="list-group">	
		<c:forEach items="${blogList}" var="bloghis">
			<li class="list-group-item">
			<a href="${pageContext.request.contextPath}/visitor/${loginuser.name}/articlelist/read/${bloghis.blog_id}">${bloghis.title}</a>			
			<span style="float: right;margin-right: 30px">${bloghis.formatdate}</span>			
			</li>
		</c:forEach>
	</ul>
	<hr>
	<center>
	<ul class="pagination">
		<!-- <li><a href="${pageContext.request.contextPath}/user/${loginuser.name}/articlelist/${nowpagenum-1}">&laquo;</a></li>	 -->	
		<!-- 分页导航栏 -->
		<c:forEach var="i" begin="1" end="${totalpage}">	
			<li><a href="${pageContext.request.contextPath}/visitor/${hostuser.user_id}/articlelist/${i}">${i }</a></li>
		</c:forEach>
		
		<!-- <li><a href="${pageContext.request.contextPath}/user/${loginuser.name}/articlelist/${nowpagenum+1}">&raquo;</a></li> -->
	</ul>
	</center>
	

</div>



<!--------------Footer--------------->


<div id="copyright">
	<p>京ICP备17020361号　｜　Copyright © 2016 - Daniel Qiu</p>
	<p>All rights reserved</p>
</div>
	<script src="${pageContext.request.contextPath}/js/lib/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/responsiveslides-userself.js"></script>
	<script type="text/javascript">
		function del(){
			var k = window.confirm("您确定要删除吗？"); //返回true或false
			if(k){
				event.returnValue = true;
				}else{
					event.returnValue = false;
					}
		}
	</script>
    
</body></html>
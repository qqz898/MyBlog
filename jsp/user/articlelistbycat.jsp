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
	<title>我的博客-${artCatNow.cat_name}</title>
	
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
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/mainpage">主页</a></li>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/${loginuser.name}/articlelist/1">查看博客</a></li>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/${loginuser.name}/towrite">写博客</a></li>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/${loginuser.name}/toalbumpage">相册</a></li>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/${loginuser.name}/tomessageboard">留言版</a></li>
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
		<a href="${pageContext.request.contextPath}/user/${loginuser.name}/articlelist/1">所有博客</a>		
    </li>
    <c:forEach items="${artCatList}" var="artCat">
		<li style="text-align: center;"><a href="${pageContext.request.contextPath}/user/${loginuser.name}/articlecatlog/${artCat.art_cat_id}/1">${artCat.cat_name }</a></li>
	</c:forEach>
	<li style="text-align: center;" class="divider"></li>
	<li style="text-align: center;" class="active">
		<a href="#" data-toggle="modal" data-target="#myModal">新建分类</a>
	</li>
	<li style="text-align: center;" class="active">
		<a href="#" data-toggle="modal" data-target="#myModal1">删除分类</a>
	</li>
</ul>

		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							新建博客分类
						</h4>
					</div>
					<div class="modal-body">
						<div style="margin-left: 10px;">
							<form method="post" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/${loginuser.name}/buildanartcat">
								<div class="form-group">
									<label style="float: left;" for="firstname">分类名称：&nbsp;&nbsp;&nbsp;</label>
									<div style="float: left;">								
										<input name="art_cat" type="text" class="form-control" id="art_cat" 
											   placeholder="请输入分类名称(小于10字)">
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
		
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							删除博客分类
						</h4>
					</div>
					<div class="modal-body">
						<div style="margin-left: 10px;">
							<form method="post" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/${loginuser.name}/deleteartcat">
								<div class="form-group">
									<label style="float: left;" for="firstname">分类名称：&nbsp;&nbsp;&nbsp;</label>
									<div style="float: left;">
										<c:forEach items="${artCatList}" var="artCat">								
											<div class="radio">
												<label>
													<input type="radio" name="optionsRadios" id="optionsRadios" value="${artCat.art_cat_id}" checked> ${artCat.cat_name }
												</label>
											</div>
										</c:forEach>
									</div>
								</div>												
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭
									</button>
									<button type="submit" onclick="dosubmitdelcat()" class="btn btn-primary">
										删除
									</button>
								</div>
							</form>
							</div>
					</div>					
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
</div>

<!-- 文章列表 -->
<div style="margin: 0 auto;width: 960px;min-height: 500px;">
	<ul class="list-group">	
		<c:forEach items="${artListByCat}" var="bloghis">
			<li class="list-group-item">
			<a href="${pageContext.request.contextPath}/user/${loginuser.name}/articlelist/read/${bloghis.blog_id}">${bloghis.title}</a>			
			<a onclick="del()" style="float: right;margin-left: 15px" href="${pageContext.request.contextPath}/user/${loginuser.name}/articlelist/delete/${bloghis.blog_id}">删除</a>
			<a style="float: right;margin-left: 15px" href="${pageContext.request.contextPath}/user/${loginuser.name}/articlelist/modify/${bloghis.blog_id}">修改</a>			
			<span style="float: right;margin-right: 30px">${bloghis.formatdate}</span>
			<span style="float: right;margin-right: 30px;color: #FF6600;font-style: italic;">${bloghis.privateinfo}</span>			
			</li>
		</c:forEach>
	</ul>
	<hr>
	<center>
	<ul class="pagination">
		<!-- <li><a href="${pageContext.request.contextPath}/user/${loginuser.name}/articlelist/${nowpagenum-1}">&laquo;</a></li>	 -->	
		<!-- 分页导航栏 -->
		<c:forEach var="i" begin="1" end="${totalpage}">	
			<li><a href="${pageContext.request.contextPath}/user/${loginuser.name}/articlecatlog/${artCatNow.art_cat_id}/${i}">${i }</a></li>
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
	//提交校验
	function dosubmit() {
		  
	     var art_cat = document.getElementById('art_cat').value;	      
	     
	      if(art_cat=="") {
	    	  alert("分类名不能为空！");
	    	  return false;
	      }
	      if(art_cat.length>10){
	    	  alert("分类名称不能大于10个字！");
	    	  return false;
	      }
	      return true;
	           
	  }
	//删除确认
	function dosubmitdelcat() {		
		var n = window.confirm("您确定要删除吗？"); //返回true或false
		if(n){
			event.returnValue = true;
			}else{
				event.returnValue = false;
		}	           
	  }
</script>
</body></html>
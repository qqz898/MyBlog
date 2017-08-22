<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="keywords" content="opensource rich wysiwyg text editor jquery bootstrap execCommand html5" />
    <meta name="description" content="This tiny jQuery Bootstrap WYSIWYG plugin turns any DIV into a HTML5 rich text editor" />

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/websiteicon.png">
    
     <link href="${pageContext.request.contextPath}/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
   	<!-- wangedtor配置 -->
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wangEditor.min.css">
   	
   	
   	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zerogrid-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-userself-readingmodel.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsiveslides-userself.css" />
    
    
  </head>
  <body>
  
  <!--------------Navigation--------------->


  
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

<div class="container">
  <div class="hero-unit">
  <div class="pull-right">
	<div class="fb-like" data-href="http://facebook.com/mindmupapp" data-send="false" data-layout="button_count" data-width="100" data-show-faces="false"></div><br/>    
  </div>
	<h2>编辑你的日志</h2></hr>
	
	<!-- 标题 -->
	<form role="form">
		<div class="form-group">
			<label for="name" style="font-size: 16px">题目</label><div style="height:5px;"></div>
			<input type="text" id="blogtitle" value="${oldblog.title }" class="form-control" placeholder="请输入标题"/><br/>
		</div>
 	</form>
	
	<div id="alerts"></div>
    <!--demo-->
    <div style="text-align:left;">
        <div id="divDemo" style="height:700px;">${oldblog.content}</div>
    </div><!--demo end-->

    
    
    <br>
    <!-- 是否为私密日志 -->
	  <div class="radio">
		<label>
			<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked> <small style="font-size: 15px;">公开博客</small>
		</label>
	  </div>
	  <div class="radio">
		<label>
			<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2"><small style="font-size: 15px;">私密博客(仅自己可见)</small>
		</label>
	  </div>
	  <!-- 选择分类 -->
	  <div>
	  	<small style="font-size: 15px;">分类</small>
	  	<select id="cat_select" class="form-control">
	  		<option value="0">所有博客</option>
	  		<c:forEach items="${artCatList}" var="artCat">
				<option value="${artCat.art_cat_id }">${artCat.cat_name }</option>
			</c:forEach>
		</select>
	  </div>
	  <div style="height: 5px;"></div>	  
	  <button type="button" onclick="submitblog()" class="btn btn-primary">发布博客</button>
  </div>
  
  

</div>

<!--------------Footer--------------->


<div id="copyright">
	<p>京ICP备17020361号　｜　Copyright © 2016 - Daniel Qiu</p>
	<p>All rights reserved</p>
</div>


<script src="${pageContext.request.contextPath}/js/lib/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/wangEditor.js"></script>
    
<script>

	var editor = new wangEditor('divDemo');
	// 上传图片（举例）
		editor.config.uploadImgUrl = "${pageContext.request.contextPath}/user/writearticle/uploadphoto";
		editor.config.uploadImgFileName = 'articlePhoto';
		// 隐藏掉插入网络图片功能。该配置，只有在你正确配置了图片上传功能之后才可用。
		editor.config.hideLinkImg = true;
	editor.create();
	function submitblog(){
		
		var temp = document.createElement("form");
		temp.action = "${pageContext.request.contextPath}/user/${loginuser.name}/submitmodify/${oldblog.blog_id}";
		temp.method = "post";
		temp.style.display = "none";
		
		var blogtitle =  document.createElement("textarea");
		blogtitle.name = "blogtitle";
		blogtitle.value = document.getElementById("blogtitle").value;
		temp.appendChild(blogtitle);
		if(blogtitle.value==""){
			alert("题目不能为空！");
		}
		
		var blogcontext =  document.createElement("textarea");
		blogcontext.name = "blogcontext";
		blogcontext.value = editor.$txt.html();
		temp.appendChild(blogcontext);
		
		var blogcatelognum =  document.createElement("textarea");
		blogcatelognum.name = "blogcatelognum";
		blogcatelognum.value = document.getElementById("cat_select").value;
		temp.appendChild(blogcatelognum);
		
		var blogoptions = document.createElement("textarea");
		blogoptions.name= "blogoptions";
		var option = document.getElementsByName("optionsRadios");
		for(var iii=0;iii<option.length;iii++){
			if(option[iii].checked)
				blogoptions.value = option[iii].value;
		}
		temp.appendChild(blogoptions);
		document.body.appendChild(temp);
		temp.submit();
		alert("发布成功！点击确定返回主页...")
		return temp;
		
		
	}
  </script>
</body>
</html>




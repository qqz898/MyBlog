<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>留言板</title>
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
   	
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zerogrid-userself.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-userself.css">
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
	
<!-- 评论区 -->
	<div style="margin:0 auto; width: 960px;">
	<center><span style="font-size: 25px">留言板</span></center><hr>
	</div>
	<!-- 留言内容 -->
	<div style="margin:0 auto; width: 960px;">
		<c:forEach items="${messageList}" var="message">
		<div>
			<img style="float: left;margin-right: 20px" src="${message.comment_writer.icon }" width="80px" height="80px" onerror="javascript:this.src='${pageContext.request.contextPath}/images/touxiang.jpg'"  />
			<span style="color: blue">${message.comment_writer.name }&nbsp;&nbsp;${message.formatdate}</span><a style="float: right;" onclick="dosubmitdelcomment()" href="${pageContext.request.contextPath}/user/${loginuser.name}/deletemessage/${message.comment_id}">删除</a><br>
			${message.comment_context }
		</div>
		<br><hr>
		</c:forEach>		
	</div>


	


<div class="container" style="margin: 0 auto;width: 960px;">
<br><br><br>
  <div class="hero-unit">
  <div class="pull-right">
	<div class="fb-like" data-href="http://facebook.com/mindmupapp" data-send="false" data-layout="button_count" data-width="100" data-show-faces="false"></div><br/>    
  </div>
	<h2>发表你的留言</h2></hr>
	
	<div id="alerts"></div>
    <!--demo-->
    <div style="text-align:left;">
        <div id="divDemo" style="height:300px;">Write your comment...</div>
    </div><!--demo end-->
    
    
    <br>
    
	  <div style="height: 5px;"></div>
	  <button type="button" onclick="submitmessage()" class="btn btn-primary">提交</button>
  </div>
  
 </div>



<!--------------Footer--------------->


<div id="copyright">
	<p>京ICP备17020361号　｜　Copyright © 2016 - Daniel Qiu</p>
	<p>All rights reserved</p>
</div>

<script src="${pageContext.request.contextPath}/js/lib/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/wangEditor.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var editor = new wangEditor('divDemo');
	editor.config.menus = [
	               		'source',
	               		'|',
	               		'bold',
	               		'underline',
	               		'italic',
	               		'strikethrough',
	               		'eraser',
	               		'forecolor',
	               		'bgcolor',
	               		'|',
	               		'quote',
	               		'fontfamily',
	               		'fontsize',
	               		'|',
	               		'link',
	               		'unlink',
	               		'emotion',
	               		'|',
	               		'img',
	               		'location'
	                    ];
	// 上传图片（举例）
	editor.config.uploadImgUrl = "${pageContext.request.contextPath}/user/writearticle/uploadphoto";
	editor.config.uploadImgFileName = 'articlePhoto';
	// 隐藏掉插入网络图片功能。该配置，只有在你正确配置了图片上传功能之后才可用。
	editor.config.hideLinkImg = true;
	editor.create();
	
    function submitmessage(){
		
    	var temp = document.createElement("form");
		temp.action = "${pageContext.request.contextPath}/user/${loginuser.name}/submitmessage";
		temp.method = "post";
		temp.style.display = "none";
		
		
		
		var commentcontext =  document.createElement("textarea");
		commentcontext.name = "commentcontext";
		commentcontext.value = editor.$txt.html();
		temp.appendChild(commentcontext);
		
		var commentwriterid = document.createElement("textarea");
		commentwriterid.name= "commentwriterid";
		commentwriterid.value = ${loginuser.user_id};
		temp.appendChild(commentwriterid);
		
		var commentreceiver = document.createElement("textarea");
		commentreceiver.name= "commentreceiver";
		commentreceiver.value = ${loginuser.user_id};
		temp.appendChild(commentreceiver);    		
		
		document.body.appendChild(temp);
		temp.submit();
		alert("发布成功！")
		return temp;
		
		
	}
    function dosubmitdelcomment() {		
		var n = window.confirm("您确定要删除吗？"); //返回true或false
		if(n){
			event.returnValue = true;
			}else{
				event.returnValue = false;
		}	           
	  }
    </script>
  </body>
</html>




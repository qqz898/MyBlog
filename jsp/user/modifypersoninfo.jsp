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
	<title>修改个人资料</title>
	
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
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/mainpage">回到主页</a></li>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/topersonalsettings">个人资料</a></li>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/tochangepersonalinfo">修改资料</a></li>
		<li><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/tochangepersonalpwd">修改密码</a></li>
		<li style="float:right;margin-left:0px;margin-right:5px"><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/logout">注销</a></li>
		<li style="float:right;margin-left:5px;margin-right:0px"><a style="text-decoration: none;color: white" href="${pageContext.request.contextPath}/user/topersonalsettings">${loginuser.name}</a></li>
		
	</ul>
</nav>
<div style="margin: 0 auto;width: 960px;height: 550px">
	<div>
		<center>
			<span style="font-size: 30px;">修改个人资料</span>
		</center>		
	</div><hr>
	
	<center>
		<div class="alert alert-danger alert-dismissable" style="width:950px;margin-top:15px;">
				<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">
					&times;
				</button>
				您可以在此修改除登录名字以外的个人信息
		</div>
	</center>
	
	<center>
		<form method="post" action="${pageContext.request.contextPath}/user/changepersonalinfo" class="login" name="changepersonalinfoform">
	
						<table>
							<tr><td style="width:100px;padding-left:40px;font-size:15px;">用户名:</td>
								<td style>
									<div style="width:480px;">
							    		<input autocomplete="off" disabled="true" id="username" type="text" name="username" class="required form-control" placeholder="${loginuser.name} ">
								    </div>
								</td>
							</tr>
							<tr><td><p></p></td><td></td></tr>
							<tr><td style="width:100px;padding-left:40px;font-size:15px;">昵称:</td>
								<td style>
									<div style="width:480px;">
							    		<input autocomplete="off" id="nickname" type="text" name="nickname" class="required form-control" placeholder="${loginuser.nickname }">
								    </div>
								</td>
							</tr>
							<tr><td><p></p></td><td></td></tr>
							<tr><td style="width:100px;padding-left:40px;font-size:15px;">Email:</td>
								<td style>
									<div style="width:480px;">
							    		<input autocomplete="off" id="email" type="text" name="email" class="required form-control" placeholder="${loginuser.email }">
								    </div>
								</td>
							</tr>							
						</table><br><br>
						<div class="form-group">
								<span style="font-size: 15px">个人简介</span>
								<textarea class="form-control" id="introduce" rows="3" name="introduce"></textarea>
						</div><br>
						<div class="form-group" >
				                <a style="color: white;text-decoration:none; background-color: #d64f4f;padding: 15px 20px 15px 20px;font-size: 12px;" href="javascript:document.changepersonalinfoform.submit();" onclick="return dosubmit()" onmouseover="linkmouseOver(this)" onmouseout="linkmouseOut(this)"><strong>确认修改</strong></a>
				        </div>
			  	  </form>
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
		function linkmouseOver(obj){
			obj.style.backgroundColor="#ff8888";
			obj.style.color = "white";
		}
		function linkmouseOut(obj)
		{
			obj.style.backgroundColor="#d64f4f";
			obj.style.color = "white";
		}
</script>   

<script type="text/javascript">
		//检查用户名位数
		function checkNameLength(){
			var pwd = document.getElementById("nickname").value;
	 		if(pwd.length>32){
	  		alert("用户名不能超32位！");
	        return false;
	 		}else{
	        return true;
	    	}
		}		 	
	 	//检查邮箱格式
		function checkemail(){
			var email = document.getElementById("email").value;
			var pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
			if (pattern.test(email)) {		          
	          return true;
	    	}else{
	          alert("邮箱格式有误！");
	          return false;
	    	}
		}
		//检查个人简介
		function checkIntroduce(){
			var introduce = document.getElementById("introduce").value;
			if(pwd.length>250){
	  			alert("个人简介过长！");
	        	return false;
	 		}else{
	        	return true;
	    	}
		}
		//检查input
	  function checkInput(obj) {
	       var val = obj.value;
	        if(val == "") {   
	            obj.className = "errstyle";
	            return false;
	        } else {
	            return true;
	        }
	    }
			//提交校验
		function dosubmit() {
			  
		     var input1 = checkInput(document.getElementById('nickname'));
		      var input2 = checkInput(document.getElementById('email'));
		      var input3 = checkInput(document.getElementById('introduce'));
		      
		      if(input1 && input2 && input3) {
		           //提交表单代码
		           var email =  checkemail();
		           var telephone = checkNameLength();
		           var introduce = checkIntroduce();
		           if(email && telephone && introduce){
		                alert("提交成功");
		                return true;
		           }else{
		                return false;
		           }
		      }else {
		           alert("请将表单信息填写完整");
		           return false;
		      }
		  }
</script>
</body></html>
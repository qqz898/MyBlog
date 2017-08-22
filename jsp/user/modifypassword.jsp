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
	<title>修改密码</title>
	
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
<div style="margin: 0 auto;width: 960px;height: 400px;">
	<div>
		<center>
			<span style="font-size: 30px;">修改密码</span>
		</center>		
	</div><hr>
	<center>
		<div class="alert alert-danger alert-dismissable" style="width:950px;margin-top:15px;">
				<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">
					&times;
				</button>
				输入旧密码和新密码来修改密码
		</div>
	</center>
	
	<center>
		<form method="post" action="${pageContext.request.contextPath}/user/changepassword" class="login" name="changepasswordform">
	
						<table>
							<tr><td style="width:100px;padding-left:40px;font-size:15px;">用户名:</td>
								<td style>
									<div style="width:480px;">
							    		<input autocomplete="off" disabled="true" id="teamname" type="text" name="teamname" class="required form-control" placeholder="${loginuser.name} ">
								    </div>
								</td>
							</tr>
							<tr><td><p></p></td><td></td></tr>
							<tr><td style="width:100px;padding-left:40px;font-size:15px;">旧密码:</td>
								<td style>
									<div style="width:480px;">
							    		<input autocomplete="off" id="oldpassword" type="text" name="oldpassword" class="required form-control" placeholder="旧密码 *">
								    </div>
								</td>
							</tr>
							<tr><td><p></p></td><td></td></tr>
							<tr><td style="width:100px;padding-left:40px;font-size:15px;">新密码:</td>
								<td style>
									<div style="width:480px;">
							    		<input autocomplete="off" id="newpassword" type="text" name="newpassword" class="required form-control" placeholder="新密码 *">
								    </div>
								</td>
							</tr>
						</table><br><br>
						<div class="form-group" >
				                <a style="color: white;text-decoration:none; background-color: #d64f4f;padding: 15px 20px 15px 20px;font-size: 12px;" href="javascript:document.changepasswordform.submit();" onclick="return dosubmit()" onmouseover="linkmouseOver(this)" onmouseout="linkmouseOut(this)"><strong>确认修改</strong></a>
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
		function dosubmit() {
			  
		     var oldpassword = document.getElementById('oldpassword').value;
		      var newpassword = document.getElementById('newpassword').value;		      
		     
		      if(oldpassword==""||newpassword=="") {
		    	  alert("请将表单信息填写完整！");
		    	  return false;
		      }
		      if(newpassword.length<6){
		    	  alert("密码不能小于6位！")
		    	  return false;
		      }
		      return true;		           
		  }
</script>   
</body></html>
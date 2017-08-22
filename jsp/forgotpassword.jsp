<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
	<title>Forgot Password</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/templatemo_style.css" rel="stylesheet" type="text/css">	
</head>
<body class="templatemo-bg-gray">
	<div class="container">
		<div class="col-md-12">
			<h1 class="margin-bottom-15">Password Reset</h1>
			<form class="form-horizontal templatemo-forgot-password-form templatemo-container" role="form" action="${pageContext.request.contextPath}/forgetpwd" method="post">	
				<div class="form-group">
		          <div class="col-md-12">
		          	Please enter your username so that we can send your password by email.
		          </div>
		        </div>		
		        <div class="form-group">
		          <div class="col-md-12">
		            <input type="text" class="form-control" name="username" id="username" placeholder="Your username">	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		            <input type="submit" value="Submit" onclick="return infotoemail()" class="btn btn-danger">
                    <br><br>                    
		          </div>
		        </div>
		      </form>
		</div>
	</div>
	<script type="text/javascript">
		function infotoemail() {
			  
		     
		      var username = document.getElementById('username').value;		      
		     
		      if(username=="") {
		    	  alert("请填入您要找回的帐号用户名！");
		    	  return false;
		      }
		      alert("您的密码已经发送至您注册时使用的邮箱...请登陆查看...");
		      return true;		           
		  }
	</script>
</body>
</html>
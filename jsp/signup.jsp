<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<link rel="shortcut icon" href="images/websiteicon.png">
		<title>注册</title>
		<script src="js/modernizr.js"></script>
		<link href="css/style-signup.css" rel="stylesheet" type="text/css" />
		<style>
			body {
				background: #e1c192 url(images/bg-signup.png) top left repeat;
				font-family: "Proxima Nova";
				font-size: 13px;
			}
		</style>
		<script type="text/javascript">
			//检查用户名位数
			function checkNameLength(){
				var name = document.getElementById("name").value;
		 		if(name.length>32){
			  		alert("用户名不能超32位！");
			        return false;
		 		}else if(name.length<3){
		 			alert("用户名不能小于3位!");
		 			return false;
		 		}else{
		        	return true;
		    	}
			}
		 	//检查密码位数
	 		function checkPwdLength(){
				var pwd = document.getElementById("pwd").value;
		 		if(pwd.length>32){
			  		alert("密码不能超32位！");
			        return false;
		 		}else if(pwd.length<6){
		 			alert("密码不能小于6位!");
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
				  
			     var input1 = checkInput(document.getElementById('name'));
			      var input2 = checkInput(document.getElementById('email'));
			      var input3 = checkInput(document.getElementById('pwd'));
			      
			      if(input1 && input2 &&input3) {
			           //提交表单代码
			           var email =  checkemail();
			           var telephone = checkNameLength();
			           var pwd = checkPwdLength();
			           if(email && telephone && pwd){
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
	</head>
	<body>
		<div class="content">
			<div id="register_box">
				<div id="head">
					<h1>Create An Account</h1>
					<div class="tag">You can improve your personal information later</div>
				</div>
				<div id="main_box">
					<form class="register" method="post" action="${pageContext.request.contextPath}/signup">
						<h1>Sign up for free!</h1>
						<br />
						
						<div class="text">
							<img src="images/username.png" alt="username" />
							<input type="text" id="name" name="username" placeholder="Username" /><br />
						</div>
						<div class="text">
							<img src="images/email.png" alt="email" />
							<input type="text" id="email" name="email" placeholder="Email Address" /><br />
						</div>
						<div class="text">
							<img src="images/password.png" alt="password" />
							<input type="password" id="pwd" name="password" placeholder="Password" /><br />
						</div>	
						
						<input type="submit" value="Register Now" onclick="return dosubmit()" />
						<br />					
						<div class="login">
							Already have an account?
							<a href="${pageContext.request.contextPath}/toLogin">Sign in now.</a>
						</div>
					</form>
					
					<div class="right_box">
						<div id="benefits">
							<h1>Reasons to register</h1><br />
							
							<ul>
								<li>
									<div class="he">Become Blogger</div>
									<span>Write your own stories and share your life!</span>
								</li>
								<li>
									<div class="he">Community</div>
									<span>Leave a message on a friend's blog!</span>
								</li>
								<li>
									<div class="he">More permissions</div>
									<span>Upload your photos with no limitation! </span>
								</li>
							</ul>
						</div>
						<br /><br /><br />
						<div id="facebook-con">
							<h1>easy way out</h1>
							<br />
						</div>
						<div class="fbl">
							<br />
							<a href="#">
								<img src="images/facebook.png" alt="Connect with Facebook" />
							</a>
							<br /><br>
							<span>	
								Don't worry. We'll <em>never</em> post without your permission.
							</span>
						</div>
					</div>
				</div>
				<div id="footer_box">
					Powered By Daniel Qiu @2016 - All Rights Reserved
				</div>
			</div>
		</div>
	</body>
</html>
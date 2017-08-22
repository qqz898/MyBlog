<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
    <head>
		<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <title>登录</title>
        <meta name="description" content="Custom Login Form Styling with CSS3" />
        <meta name="keywords" content="css3, login, form, custom, input, submit, button, html5, placeholder" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/websiteicon.png"> 
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
        <script src="${pageContext.request.contextPath}/js/modernizr.custom.63321.js"></script>
        <!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
		<style>
			body {
				background: #e1c192 url(${pageContext.request.contextPath}/images/wood_pattern.jpg);
			}
		</style>
		<script type="text/javascript">
			function joke(){
				alert("Haha,Visitor function is not supported now...")
			}
		</script>
    </head>
    <body>
        <div class="container">
		
		<!-- Codrops top bar -->
            <div class="codrops-top">                
                <span class="right">
                    <a href="#" onclick="joke()">
                        <strong>随便看看</strong>
                    </a>
                </span>
            </div><!--/ Codrops top bar -->
			<div style="height: 70px;"></div>
			<header>
			
				<h1><strong>Personal Blog Login Page</strong></h1>
				<h2>Write your story, share your life!</h2>		
				
				<div class="support-note">
					<span class="note-ie">Sorry, only modern browsers.</span>
				</div>
				
			</header>
			
			<section class="main">
				<form class="form-2" method="post" action="${pageContext.request.contextPath}/user/mainpage">
					<h1><span class="log-in">Sign up</span> or <span class="sign-up">sign in</span></h1>
					<p class="float">
						<label for="login"><i class="icon-user"></i>Username</label>
						<input type="text" name="username" placeholder="Username">
						<a style="position: relative;left: 5px;" href="${pageContext.request.contextPath}/toforgetpwdpage">forget password?</a>
					</p>
					<p class="float">						
						<label for="password"><i class="icon-lock"></i>Password</label>
						<input type="password" name="password" placeholder="Password" class="showpassword">
					</p>					
					<p class="clearfix"> 						
						<a href="${pageContext.request.contextPath}/tosignup" class="log-twitter">Sign up</a>    
						<input type="submit" name="submit" value="Sign in">
					</p>
				</form>​​
			</section>
			
        </div>
		<!-- jQuery if needed -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript">
			$(function(){
			    $(".showpassword").each(function(index,input) {
			        var $input = $(input);
			        $("<p class='opt'/>").append(
			            $("<input type='checkbox' class='showpasswordcheckbox' id='showPassword' />").click(function() {
			                var change = $(this).is(":checked") ? "text" : "password";
			                var rep = $("<input placeholder='Password' type='" + change + "' />")
			                    .attr("id", $input.attr("id"))
			                    .attr("name", $input.attr("name"))
			                    .attr('class', $input.attr('class'))
			                    .val($input.val())
			                    .insertBefore($input);
			                $input.remove();
			                $input = rep;
			             })
			        ).append($("<label for='showPassword'/>").text("Show password")).insertAfter($input.parent());
			    });

			    $('#showPassword').click(function(){
					if($("#showPassword").is(":checked")) {
						$('.icon-lock').addClass('icon-unlock');
						$('.icon-unlock').removeClass('icon-lock');    
					} else {
						$('.icon-unlock').addClass('icon-lock');
						$('.icon-lock').removeClass('icon-unlock');
					}
			    });
			});
		</script>
    </body>
</html>
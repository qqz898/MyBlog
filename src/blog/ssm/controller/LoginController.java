package blog.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import blog.ssm.domain.Album;
import blog.ssm.domain.ArtCat;
import blog.ssm.domain.Blog;
import blog.ssm.domain.Comment;
import blog.ssm.domain.User;
import blog.ssm.service.inter.AlbumService;
import blog.ssm.service.inter.ArtCatService;
import blog.ssm.service.inter.BlogService;
import blog.ssm.service.inter.UserService;
import blog.ssm.web.forms.ChangePasswordForm;
import blog.ssm.web.forms.ChangePersonalInfoForm;
import blog.ssm.web.forms.LoginUserForm;
import blog.ssm.web.forms.SignupUserForm;

@Controller

public class LoginController {
	@Resource
	private BlogService blogService;
	@Resource
	private UserService userService;
	@Resource
	private AlbumService albumService;
	@Resource 
	private ArtCatService artCatService;
	//跳转到注册页面
	@RequestMapping(value="/tosignup",method=RequestMethod.GET)	
	public String toSingup(ModelMap model) {
	
		return "signup";
	}
	//跳转到登陆页面
	@RequestMapping(value="/toLogin",method=RequestMethod.GET)	
	public String toLogin(ModelMap model) {
	
		return "login";
	}
	//跳转到忘记密码页
	@RequestMapping(value="/toforgetpwdpage",method=RequestMethod.GET)	
	public String toForgetPwdPage(HttpSession httpSession,ModelMap model) {
		
		return "forgotpassword";
	}
	
	//查询用户并发email
	@RequestMapping(value="/forgetpwd",method=RequestMethod.POST)	
	public String toSendEmail(LoginUserForm loginUserForm,ModelMap model) throws AddressException, MessagingException {
		
		User user = userService.seletUserByName(loginUserForm.getUsername());
		if(user==null)
			return "forgotpassword";
		// 创建Properties 类用于记录邮箱的一些属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
        props.put("mail.smtp.port", "587");
        // 此处填写你的账号
        props.put("mail.user", "421344748@qq.com");
        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password", "tembamnhjferbgdf");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress(user.getEmail());
        message.setRecipient(RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject("Dear MyBlog User, This is your Passsword..");

        // 设置邮件的内容体
        message.setContent("您的用户名："+user.getName()+"<br>密码："+user.getPassword()+"<br>欢迎在MyBlog中写下你的故事...", "text/html;charset=UTF-8");

        // 最后当然就是发送邮件啦
        Transport.send(message);
		
		return "login";
	}
	//跳转到个人中心页面
	@RequestMapping(value="/user/topersonalsettings",method=RequestMethod.GET)	
	public String toPersonalSettings(ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){
			//更新个人信息
			User user = (User) httpSession.getAttribute("loginuser");
			User newuser = userService.selectUserById(user.getUser_id());
			httpSession.setAttribute("loginuser", newuser);
			return "user/personalsettings";
		}else
			return null;		
	}
	//跳转到修改个人资料页面
	@RequestMapping(value="/user/tochangepersonalinfo",method=RequestMethod.GET)	
	public String toChangePersonalInfo(ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){
			return "user/modifypersoninfo";
		}else
			return null;		
	}
	//跳转到修改密码页面
	@RequestMapping(value="/user/tochangepersonalpwd",method=RequestMethod.GET)	
	public String toChangePersonalPwd(ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){
			return "user/modifypassword";
		}else
			return null;		
	}
	
	
	//跳转到浏览日志界面
	@RequestMapping(value="/user/{username}/articlelist/{pagenum}",method=RequestMethod.GET)	
	public String toLookupArticle(@PathVariable String pagenum,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			//System.out.println("hahhahahahah");
			int startindex;	//检索起始下标
			int selectnum = 15;	//检索条数
			int totalpage;		//总共分几页显示
			
			User user = (User) httpSession.getAttribute("loginuser");
			List<Blog> l = blogService.selectBlogByUserID(user.getUser_id());
			
			if(l.size()%selectnum==0)		//计算总页数
				totalpage = l.size()/selectnum;
			else totalpage = l.size()/selectnum + 1;
			
			if(pagenum==null)		//前端传回的页码,用来计算查询起点
				startindex = 0;
			else
				startindex = (Integer.parseInt(pagenum)-1)*selectnum;
			
			
			//下面开始查询
			//System.out.println(user.getUser_id()+user.getName());
			
			List<Blog> blogList = blogService.getUserArticleByPage(user.getUser_id(), startindex, selectnum);
			for(Blog b:blogList){		//处理一下日期格式
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				b.setFormatdate(formatter.format(b.getTime()));
				if(b.getAccess_authority()==1)
					b.setPrivateinfo("");
				else if(b.getAccess_authority()==2)
					b.setPrivateinfo("私密博客");
			}
			//将文章分类信息写入session
			List<ArtCat> artCatList = artCatService.selectArtCatListByUserId(user.getUser_id());
			
			httpSession.setAttribute("artCatList", artCatList);
			//把文章和总页数传回前端
			httpSession.setAttribute("blogList", blogList);
			httpSession.setAttribute("totalpage", totalpage);
				
				//System.out.println(b.getTitle()+b.getTime()+b.getUser());
			return "user/articlelist";
		}else
			return "login";		
	}
	
	//删除日志
		@RequestMapping(value="/user/{username}/articlelist/delete/{articleID}",method=RequestMethod.GET)	
		public String DeleteArticle(@PathVariable String username,@PathVariable String articleID,ModelMap model,HttpSession httpSession) {	
			if(httpSession.getAttribute("loginuser")!=null){			
				blogService.deleteBlogByArticleID(Integer.parseInt(articleID));
				//调用toLookupArticle来更新session中的blogList为删除后的新list
				return toLookupArticle("1", model, httpSession);
			}else
				return "login";		
		}
		
	//修改日志
	@RequestMapping(value="/user/{username}/articlelist/modify/{articleID}",method=RequestMethod.GET)	
	public String toModifyArticlePage(@PathVariable String articleID,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			
			List<Blog> blogList = blogService.selectBlogByBlogId(Integer.parseInt(articleID));
			if(!blogList.isEmpty()){
				httpSession.setAttribute("oldblog", blogList.get(0));
			}
			//将文章分类信息写入session
			List<ArtCat> artCatList = artCatService.selectArtCatListByUserId(user.getUser_id());
			
			httpSession.setAttribute("artCatList", artCatList);
			return "user/modifyblog";
		}else
			return "login";		
	}
	//注销
		@RequestMapping(value="/logout",method=RequestMethod.GET)	
		public String logout(ModelMap model,HttpSession httpSession) {	
			httpSession.invalidate();
			return "login";		
		}
	
	//获取用户登录表单信息并验证
	@RequestMapping(value = "/user/mainpage")
    public String loginCheck(LoginUserForm loginUserForm,ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){
			User loginuser = (User) httpSession.getAttribute("loginuser");
			//取出最近5篇博客			
			List<Blog> blogList = blogService.getFiveBlogs(loginuser.getUser_id());
			//取出5篇博客的评论数
			for(Blog b:blogList){
				//取出某个日志的评论，放入日志对象
				List<Comment> commentList = blogService.selectCommentByBlogId(b.getBlog_id());
				b.setComment_num(commentList.size());
			}
			//取出站上所有用户
			List<User> allUser = userService.selectAllUsers();
			//取出相册信息,供主页展示
			List<Album> mainPageAlbum = albumService.getLastThreeAlbum(loginuser.getUser_id());
			httpSession.setAttribute("mainPageAlbum", mainPageAlbum);
			httpSession.setAttribute("allUser", allUser);
			httpSession.setAttribute("recentfourblog", blogList);
			
			
			return "user/mainpage";
		}else{
		User user = userService.getUserByNameAndPwd(loginUserForm.getUsername(), loginUserForm.getPassword());
		if(user==null){
			//没找到这个用户
			return "login";
		}else{
			//取出最近5篇博客
			List<Blog> blogList = blogService.getFiveBlogs(user.getUser_id());
			//取出5篇博客的评论数
			for(Blog b:blogList){
				//取出某个日志的评论，放入日志对象
				List<Comment> commentList = blogService.selectCommentByBlogId(b.getBlog_id());
				b.setComment_num(commentList.size());
			}
			//取出站上所有用户
			List<User> allUser = userService.selectAllUsers();
			//取出相册信息,供主页展示
			List<Album> mainPageAlbum = albumService.getLastThreeAlbum(user.getUser_id());
			
			httpSession.setAttribute("mainPageAlbum", mainPageAlbum);
			httpSession.setAttribute("recentfourblog", blogList);
			httpSession.setAttribute("allUser", allUser);
			httpSession.setAttribute("loginuser", user);			
			return "user/mainpage";   
		}
		//model.addAttribute("loginuser", user);
		}
    }
	
	//获取用户注册表单信息并写入数据库
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpProcess(SignupUserForm signupUserForm,ModelMap model) {
		User user = userService.seletUserByName(signupUserForm.getUsername());
		if(user==null){				
			User newUser = new User();
			newUser.setName(signupUserForm.getUsername());
			newUser.setPassword(signupUserForm.getPassword());
			newUser.setEmail(signupUserForm.getEmail());
			userService.doSignupProcess(newUser);
			return "login";
		}
		return null;      
    }

	
	//修改密码
	@RequestMapping(value = "/user/changepassword", method = RequestMethod.POST)
    public String changePassword(ChangePasswordForm changePasswordForm,ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");			
			if(changePasswordForm.getOldpassword().equals(user.getPassword())){
				userService.modifyPassword(user.getUser_id(),Integer.parseInt(changePasswordForm.getNewpassword()));
				httpSession.invalidate();
				return "login";
			}else
				return "user/modifypassword";		//这里要解决输入错误旧密码时候的错误页面问题			
		}else
			return null;
    }
	
	
	//修改用户个人信息
		@RequestMapping(value = "/user/changepersonalinfo", method = RequestMethod.POST)
	    public String changePersonalInfo(ChangePersonalInfoForm changePersonalInfoForm,ModelMap model,HttpSession httpSession) {
			if(httpSession.getAttribute("loginuser")!=null){
				User user = (User) httpSession.getAttribute("loginuser");
				User tempuser = new User();
				tempuser.setUser_id(user.getUser_id());
				tempuser.setNickname(changePersonalInfoForm.getNickname());
				tempuser.setEmail(changePersonalInfoForm.getEmail());
				tempuser.setIntroduction(changePersonalInfoForm.getIntroduce());
				
				userService.modifyPersonalInfo(tempuser);
				//通过跳回个人中心页面更新user信息
				return toPersonalSettings(model,httpSession);
				
				
			}else
				return null;
	    }
}

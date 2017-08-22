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
	//��ת��ע��ҳ��
	@RequestMapping(value="/tosignup",method=RequestMethod.GET)	
	public String toSingup(ModelMap model) {
	
		return "signup";
	}
	//��ת����½ҳ��
	@RequestMapping(value="/toLogin",method=RequestMethod.GET)	
	public String toLogin(ModelMap model) {
	
		return "login";
	}
	//��ת����������ҳ
	@RequestMapping(value="/toforgetpwdpage",method=RequestMethod.GET)	
	public String toForgetPwdPage(HttpSession httpSession,ModelMap model) {
		
		return "forgotpassword";
	}
	
	//��ѯ�û�����email
	@RequestMapping(value="/forgetpwd",method=RequestMethod.POST)	
	public String toSendEmail(LoginUserForm loginUserForm,ModelMap model) throws AddressException, MessagingException {
		
		User user = userService.seletUserByName(loginUserForm.getUsername());
		if(user==null)
			return "forgotpassword";
		// ����Properties �����ڼ�¼�����һЩ����
        final Properties props = new Properties();
        // ��ʾSMTP�����ʼ���������������֤
        props.put("mail.smtp.auth", "true");
        //�˴���дSMTP������
        props.put("mail.smtp.host", "smtp.qq.com");
        //�˿ںţ�QQ��������������˿ڣ�������һ����һֱʹ�ò��ˣ����Ծ͸�����һ��587
        props.put("mail.smtp.port", "587");
        // �˴���д����˺�
        props.put("mail.user", "421344748@qq.com");
        // �˴����������ǰ��˵��16λSTMP����
        props.put("mail.password", "tembamnhjferbgdf");

        // ������Ȩ��Ϣ�����ڽ���SMTP���������֤
        Authenticator authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // �û���������
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
        Session mailSession = Session.getInstance(props, authenticator);
        // �����ʼ���Ϣ
        MimeMessage message = new MimeMessage(mailSession);
        // ���÷�����
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // �����ռ��˵�����
        InternetAddress to = new InternetAddress(user.getEmail());
        message.setRecipient(RecipientType.TO, to);

        // �����ʼ�����
        message.setSubject("Dear MyBlog User, This is your Passsword..");

        // �����ʼ���������
        message.setContent("�����û�����"+user.getName()+"<br>���룺"+user.getPassword()+"<br>��ӭ��MyBlog��д����Ĺ���...", "text/html;charset=UTF-8");

        // ���Ȼ���Ƿ����ʼ���
        Transport.send(message);
		
		return "login";
	}
	//��ת����������ҳ��
	@RequestMapping(value="/user/topersonalsettings",method=RequestMethod.GET)	
	public String toPersonalSettings(ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){
			//���¸�����Ϣ
			User user = (User) httpSession.getAttribute("loginuser");
			User newuser = userService.selectUserById(user.getUser_id());
			httpSession.setAttribute("loginuser", newuser);
			return "user/personalsettings";
		}else
			return null;		
	}
	//��ת���޸ĸ�������ҳ��
	@RequestMapping(value="/user/tochangepersonalinfo",method=RequestMethod.GET)	
	public String toChangePersonalInfo(ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){
			return "user/modifypersoninfo";
		}else
			return null;		
	}
	//��ת���޸�����ҳ��
	@RequestMapping(value="/user/tochangepersonalpwd",method=RequestMethod.GET)	
	public String toChangePersonalPwd(ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){
			return "user/modifypassword";
		}else
			return null;		
	}
	
	
	//��ת�������־����
	@RequestMapping(value="/user/{username}/articlelist/{pagenum}",method=RequestMethod.GET)	
	public String toLookupArticle(@PathVariable String pagenum,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			//System.out.println("hahhahahahah");
			int startindex;	//������ʼ�±�
			int selectnum = 15;	//��������
			int totalpage;		//�ܹ��ּ�ҳ��ʾ
			
			User user = (User) httpSession.getAttribute("loginuser");
			List<Blog> l = blogService.selectBlogByUserID(user.getUser_id());
			
			if(l.size()%selectnum==0)		//������ҳ��
				totalpage = l.size()/selectnum;
			else totalpage = l.size()/selectnum + 1;
			
			if(pagenum==null)		//ǰ�˴��ص�ҳ��,���������ѯ���
				startindex = 0;
			else
				startindex = (Integer.parseInt(pagenum)-1)*selectnum;
			
			
			//���濪ʼ��ѯ
			//System.out.println(user.getUser_id()+user.getName());
			
			List<Blog> blogList = blogService.getUserArticleByPage(user.getUser_id(), startindex, selectnum);
			for(Blog b:blogList){		//����һ�����ڸ�ʽ
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				b.setFormatdate(formatter.format(b.getTime()));
				if(b.getAccess_authority()==1)
					b.setPrivateinfo("");
				else if(b.getAccess_authority()==2)
					b.setPrivateinfo("˽�ܲ���");
			}
			//�����·�����Ϣд��session
			List<ArtCat> artCatList = artCatService.selectArtCatListByUserId(user.getUser_id());
			
			httpSession.setAttribute("artCatList", artCatList);
			//�����º���ҳ������ǰ��
			httpSession.setAttribute("blogList", blogList);
			httpSession.setAttribute("totalpage", totalpage);
				
				//System.out.println(b.getTitle()+b.getTime()+b.getUser());
			return "user/articlelist";
		}else
			return "login";		
	}
	
	//ɾ����־
		@RequestMapping(value="/user/{username}/articlelist/delete/{articleID}",method=RequestMethod.GET)	
		public String DeleteArticle(@PathVariable String username,@PathVariable String articleID,ModelMap model,HttpSession httpSession) {	
			if(httpSession.getAttribute("loginuser")!=null){			
				blogService.deleteBlogByArticleID(Integer.parseInt(articleID));
				//����toLookupArticle������session�е�blogListΪɾ�������list
				return toLookupArticle("1", model, httpSession);
			}else
				return "login";		
		}
		
	//�޸���־
	@RequestMapping(value="/user/{username}/articlelist/modify/{articleID}",method=RequestMethod.GET)	
	public String toModifyArticlePage(@PathVariable String articleID,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			
			List<Blog> blogList = blogService.selectBlogByBlogId(Integer.parseInt(articleID));
			if(!blogList.isEmpty()){
				httpSession.setAttribute("oldblog", blogList.get(0));
			}
			//�����·�����Ϣд��session
			List<ArtCat> artCatList = artCatService.selectArtCatListByUserId(user.getUser_id());
			
			httpSession.setAttribute("artCatList", artCatList);
			return "user/modifyblog";
		}else
			return "login";		
	}
	//ע��
		@RequestMapping(value="/logout",method=RequestMethod.GET)	
		public String logout(ModelMap model,HttpSession httpSession) {	
			httpSession.invalidate();
			return "login";		
		}
	
	//��ȡ�û���¼����Ϣ����֤
	@RequestMapping(value = "/user/mainpage")
    public String loginCheck(LoginUserForm loginUserForm,ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){
			User loginuser = (User) httpSession.getAttribute("loginuser");
			//ȡ�����5ƪ����			
			List<Blog> blogList = blogService.getFiveBlogs(loginuser.getUser_id());
			//ȡ��5ƪ���͵�������
			for(Blog b:blogList){
				//ȡ��ĳ����־�����ۣ�������־����
				List<Comment> commentList = blogService.selectCommentByBlogId(b.getBlog_id());
				b.setComment_num(commentList.size());
			}
			//ȡ��վ�������û�
			List<User> allUser = userService.selectAllUsers();
			//ȡ�������Ϣ,����ҳչʾ
			List<Album> mainPageAlbum = albumService.getLastThreeAlbum(loginuser.getUser_id());
			httpSession.setAttribute("mainPageAlbum", mainPageAlbum);
			httpSession.setAttribute("allUser", allUser);
			httpSession.setAttribute("recentfourblog", blogList);
			
			
			return "user/mainpage";
		}else{
		User user = userService.getUserByNameAndPwd(loginUserForm.getUsername(), loginUserForm.getPassword());
		if(user==null){
			//û�ҵ�����û�
			return "login";
		}else{
			//ȡ�����5ƪ����
			List<Blog> blogList = blogService.getFiveBlogs(user.getUser_id());
			//ȡ��5ƪ���͵�������
			for(Blog b:blogList){
				//ȡ��ĳ����־�����ۣ�������־����
				List<Comment> commentList = blogService.selectCommentByBlogId(b.getBlog_id());
				b.setComment_num(commentList.size());
			}
			//ȡ��վ�������û�
			List<User> allUser = userService.selectAllUsers();
			//ȡ�������Ϣ,����ҳչʾ
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
	
	//��ȡ�û�ע�����Ϣ��д�����ݿ�
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

	
	//�޸�����
	@RequestMapping(value = "/user/changepassword", method = RequestMethod.POST)
    public String changePassword(ChangePasswordForm changePasswordForm,ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");			
			if(changePasswordForm.getOldpassword().equals(user.getPassword())){
				userService.modifyPassword(user.getUser_id(),Integer.parseInt(changePasswordForm.getNewpassword()));
				httpSession.invalidate();
				return "login";
			}else
				return "user/modifypassword";		//����Ҫ���������������ʱ��Ĵ���ҳ������			
		}else
			return null;
    }
	
	
	//�޸��û�������Ϣ
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
				//ͨ�����ظ�������ҳ�����user��Ϣ
				return toPersonalSettings(model,httpSession);
				
				
			}else
				return null;
	    }
}

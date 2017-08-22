package blog.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.ssm.domain.Blog;
import blog.ssm.domain.Comment;
import blog.ssm.domain.User;
import blog.ssm.service.inter.BlogService;
import blog.ssm.service.inter.UserService;
import blog.ssm.web.forms.BlogCommentForm;
import blog.ssm.web.forms.BlogSubmissionForm;

@Controller
public class CommentController {

	
	@Resource
	private BlogService blogService;
	@Resource
	private UserService userService;
	
	//����������д�����ݿ�
	@RequestMapping(value="/user/{username}/submitcomment",method=RequestMethod.POST)
	public String submitComment(BlogCommentForm blogCommentForm,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			
			Comment comment = new Comment();
			comment.setFrom_user_id(Integer.parseInt(blogCommentForm.getCommentwriterid()));
			comment.setTo_user_id(Integer.parseInt(blogCommentForm.getCommentreceiver()));
			comment.setComment_blog_id(Integer.parseInt(blogCommentForm.getBlogid()));
			comment.setComment_context(blogCommentForm.getCommentcontext());
			comment.setTime(new Date());
			//�˴�ע�⣬���ݿ���comment_type�ֶ������������۵�λ�á�1:��־����������    2:��������������    3:ͼƬ������
			comment.setComment_type(1);
			blogService.submitBlogComment(comment);							
			return "user/articlelist";
		}				
		return null;
	}
	
	//����������д�����ݿ⣨�ο͵����ۣ�
	@RequestMapping(value="/visitor/{username}/submitcomment",method=RequestMethod.POST)
	public String submitCommentasVisitor(BlogCommentForm blogCommentForm,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			
			Comment comment = new Comment();
			comment.setFrom_user_id(Integer.parseInt(blogCommentForm.getCommentwriterid()));
			comment.setTo_user_id(Integer.parseInt(blogCommentForm.getCommentreceiver()));
			comment.setComment_blog_id(Integer.parseInt(blogCommentForm.getBlogid()));
			comment.setComment_context(blogCommentForm.getCommentcontext());
			comment.setTime(new Date());
			//�˴�ע�⣬���ݿ���comment_type�ֶ������������۵�λ�á�1:��־����������    2:��������������    3:ͼƬ������
			comment.setComment_type(1);
			blogService.submitBlogComment(comment);							
			return "visitor/articlelist";
		}				
		return null;
	}
	
	//��ת�����԰����
	@RequestMapping(value="/user/{username}/tomessageboard",method=RequestMethod.GET)	
	public String toMessageBoard(@PathVariable String username,ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){			
			User user = (User) httpSession.getAttribute("loginuser");		
			//�����͵����ڸ�ʽ
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			////ȡ�����԰�����
			List<Comment> messageList = userService.selectMessageBoardByUserId(user.getUser_id());
			
			for(Comment c:messageList){
				//ȡ��ĳ����־�����۵����ߣ�������־����
				User messageauthor = userService.selectUserById(c.getFrom_user_id());
				c.setComment_writer(messageauthor);
				c.setFormatdate(formatter.format(c.getTime()));
			}
							
			httpSession.setAttribute("messageList", messageList);			
			return "user/messageboard";
		}else
			return "login";		
	}
	
	//��ת�����԰����(�ο�)
	@RequestMapping(value="/visitor/{hostid}/tomessageboard",method=RequestMethod.GET)	
	public String toMessageBoardasVisitor(@PathVariable String hostid,ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){			
			User user = (User) httpSession.getAttribute("loginuser");		
			//�����͵����ڸ�ʽ
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			////ȡ�����԰�����
			List<Comment> messageList = userService.selectMessageBoardByUserId(Integer.parseInt(hostid));
			
			for(Comment c:messageList){
				//ȡ��ĳ����־�����۵����ߣ�������־����
				User messageauthor = userService.selectUserById(c.getFrom_user_id());
				c.setComment_writer(messageauthor);
				c.setFormatdate(formatter.format(c.getTime()));
			}
							
			httpSession.setAttribute("messageList", messageList);			
			return "visitor/messageboard";
		}else
			return "login";		
	}
	
	//����������д�����ݿ�
	@RequestMapping(value="/user/{username}/submitmessage",method=RequestMethod.POST)
	public String submitMessage(BlogCommentForm blogCommentForm,ModelMap model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			User nowuser = (User) httpSession.getAttribute("loginuser");
			Comment comment = new Comment();
			comment.setFrom_user_id(Integer.parseInt(blogCommentForm.getCommentwriterid()));
			comment.setTo_user_id(Integer.parseInt(blogCommentForm.getCommentreceiver()));
			//comment.setComment_blog_id(Integer.parseInt(blogCommentForm.getBlogid()));
			comment.setComment_context(blogCommentForm.getCommentcontext());
			comment.setTime(new Date());
			//�˴�ע�⣬���ݿ���comment_type�ֶ������������۵�λ�á�1:��־����������    2:��������������    3:ͼƬ������
			comment.setComment_type(2);
			blogService.submitBlogComment(comment);
			//����toMessageBoard���������԰���Ϣ
			return toMessageBoard(nowuser.getName(),model,httpSession);
		}				
		return null;
	}
	
	//����������д�����ݿ�(�ο�)
	@RequestMapping(value="/visitor/{username}/submitmessage",method=RequestMethod.POST)
	public String submitMessageasVisitor(BlogCommentForm blogCommentForm,ModelMap model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			User hostuser = (User) httpSession.getAttribute("hostuser");
			//System.out.println(blogCommentForm.getCommentwriterid()+blogCommentForm.getCommentreceiver()+blogCommentForm.getCommentcontext());
			Comment comment = new Comment();
			comment.setFrom_user_id(Integer.parseInt(blogCommentForm.getCommentwriterid()));
			comment.setTo_user_id(Integer.parseInt(blogCommentForm.getCommentreceiver()));
			//comment.setComment_blog_id(Integer.parseInt(blogCommentForm.getBlogid()));
			comment.setComment_context(blogCommentForm.getCommentcontext());
			comment.setTime(new Date());
			//�˴�ע�⣬���ݿ���comment_type�ֶ������������۵�λ�á�1:��־����������    2:��������������    3:ͼƬ������
			comment.setComment_type(2);
			blogService.submitBlogComment(comment);
			//����toMessageBoard���������԰���Ϣ			
			return toMessageBoardasVisitor(String.valueOf(hostuser.getUser_id()),model,httpSession);
		}				
		return null;
	}
	
	//ɾ�����ۣ�������������
	@RequestMapping(value="/user/{username}/deletemessage/{comment_id}",method=RequestMethod.GET)
	public String deleteMessageByMessageID(@PathVariable String comment_id,ModelMap model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			User nowuser = (User) httpSession.getAttribute("loginuser");
			blogService.deleteMessageByMessageID(Integer.parseInt(comment_id));
			//����toMessageBoard���������԰���Ϣ			
			return toMessageBoard(nowuser.getName(),model,httpSession);
		}				
		return null;
	}

}

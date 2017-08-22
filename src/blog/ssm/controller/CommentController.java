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
	
	//将博文评论写入数据库
	@RequestMapping(value="/user/{username}/submitcomment",method=RequestMethod.POST)
	public String submitComment(BlogCommentForm blogCommentForm,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			
			Comment comment = new Comment();
			comment.setFrom_user_id(Integer.parseInt(blogCommentForm.getCommentwriterid()));
			comment.setTo_user_id(Integer.parseInt(blogCommentForm.getCommentreceiver()));
			comment.setComment_blog_id(Integer.parseInt(blogCommentForm.getBlogid()));
			comment.setComment_context(blogCommentForm.getCommentcontext());
			comment.setTime(new Date());
			//此处注意，数据库中comment_type字段用于区分评论的位置。1:日志评论区评论    2:公共评论区评论    3:图片区评论
			comment.setComment_type(1);
			blogService.submitBlogComment(comment);							
			return "user/articlelist";
		}				
		return null;
	}
	
	//将博文评论写入数据库（游客的评论）
	@RequestMapping(value="/visitor/{username}/submitcomment",method=RequestMethod.POST)
	public String submitCommentasVisitor(BlogCommentForm blogCommentForm,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			
			Comment comment = new Comment();
			comment.setFrom_user_id(Integer.parseInt(blogCommentForm.getCommentwriterid()));
			comment.setTo_user_id(Integer.parseInt(blogCommentForm.getCommentreceiver()));
			comment.setComment_blog_id(Integer.parseInt(blogCommentForm.getBlogid()));
			comment.setComment_context(blogCommentForm.getCommentcontext());
			comment.setTime(new Date());
			//此处注意，数据库中comment_type字段用于区分评论的位置。1:日志评论区评论    2:公共评论区评论    3:图片区评论
			comment.setComment_type(1);
			blogService.submitBlogComment(comment);							
			return "visitor/articlelist";
		}				
		return null;
	}
	
	//跳转到留言版界面
	@RequestMapping(value="/user/{username}/tomessageboard",method=RequestMethod.GET)	
	public String toMessageBoard(@PathVariable String username,ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){			
			User user = (User) httpSession.getAttribute("loginuser");		
			//处理博客的日期格式
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			////取出留言版内容
			List<Comment> messageList = userService.selectMessageBoardByUserId(user.getUser_id());
			
			for(Comment c:messageList){
				//取出某个日志的评论的作者，放入日志对象
				User messageauthor = userService.selectUserById(c.getFrom_user_id());
				c.setComment_writer(messageauthor);
				c.setFormatdate(formatter.format(c.getTime()));
			}
							
			httpSession.setAttribute("messageList", messageList);			
			return "user/messageboard";
		}else
			return "login";		
	}
	
	//跳转到留言版界面(游客)
	@RequestMapping(value="/visitor/{hostid}/tomessageboard",method=RequestMethod.GET)	
	public String toMessageBoardasVisitor(@PathVariable String hostid,ModelMap model,HttpSession httpSession) {
		if(httpSession.getAttribute("loginuser")!=null){			
			User user = (User) httpSession.getAttribute("loginuser");		
			//处理博客的日期格式
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			////取出留言版内容
			List<Comment> messageList = userService.selectMessageBoardByUserId(Integer.parseInt(hostid));
			
			for(Comment c:messageList){
				//取出某个日志的评论的作者，放入日志对象
				User messageauthor = userService.selectUserById(c.getFrom_user_id());
				c.setComment_writer(messageauthor);
				c.setFormatdate(formatter.format(c.getTime()));
			}
							
			httpSession.setAttribute("messageList", messageList);			
			return "visitor/messageboard";
		}else
			return "login";		
	}
	
	//将留言评论写入数据库
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
			//此处注意，数据库中comment_type字段用于区分评论的位置。1:日志评论区评论    2:公共评论区评论    3:图片区评论
			comment.setComment_type(2);
			blogService.submitBlogComment(comment);
			//调用toMessageBoard来更新留言版信息
			return toMessageBoard(nowuser.getName(),model,httpSession);
		}				
		return null;
	}
	
	//将留言评论写入数据库(游客)
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
			//此处注意，数据库中comment_type字段用于区分评论的位置。1:日志评论区评论    2:公共评论区评论    3:图片区评论
			comment.setComment_type(2);
			blogService.submitBlogComment(comment);
			//调用toMessageBoard来更新留言版信息			
			return toMessageBoardasVisitor(String.valueOf(hostuser.getUser_id()),model,httpSession);
		}				
		return null;
	}
	
	//删除评论，仅博主可以做
	@RequestMapping(value="/user/{username}/deletemessage/{comment_id}",method=RequestMethod.GET)
	public String deleteMessageByMessageID(@PathVariable String comment_id,ModelMap model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			User nowuser = (User) httpSession.getAttribute("loginuser");
			blogService.deleteMessageByMessageID(Integer.parseInt(comment_id));
			//调用toMessageBoard来更新留言版信息			
			return toMessageBoard(nowuser.getName(),model,httpSession);
		}				
		return null;
	}

}

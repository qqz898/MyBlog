package blog.ssm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.ssm.domain.Album;
import blog.ssm.domain.ArtCat;
import blog.ssm.domain.Blog;
import blog.ssm.domain.Comment;
import blog.ssm.domain.Photo;
import blog.ssm.domain.User;
import blog.ssm.service.inter.AlbumService;
import blog.ssm.service.inter.ArtCatService;
import blog.ssm.service.inter.BlogService;
import blog.ssm.service.inter.PhotoService;
import blog.ssm.service.inter.UserService;

@Controller
@RequestMapping("/visitor")
public class VisitorController {

	@Resource
	private BlogService blogService;
	@Resource
	private UserService userService;
	@Resource
	private AlbumService albumService;
	@Resource 
	private PhotoService photoService;
	@Resource
	private ArtCatService artcatService;
	
	//以浏览者的模式跳转到相应id用户的主页
	@RequestMapping(value="/{userid}/mainpage",method=RequestMethod.GET)
	public String toMainpageasVisitor(@PathVariable String userid,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			User user = userService.selectUserById(Integer.parseInt(userid));
			httpSession.setAttribute("hostuser", user);
			//取出博主最近5篇博客			
			List<Blog> blogList = blogService.getFiveBlogs(user.getUser_id());
			
			//取出5篇博客的评论数
			for(Blog b:blogList){
				//取出某个日志的评论，放入日志对象
				List<Comment> commentList = blogService.selectCommentByBlogId(b.getBlog_id());
				b.setComment_num(commentList.size());
			}
			//取出站上所有用户
			List<User> allUser = userService.selectAllUsers();
			//取出博主相册信息,供主页展示
			List<Album> mainPageAlbum = albumService.getLastThreeAlbum(user.getUser_id());
			httpSession.setAttribute("hostmainPageAlbum", mainPageAlbum);
			httpSession.setAttribute("allUser", allUser);
			httpSession.setAttribute("hostrecentfourblog", blogList);
			return "visitor/mainpage";
		}			
		return null;
	}
	
	//跳转到浏览日志界面
	@RequestMapping(value="/{userid}/articlelist/{pagenum}",method=RequestMethod.GET)	
	public String toLookupBlogasVisitor(@PathVariable String userid,@PathVariable String pagenum,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			//System.out.println("hahhahahahah");
			int startindex;	//检索起始下标
			int selectnum = 15;	//检索条数
			int totalpage;		//总共分几页显示
			
			
			//以游客身份获取文章列表，不显示私密博客
			List<Blog> l = blogService.selectBlogByUserIDasVisitor(Integer.parseInt(userid));
			
			if(l.size()%selectnum==0)		//计算总页数
				totalpage = l.size()/selectnum;
			else totalpage = l.size()/selectnum + 1;
			
			if(pagenum==null)		//前端传回的页码,用来计算查询起点
				startindex = 0;
			else
				startindex = (Integer.parseInt(pagenum)-1)*selectnum;
			
			
			//下面开始查询
			//System.out.println(user.getUser_id()+user.getName());
			
			List<Blog> blogList = blogService.getUserArticleByPageasVisitor(Integer.parseInt(userid), startindex, selectnum);
			for(Blog b:blogList){		//处理一下日期格式
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				b.setFormatdate(formatter.format(b.getTime()));
			}
			
			//取出某个用户的cat
			List<ArtCat> artCatList = artcatService.selectArtCatListByUserId(Integer.parseInt(userid));
			httpSession.setAttribute("artCatList", artCatList);			
			//把文章和总页数传回前端
			httpSession.setAttribute("blogList", blogList);
			httpSession.setAttribute("totalpage", totalpage);
			
				//System.out.println(b.getTitle()+b.getTime()+b.getUser());
			return "visitor/articlelist";
		}else
			return "login";		
	}
	
	//以浏览者身份跳转到某个博客分类下的文章列表
	@RequestMapping(value="/{username}/articlecatlog/{art_cat_id}/{pagenum}",method=RequestMethod.GET)
	public String toCatelogPage(@PathVariable String art_cat_id,@PathVariable String pagenum,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			//取到存在session中博主的对象
			User user = (User) httpSession.getAttribute("hostuser");
			ArtCat artCatNowVis = artcatService.getAnArtCatByCatId(user.getUser_id(), Integer.parseInt(art_cat_id));
			//查找主人的所有分类
			List<ArtCat> artCatList = artcatService.selectArtCatListByUserId(user.getUser_id());
					
			int startindex;	//检索起始下标
			int selectnum = 15;	//检索条数
			int totalpage;		//总共分几页显示
			
			List<Blog> l = blogService.selectBlogByUserIDAndCatasVisitor(user.getUser_id(),Integer.parseInt(art_cat_id));
			
			
			if(l.size()%selectnum==0)		//计算总页数
				totalpage = l.size()/selectnum;
			else totalpage = l.size()/selectnum + 1;
			
			if(pagenum==null)		//前端传回的页码,用来计算查询起点
				startindex = 0;
			else
				startindex = (Integer.parseInt(pagenum)-1)*selectnum;			
			
			
			List<Blog> blogList = blogService.selectBlogByPageAndCatIdasVisitor(user.getUser_id(), startindex, selectnum,Integer.parseInt(art_cat_id));
			for(Blog b:blogList){		//处理一下日期格式
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				b.setFormatdate(formatter.format(b.getTime()));
				if(b.getAccess_authority()==1)
					b.setPrivateinfo("");
				else if(b.getAccess_authority()==2)
					b.setPrivateinfo("私密博客");
			}
			//将文章分类信息写入session
			httpSession.setAttribute("artCatList", artCatList);
			httpSession.setAttribute("artListByCat", blogList);
			httpSession.setAttribute("artCatNowVis", artCatNowVis);
			httpSession.setAttribute("totalpage", totalpage);
			return "visitor/articlelistbycat";
		}			
		return null;
	}
	
	
	//以游览者身份阅读日志
		@RequestMapping(value="/{username}/articlelist/read/{articleID}",method=RequestMethod.GET)	
		public String DeleteArticle(@PathVariable String username,@PathVariable String articleID,ModelMap model,HttpSession httpSession) {	
			if(httpSession.getAttribute("loginuser")!=null){			
				List<Blog> blogList = blogService.selectBlogByBlogId(Integer.parseInt(articleID));
				
				//System.out.println(c.getComment_id()+c.getComment_context());
				//System.out.println("blogid= "+articleID);
				
				if(!blogList.isEmpty()){
					
					//处理博客的日期格式
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Blog selectedblog = blogList.get(0);
					selectedblog.setFormatdate(formatter.format(selectedblog.getTime()));
					//取出某个日志的评论，放入日志对象
					List<Comment> commentList = blogService.selectCommentByBlogId(selectedblog.getBlog_id());
					selectedblog.setComment(commentList);			
					
					for(Comment c:commentList){
						//取出某个日志的评论的作者，放入日志对象
						User user = userService.selectUserById(c.getFrom_user_id());
						c.setComment_writer(user);
						c.setFormatdate(formatter.format(c.getTime()));
					}
									
					httpSession.setAttribute("blogselected", selectedblog);
				}
				return "visitor/articledetail";
			}else
				return "login";		
		}
	
		
	//以访客身份转到博主相册页面
	@RequestMapping(value="/{hostuserid}/toalbumpage",method=RequestMethod.GET)	
	public String toAlbumPage(@PathVariable String hostuserid,ModelMap model,HttpSession httpSession) throws IOException {
		if(httpSession.getAttribute("loginuser")!=null){
			List<Album> albumList = albumService.getAllOpenAlbumByOwnerId(Integer.parseInt(hostuserid));
			httpSession.setAttribute("hostalbumList", albumList);
			return "visitor/album";
		}else
			return "login";		
	}
	
	//进入某个用户指定的相册中
	@RequestMapping(value="/{hostuserid}/album/{album_id}",method=RequestMethod.GET)	
	public String EnterSomeAlbum(@PathVariable String album_id,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			List<Photo> photoList = photoService.getPhotosByAlbumId(Integer.parseInt(album_id));
			httpSession.setAttribute("hostphotoList", photoList);
			Album album = albumService.selectAlbumByAlbumId(Integer.parseInt(album_id));
			httpSession.setAttribute("visitor_selected_album", album);
			return "visitor/gallery";
		}else
			return "login";		
	}
	
}

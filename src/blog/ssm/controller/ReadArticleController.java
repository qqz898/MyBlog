package blog.ssm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.ssm.domain.Album;
import blog.ssm.domain.ArtCat;
import blog.ssm.domain.Blog;
import blog.ssm.domain.Comment;
import blog.ssm.domain.User;
import blog.ssm.service.inter.AlbumService;
import blog.ssm.service.inter.ArtCatService;
import blog.ssm.service.inter.BlogService;
import blog.ssm.service.inter.PhotoService;
import blog.ssm.service.inter.UserService;
import blog.ssm.web.forms.DeleteAnCatForm;
import blog.ssm.web.forms.NewAlbumForm;
import blog.ssm.web.forms.NewArticleCatForm;

@Controller
public class ReadArticleController {

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
	
	
	//阅读日志
	@RequestMapping(value="/user/{username}/articlelist/read/{articleID}",method=RequestMethod.GET)	
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
			return "user/articledetail";
		}else
			return "login";		
	}
	
	//新建博客分类
	@RequestMapping(value="/user/{username}/buildanartcat",method=RequestMethod.POST)	
	public String BuildAnArtCat(NewArticleCatForm newArticleCatForm,ModelMap model,HttpSession httpSession) throws IOException {
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			ArtCat artCat = new ArtCat();
			artCat.setCat_name(newArticleCatForm.getArt_cat());
			artCat.setCat_user(user.getUser_id());
			artcatService.BuildArtCat(artCat);
			//更新博客分类信息
			List<ArtCat> artCatList = artcatService.selectArtCatListByUserId(user.getUser_id());
			httpSession.setAttribute("artCatList", artCatList);			
			return "user/articlelist";
		}else
			return "login";		
	}
	
	//删除博客分类
	@RequestMapping(value="/user/{username}/deleteartcat",method=RequestMethod.POST)	
	public String DeleteAnArtCat(DeleteAnCatForm deleteAnCatForm,ModelMap model,HttpSession httpSession) throws IOException {
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			//删除指定的分类
			artcatService.DeleteArtCatByCatId(user.getUser_id(), Integer.parseInt(deleteAnCatForm.getOptionsRadios()));
			//更新博客分类信息
			List<ArtCat> artCatList = artcatService.selectArtCatListByUserId(user.getUser_id());
			httpSession.setAttribute("artCatList", artCatList);			
			return "user/articlelist";
		}else
			return "login";		
	}
}

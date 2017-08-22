package blog.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import blog.ssm.Util.UploadAlbumPhoto;
import blog.ssm.Util.UploadArticlePhoto;
import blog.ssm.domain.ArtCat;
import blog.ssm.domain.Blog;
import blog.ssm.domain.User;
import blog.ssm.service.inter.ArtCatService;
import blog.ssm.service.inter.BlogService;
import blog.ssm.service.inter.UserService;
import blog.ssm.web.forms.BlogSubmissionForm;
import blog.ssm.web.forms.LoginUserForm;

@Controller
@RequestMapping("/user")
public class WriteAriticleController {

	@Resource
	private BlogService blogService;
	@Resource
	private UserService userService;
	@Resource
	private ArtCatService artcatService;
	
	//跳转到写日志界面
	@RequestMapping(value="/{username}/towrite",method=RequestMethod.GET)
	public String toWritePage(Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			List<ArtCat> artCatList = artcatService.selectArtCatListByUserId(user.getUser_id());
			
			httpSession.setAttribute("artCatList", artCatList);
			return "user/writearticle";
		}			
		return null;
	}
	
	//跳转到某个博客分类下的文章列表
	@RequestMapping(value="/{username}/articlecatlog/{art_cat_id}/{pagenum}",method=RequestMethod.GET)
	public String toCatelogPage(@PathVariable String pagenum,@PathVariable String art_cat_id,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			List<ArtCat> artCatList = artcatService.selectArtCatListByUserId(user.getUser_id());
			ArtCat artCatNow = artcatService.getAnArtCatByCatId(user.getUser_id(), Integer.parseInt(art_cat_id));		
			int startindex;	//检索起始下标
			int selectnum = 15;	//检索条数
			int totalpage;		//总共分几页显示
			
			List<Blog> l = blogService.selectBlogByUserIDAndCat(user.getUser_id(),Integer.parseInt(art_cat_id));
			
			if(l.size()%selectnum==0)		//计算总页数
				totalpage = l.size()/selectnum;
			else totalpage = l.size()/selectnum + 1;
			
			if(pagenum==null)		//前端传回的页码,用来计算查询起点
				startindex = 0;
			else
				startindex = (Integer.parseInt(pagenum)-1)*selectnum;			
			
			
			List<Blog> blogList = blogService.selectBlogByPageAndCatId(user.getUser_id(), startindex, selectnum,Integer.parseInt(art_cat_id));
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
			httpSession.setAttribute("artCatNow", artCatNow);
			httpSession.setAttribute("totalpage", totalpage);
			return "user/articlelistbycat";
		}			
		return null;
	}
	//将用户日志存入数据库
	@RequestMapping(value="/{username}/submitblog",method=RequestMethod.POST)
	public String submitArticle(BlogSubmissionForm blogSubmissionForm,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			Blog blog = new Blog();
			blog.setUser(user);
			blog.setTitle(blogSubmissionForm.getBlogtitle());
			blog.setContent(blogSubmissionForm.getBlogcontext());
			blog.setTime(new Date());
			blog.setArtcat(Integer.parseInt(blogSubmissionForm.getBlogcatelognum()));
			//option1	对所有人可见		option2　　仅自己可见
			if(blogSubmissionForm.getBlogoptions().equals("option1"))
				blog.setAccess_authority(1);
			else blog.setAccess_authority(2);
			try {
				blogService.blogSubmition(blog);			
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("日志写入数据库过程中出错,可能是由于日志中图片体积过大!"+e.getMessage());
			}
			//更新private标志			
			int startindex = 0;	//检索起始下标
			int selectnum = 15;	//检索条数
			int totalpage;		//总共分几页显示
			List<Blog> l = blogService.selectBlogByUserID(user.getUser_id());
			if(l.size()%selectnum==0)		//计算总页数
				totalpage = l.size()/selectnum;
			else totalpage = l.size()/selectnum + 1;
			List<Blog> blogList = blogService.getUserArticleByPage(user.getUser_id(), 0, 10);
			for(Blog b:blogList){		//处理一下日期格式
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				b.setFormatdate(formatter.format(b.getTime()));
				if(b.getAccess_authority()==1)
					b.setPrivateinfo("");
				else if(b.getAccess_authority()==2)
					b.setPrivateinfo("私密博客");
			}			
			//把文章和总页数传回前端
			httpSession.setAttribute("blogList", blogList);	
			httpSession.setAttribute("totalpage", totalpage);
			return "user/articlelist";
		}				
		return null;
	}
		
	//将修改好的日志更新到数据库
	@RequestMapping(value="/{username}/submitmodify/{blog_id}",method=RequestMethod.POST)
	public String submitModify(@PathVariable String blog_id,BlogSubmissionForm blogSubmissionForm,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){			
			int authority;
			//option1	对所有人可见		option2　　仅自己可见
			if(blogSubmissionForm.getBlogoptions().equals("option1"))
				authority = 1;
			else authority = 2;
			
			blogService.blogModify(Integer.parseInt(blog_id), blogSubmissionForm.getBlogtitle(), blogSubmissionForm.getBlogcontext(), new Date(), authority,Integer.parseInt(blogSubmissionForm.getBlogcatelognum()));				
			//更新private标志
			User user = (User) httpSession.getAttribute("loginuser");			
			List<Blog> blogList = blogService.getUserArticleByPage(user.getUser_id(), 0, 10);
			for(Blog b:blogList){		//处理一下日期格式
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				b.setFormatdate(formatter.format(b.getTime()));
				if(b.getAccess_authority()==1)
					b.setPrivateinfo("");
				else if(b.getAccess_authority()==2)
					b.setPrivateinfo("私密博客");
			}			
			//把文章和总页数传回前端
			httpSession.setAttribute("blogList", blogList);	
			return "user/articlelist";
		}				
		return null;
	}
	//富文本编辑器上传图片
		@RequestMapping(value="/writearticle/uploadphoto",method=RequestMethod.POST)
		public String submitModify(@RequestParam MultipartFile articlePhoto,Model model,HttpSession httpSession,HttpServletRequest req, HttpServletResponse resp){			
			if(httpSession.getAttribute("loginuser")!=null){			
				User user = (User) httpSession.getAttribute("loginuser");			
				//将图片上传至又拍云
				UploadArticlePhoto uploader = new UploadArticlePhoto();
				String url = "error|服务器端错误";
				try {
					//将MultipartFile转换成file
					CommonsMultipartFile cf= (CommonsMultipartFile)articlePhoto; 
			        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
			        File purefile = fi.getStoreLocation();
					//File purefile = new File("myfile");
					//articlePhoto.transferTo(purefile);
					String storename = articlePhoto.getOriginalFilename();
					url = uploader.upload(String.valueOf(user.getUser_id()), storename, purefile);
					//将URL返回前端
					OutputStream o = resp.getOutputStream();
					resp.setHeader("content-type", "text/html;charset=UTF-8");
					byte[] b = url.getBytes();
					o.write(b);
					o.flush();
					o.close();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}				
			return null;
		}
}

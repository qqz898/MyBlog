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
	
	//������ߵ�ģʽ��ת����Ӧid�û�����ҳ
	@RequestMapping(value="/{userid}/mainpage",method=RequestMethod.GET)
	public String toMainpageasVisitor(@PathVariable String userid,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			User user = userService.selectUserById(Integer.parseInt(userid));
			httpSession.setAttribute("hostuser", user);
			//ȡ���������5ƪ����			
			List<Blog> blogList = blogService.getFiveBlogs(user.getUser_id());
			
			//ȡ��5ƪ���͵�������
			for(Blog b:blogList){
				//ȡ��ĳ����־�����ۣ�������־����
				List<Comment> commentList = blogService.selectCommentByBlogId(b.getBlog_id());
				b.setComment_num(commentList.size());
			}
			//ȡ��վ�������û�
			List<User> allUser = userService.selectAllUsers();
			//ȡ�����������Ϣ,����ҳչʾ
			List<Album> mainPageAlbum = albumService.getLastThreeAlbum(user.getUser_id());
			httpSession.setAttribute("hostmainPageAlbum", mainPageAlbum);
			httpSession.setAttribute("allUser", allUser);
			httpSession.setAttribute("hostrecentfourblog", blogList);
			return "visitor/mainpage";
		}			
		return null;
	}
	
	//��ת�������־����
	@RequestMapping(value="/{userid}/articlelist/{pagenum}",method=RequestMethod.GET)	
	public String toLookupBlogasVisitor(@PathVariable String userid,@PathVariable String pagenum,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			//System.out.println("hahhahahahah");
			int startindex;	//������ʼ�±�
			int selectnum = 15;	//��������
			int totalpage;		//�ܹ��ּ�ҳ��ʾ
			
			
			//���ο���ݻ�ȡ�����б�����ʾ˽�ܲ���
			List<Blog> l = blogService.selectBlogByUserIDasVisitor(Integer.parseInt(userid));
			
			if(l.size()%selectnum==0)		//������ҳ��
				totalpage = l.size()/selectnum;
			else totalpage = l.size()/selectnum + 1;
			
			if(pagenum==null)		//ǰ�˴��ص�ҳ��,���������ѯ���
				startindex = 0;
			else
				startindex = (Integer.parseInt(pagenum)-1)*selectnum;
			
			
			//���濪ʼ��ѯ
			//System.out.println(user.getUser_id()+user.getName());
			
			List<Blog> blogList = blogService.getUserArticleByPageasVisitor(Integer.parseInt(userid), startindex, selectnum);
			for(Blog b:blogList){		//����һ�����ڸ�ʽ
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				b.setFormatdate(formatter.format(b.getTime()));
			}
			
			//ȡ��ĳ���û���cat
			List<ArtCat> artCatList = artcatService.selectArtCatListByUserId(Integer.parseInt(userid));
			httpSession.setAttribute("artCatList", artCatList);			
			//�����º���ҳ������ǰ��
			httpSession.setAttribute("blogList", blogList);
			httpSession.setAttribute("totalpage", totalpage);
			
				//System.out.println(b.getTitle()+b.getTime()+b.getUser());
			return "visitor/articlelist";
		}else
			return "login";		
	}
	
	//������������ת��ĳ�����ͷ����µ������б�
	@RequestMapping(value="/{username}/articlecatlog/{art_cat_id}/{pagenum}",method=RequestMethod.GET)
	public String toCatelogPage(@PathVariable String art_cat_id,@PathVariable String pagenum,Model model,HttpSession httpSession){			
		if(httpSession.getAttribute("loginuser")!=null){
			//ȡ������session�в����Ķ���
			User user = (User) httpSession.getAttribute("hostuser");
			ArtCat artCatNowVis = artcatService.getAnArtCatByCatId(user.getUser_id(), Integer.parseInt(art_cat_id));
			//�������˵����з���
			List<ArtCat> artCatList = artcatService.selectArtCatListByUserId(user.getUser_id());
					
			int startindex;	//������ʼ�±�
			int selectnum = 15;	//��������
			int totalpage;		//�ܹ��ּ�ҳ��ʾ
			
			List<Blog> l = blogService.selectBlogByUserIDAndCatasVisitor(user.getUser_id(),Integer.parseInt(art_cat_id));
			
			
			if(l.size()%selectnum==0)		//������ҳ��
				totalpage = l.size()/selectnum;
			else totalpage = l.size()/selectnum + 1;
			
			if(pagenum==null)		//ǰ�˴��ص�ҳ��,���������ѯ���
				startindex = 0;
			else
				startindex = (Integer.parseInt(pagenum)-1)*selectnum;			
			
			
			List<Blog> blogList = blogService.selectBlogByPageAndCatIdasVisitor(user.getUser_id(), startindex, selectnum,Integer.parseInt(art_cat_id));
			for(Blog b:blogList){		//����һ�����ڸ�ʽ
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				b.setFormatdate(formatter.format(b.getTime()));
				if(b.getAccess_authority()==1)
					b.setPrivateinfo("");
				else if(b.getAccess_authority()==2)
					b.setPrivateinfo("˽�ܲ���");
			}
			//�����·�����Ϣд��session
			httpSession.setAttribute("artCatList", artCatList);
			httpSession.setAttribute("artListByCat", blogList);
			httpSession.setAttribute("artCatNowVis", artCatNowVis);
			httpSession.setAttribute("totalpage", totalpage);
			return "visitor/articlelistbycat";
		}			
		return null;
	}
	
	
	//������������Ķ���־
		@RequestMapping(value="/{username}/articlelist/read/{articleID}",method=RequestMethod.GET)	
		public String DeleteArticle(@PathVariable String username,@PathVariable String articleID,ModelMap model,HttpSession httpSession) {	
			if(httpSession.getAttribute("loginuser")!=null){			
				List<Blog> blogList = blogService.selectBlogByBlogId(Integer.parseInt(articleID));
				
				//System.out.println(c.getComment_id()+c.getComment_context());
				//System.out.println("blogid= "+articleID);
				
				if(!blogList.isEmpty()){
					
					//�����͵����ڸ�ʽ
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Blog selectedblog = blogList.get(0);
					selectedblog.setFormatdate(formatter.format(selectedblog.getTime()));
					//ȡ��ĳ����־�����ۣ�������־����
					List<Comment> commentList = blogService.selectCommentByBlogId(selectedblog.getBlog_id());
					selectedblog.setComment(commentList);			
					
					for(Comment c:commentList){
						//ȡ��ĳ����־�����۵����ߣ�������־����
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
	
		
	//�Էÿ����ת���������ҳ��
	@RequestMapping(value="/{hostuserid}/toalbumpage",method=RequestMethod.GET)	
	public String toAlbumPage(@PathVariable String hostuserid,ModelMap model,HttpSession httpSession) throws IOException {
		if(httpSession.getAttribute("loginuser")!=null){
			List<Album> albumList = albumService.getAllOpenAlbumByOwnerId(Integer.parseInt(hostuserid));
			httpSession.setAttribute("hostalbumList", albumList);
			return "visitor/album";
		}else
			return "login";		
	}
	
	//����ĳ���û�ָ���������
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

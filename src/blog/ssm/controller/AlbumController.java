package blog.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import blog.ssm.Util.DeleteAlbumPhoto;
import blog.ssm.Util.UploadAlbumPhoto;
import blog.ssm.Util.UploadPhoto;
import blog.ssm.domain.Album;
import blog.ssm.domain.Blog;
import blog.ssm.domain.Photo;
import blog.ssm.domain.User;
import blog.ssm.service.inter.AlbumService;
import blog.ssm.service.inter.BlogService;
import blog.ssm.service.inter.PhotoService;
import blog.ssm.service.inter.UserService;
import blog.ssm.web.forms.NewAlbumForm;

@Controller
public class AlbumController {

	@Resource
	private BlogService blogService;
	@Resource
	private UserService userService;
	@Resource
	private AlbumService albumService;
	@Resource
	private PhotoService photoService;
	//转到用户相册页面
	@RequestMapping(value="/user/{username}/toalbumpage",method=RequestMethod.GET)	
	public String toAlbumPage(ModelMap model,HttpSession httpSession) throws IOException {
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			List<Album> albumList = albumService.getAlbumByOwnerId(user.getUser_id());
			httpSession.setAttribute("albumList", albumList);			
			return "user/album";
		}else
			return "login";		
	}
	//转到具体某个相册的展示页面(测试用)
	@RequestMapping(value="/user/{username}/togallery",method=RequestMethod.GET)	
	public String toGalleryPage(ModelMap model,HttpSession httpSession) throws IOException {
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			
			return "user/gallery";
		}else
			return "login";		
	}
	
	//新建相册
	@RequestMapping(value="/user/{username}/buildanalbum",method=RequestMethod.POST)	
	public String BuildAnAlbum(NewAlbumForm newAlbumForm,ModelMap model,HttpSession httpSession) throws IOException {
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			//把相册信息存入数据库
			Album album = new Album();
			album.setAlbum_name(newAlbumForm.getAlbum_name());
			album.setAlbum_intro(newAlbumForm.getAlbum_intro());
			album.setAlbum_owner(user.getUser_id());
			if(newAlbumForm.getIfsecured()==null)
				album.setAlbum_authority(0);
			else if(newAlbumForm.getIfsecured().equals("yes"))
				album.setAlbum_authority(1); 		//authority为1表示是私密相册
			
			albumService.BuildAlbum(album);
			//更新相册信息
			List<Album> albumList = albumService.getAlbumByOwnerId(user.getUser_id());
			httpSession.setAttribute("albumList", albumList);			
			return "user/album";
		}else
			return "login";		
	}
	
	//进入某个用户指定的相册中
	@RequestMapping(value="/user/{username}/album/{album_id}",method=RequestMethod.GET)	
	public String EnterSomeAlbum(@PathVariable String album_id,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			List<Photo> photoList = photoService.getPhotosByAlbumId(Integer.parseInt(album_id));
			httpSession.setAttribute("photoList", photoList);
			Album album = albumService.selectAlbumByAlbumId(Integer.parseInt(album_id));
			httpSession.setAttribute("selected_album", album);
			return "user/gallery";
		}else
			return "login";		
	}
	
	//删除某个相册
	@RequestMapping(value="/user/{username}/album/deletealbum/{album_id}",method=RequestMethod.GET)	
	public String DeleteAlbum(@PathVariable String album_id,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			List<Photo> photoList = (List<Photo>) httpSession.getAttribute("photoList");
			if(photoList.isEmpty()){
				albumService.deleteAlbumByAlbumId(Integer.parseInt(album_id));
				//更新相册信息
				List<Album> albumList = albumService.getAlbumByOwnerId(user.getUser_id());
				httpSession.setAttribute("albumList", albumList);			
				return "user/album";
			}else
				return "user/album";
		}else
			return "login";		
	}
	
	//删除某个照片
	@RequestMapping(value="/user/{username}/album/deletephoto/{photo_id}",method=RequestMethod.GET)	
	public String DeletePhoto(@PathVariable String photo_id,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			Photo photo = photoService.selectPhotoByPhotoId(Integer.parseInt(photo_id));
			photoService.deletePhotoByPhotoId(Integer.parseInt(photo_id));
			//删除云服务器中的照片
			DeleteAlbumPhoto delUpYun = new DeleteAlbumPhoto();
			try {				
				delUpYun.deletePhotoInUpYun(photo.getPhoto_storename(), String.valueOf(user.getUser_id()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//更新
			Album album = (Album) httpSession.getAttribute("selected_album");
			List<Photo> photoList = photoService.getPhotosByAlbumId(album.getAlbum_id());
			httpSession.setAttribute("photoList", photoList);
			return "user/gallery";
			
		}else
			return "login";		
	}
	

	//用户上传照片处理
		@RequestMapping(value="/user/{username}/album/{album_id}/uploadphoto",method=RequestMethod.POST)	
		public String uploadPersonIcon(@RequestParam MultipartFile[] personPhoto,@PathVariable String album_id,ModelMap model,HttpSession httpSession) throws IOException {
			if(httpSession.getAttribute("loginuser")!=null){
				User user = (User) httpSession.getAttribute("loginuser");
				//将图片上传至又拍云
				UploadAlbumPhoto uploader = new UploadAlbumPhoto();
				for(MultipartFile file:personPhoto){
					//将MultipartFile转换成file
					
			        CommonsMultipartFile cf= (CommonsMultipartFile)file; 
			        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
			        File purefile = fi.getStoreLocation();
					//File  = new File("myfile");
					Photo photo = new Photo();
					//file.transferTo(purefile);
					String storename = String.valueOf(System.currentTimeMillis())+file.getOriginalFilename();
					String url = "";
					try {
						url = uploader.upload(String.valueOf(user.getUser_id()), storename, purefile);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("上传图片至又拍云过程中出错!");
						e.printStackTrace();
					}				 
					//构造一个照片对象
					photo.setPhoto_url(url);
					photo.setPhoto_album(Integer.parseInt(album_id));
					photo.setPhoto_oldname(file.getOriginalFilename());
					photo.setPhoto_storename(storename);
					photoService.addPhotoToDb(photo);
				}
				//更新照片信息
				List<Photo> photoList = photoService.getPhotosByAlbumId(Integer.parseInt(album_id));
				httpSession.setAttribute("photoList", photoList);
				//将封面信息写入数据库
				albumService.SetFrontCover(Integer.parseInt(album_id), photoList.get(photoList.size()-1).getPhoto_url());
				return "user/gallery";
			}else
				return null;		
		}

}

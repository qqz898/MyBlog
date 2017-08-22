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
	//ת���û����ҳ��
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
	//ת������ĳ������չʾҳ��(������)
	@RequestMapping(value="/user/{username}/togallery",method=RequestMethod.GET)	
	public String toGalleryPage(ModelMap model,HttpSession httpSession) throws IOException {
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			
			return "user/gallery";
		}else
			return "login";		
	}
	
	//�½����
	@RequestMapping(value="/user/{username}/buildanalbum",method=RequestMethod.POST)	
	public String BuildAnAlbum(NewAlbumForm newAlbumForm,ModelMap model,HttpSession httpSession) throws IOException {
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			//�������Ϣ�������ݿ�
			Album album = new Album();
			album.setAlbum_name(newAlbumForm.getAlbum_name());
			album.setAlbum_intro(newAlbumForm.getAlbum_intro());
			album.setAlbum_owner(user.getUser_id());
			if(newAlbumForm.getIfsecured()==null)
				album.setAlbum_authority(0);
			else if(newAlbumForm.getIfsecured().equals("yes"))
				album.setAlbum_authority(1); 		//authorityΪ1��ʾ��˽�����
			
			albumService.BuildAlbum(album);
			//���������Ϣ
			List<Album> albumList = albumService.getAlbumByOwnerId(user.getUser_id());
			httpSession.setAttribute("albumList", albumList);			
			return "user/album";
		}else
			return "login";		
	}
	
	//����ĳ���û�ָ���������
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
	
	//ɾ��ĳ�����
	@RequestMapping(value="/user/{username}/album/deletealbum/{album_id}",method=RequestMethod.GET)	
	public String DeleteAlbum(@PathVariable String album_id,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			List<Photo> photoList = (List<Photo>) httpSession.getAttribute("photoList");
			if(photoList.isEmpty()){
				albumService.deleteAlbumByAlbumId(Integer.parseInt(album_id));
				//���������Ϣ
				List<Album> albumList = albumService.getAlbumByOwnerId(user.getUser_id());
				httpSession.setAttribute("albumList", albumList);			
				return "user/album";
			}else
				return "user/album";
		}else
			return "login";		
	}
	
	//ɾ��ĳ����Ƭ
	@RequestMapping(value="/user/{username}/album/deletephoto/{photo_id}",method=RequestMethod.GET)	
	public String DeletePhoto(@PathVariable String photo_id,ModelMap model,HttpSession httpSession) {	
		if(httpSession.getAttribute("loginuser")!=null){
			User user = (User) httpSession.getAttribute("loginuser");
			Photo photo = photoService.selectPhotoByPhotoId(Integer.parseInt(photo_id));
			photoService.deletePhotoByPhotoId(Integer.parseInt(photo_id));
			//ɾ���Ʒ������е���Ƭ
			DeleteAlbumPhoto delUpYun = new DeleteAlbumPhoto();
			try {				
				delUpYun.deletePhotoInUpYun(photo.getPhoto_storename(), String.valueOf(user.getUser_id()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//����
			Album album = (Album) httpSession.getAttribute("selected_album");
			List<Photo> photoList = photoService.getPhotosByAlbumId(album.getAlbum_id());
			httpSession.setAttribute("photoList", photoList);
			return "user/gallery";
			
		}else
			return "login";		
	}
	

	//�û��ϴ���Ƭ����
		@RequestMapping(value="/user/{username}/album/{album_id}/uploadphoto",method=RequestMethod.POST)	
		public String uploadPersonIcon(@RequestParam MultipartFile[] personPhoto,@PathVariable String album_id,ModelMap model,HttpSession httpSession) throws IOException {
			if(httpSession.getAttribute("loginuser")!=null){
				User user = (User) httpSession.getAttribute("loginuser");
				//��ͼƬ�ϴ���������
				UploadAlbumPhoto uploader = new UploadAlbumPhoto();
				for(MultipartFile file:personPhoto){
					//��MultipartFileת����file
					
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
						System.out.println("�ϴ�ͼƬ�������ƹ����г���!");
						e.printStackTrace();
					}				 
					//����һ����Ƭ����
					photo.setPhoto_url(url);
					photo.setPhoto_album(Integer.parseInt(album_id));
					photo.setPhoto_oldname(file.getOriginalFilename());
					photo.setPhoto_storename(storename);
					photoService.addPhotoToDb(photo);
				}
				//������Ƭ��Ϣ
				List<Photo> photoList = photoService.getPhotosByAlbumId(Integer.parseInt(album_id));
				httpSession.setAttribute("photoList", photoList);
				//��������Ϣд�����ݿ�
				albumService.SetFrontCover(Integer.parseInt(album_id), photoList.get(photoList.size()-1).getPhoto_url());
				return "user/gallery";
			}else
				return null;		
		}

}

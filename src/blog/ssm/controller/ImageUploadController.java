package blog.ssm.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import blog.ssm.Util.UploadPhoto;
import blog.ssm.domain.User;
import blog.ssm.service.inter.BlogService;
import blog.ssm.service.inter.UserService;

@Controller
public class ImageUploadController {

	@Resource
	private BlogService blogService;
	@Resource
	private UserService userService;
	//�ϴ��û�ͷ����
		@RequestMapping(value="/user/uploadicon",method=RequestMethod.POST)	
		public String uploadPersonIcon(@RequestParam("personIcon") MultipartFile file,ModelMap model,HttpSession httpSession) throws IOException {
			if(httpSession.getAttribute("loginuser")!=null){
				User user = (User) httpSession.getAttribute("loginuser");
				UploadPhoto uploader = new UploadPhoto();
				//��MultipartFileת����file
				//File purefile = new File("myfile");
				//file.transferTo(purefile);
				CommonsMultipartFile cf= (CommonsMultipartFile)file; 
		        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
		        File purefile = fi.getStoreLocation();
				String url = "";
				try {
					url = uploader.upload(String.valueOf(user.getUser_id()), file.getOriginalFilename(), purefile);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("�ϴ�ͷ���������ƹ����г���!");
					e.printStackTrace();
				}
				
				userService.setUserIcon(user.getUser_id(), url);
				//����session�е��û���Ϣ(ͷ��)
				user.setIcon(url);
				httpSession.setAttribute("loginuser", user);
				return "user/personalsettings";
			}else
				return null;		
		}
}

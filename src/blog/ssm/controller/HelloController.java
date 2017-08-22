package blog.ssm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.ssm.domain.Blog;
import blog.ssm.domain.User;
import blog.ssm.mapper.UserMapper;
import blog.ssm.service.inter.BlogService;

import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/hello")
public class HelloController{
   @Resource
   private BlogService blogService;
   @RequestMapping(method = RequestMethod.GET)
   public String printHello(ModelMap model) {
	   System.out.println("lskdjflsakdjfsdl");
	  List<Blog> list = blogService.selectBlogByUserID(1);
	  for(Blog b : list){
		  System.out.println(b.getTitle()+" "+b.getContent());
	  }
      model.addAttribute("message", "Hello Spring MVC Framework!");
      
      return "login";
   }

}
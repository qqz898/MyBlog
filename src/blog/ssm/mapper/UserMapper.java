package blog.ssm.mapper;

import java.util.List;

import blog.ssm.domain.Comment;
import blog.ssm.domain.User;

public interface UserMapper {

	public List<User> selectUserByBlogID(int id);	
	public User getUserByNameAndPwd(String username,String password);
	public void doSignupProcess(User user);
	public User selectUserById(int id);
	public List<Comment> selectMessageBoardByUserId(int userid);
	public void modifyPassword(int userid,int newpassword);
	public void modifyPersonalInfo(User user);
	public User selectUserByName(String name);
	public void setUserIcon(int userid,String url);
	public List<User> selectAllUsers();
}

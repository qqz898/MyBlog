package blog.ssm.service.inter;

import java.util.List;

import blog.ssm.domain.Comment;
import blog.ssm.domain.User;

public interface UserService {

	public User getUserByNameAndPwd(String name,String pwd);
	public void doSignupProcess(User user);
	//通过用户ID查找用户
	public User selectUserById(int id);
	//通过用户ID查找留言板留言
	public List<Comment> selectMessageBoardByUserId(int userid);
	//修改密码
	public void modifyPassword(int userid,int newpassword);
	//修改用户信息
	public void modifyPersonalInfo(User user);
	//通用用户名查找用户
	public User seletUserByName(String name);
	//设置用户的头像(url)
	public void setUserIcon(int userid,String url);
	//取出所有用户
	public List<User> selectAllUsers();
}

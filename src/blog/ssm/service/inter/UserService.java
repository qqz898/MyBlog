package blog.ssm.service.inter;

import java.util.List;

import blog.ssm.domain.Comment;
import blog.ssm.domain.User;

public interface UserService {

	public User getUserByNameAndPwd(String name,String pwd);
	public void doSignupProcess(User user);
	//ͨ���û�ID�����û�
	public User selectUserById(int id);
	//ͨ���û�ID�������԰�����
	public List<Comment> selectMessageBoardByUserId(int userid);
	//�޸�����
	public void modifyPassword(int userid,int newpassword);
	//�޸��û���Ϣ
	public void modifyPersonalInfo(User user);
	//ͨ���û��������û�
	public User seletUserByName(String name);
	//�����û���ͷ��(url)
	public void setUserIcon(int userid,String url);
	//ȡ�������û�
	public List<User> selectAllUsers();
}

package blog.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.ssm.domain.Comment;
import blog.ssm.domain.User;
import blog.ssm.mapper.UserMapper;
import blog.ssm.service.inter.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public User getUserByNameAndPwd(String name,String pwd) {
		// TODO Auto-generated method stub
		return userMapper.getUserByNameAndPwd(name, pwd);
	}
	@Override
	public void doSignupProcess(User user) {
		// TODO Auto-generated method stub
		userMapper.doSignupProcess(user);
	}
	@Override
	public User selectUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectUserById(id);
	}
	@Override
	public List<Comment> selectMessageBoardByUserId(int userid) {
		// TODO Auto-generated method stub
		return userMapper.selectMessageBoardByUserId(userid);
	}
	@Override
	public void modifyPassword(int userid, int newpassword) {
		// TODO Auto-generated method stub
		userMapper.modifyPassword(userid, newpassword);
	}
	@Override
	public void modifyPersonalInfo(User user) {
		// TODO Auto-generated method stub
		userMapper.modifyPersonalInfo(user);
	}
	@Override
	public User seletUserByName(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByName(name);
	}
	@Override
	public void setUserIcon(int userid, String url) {
		// TODO Auto-generated method stub
		userMapper.setUserIcon(userid, url);
	}
	@Override
	public List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		return userMapper.selectAllUsers();
	}
	

}

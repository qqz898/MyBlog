package blog.ssm.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import blog.ssm.Util.MyBatisUtil;
import blog.ssm.domain.Blog;
import blog.ssm.domain.User;
import blog.ssm.mapper.UserMapper;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		
		
		SqlSessionFactory sqlSessionFactory = null;  
	    sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();  
						
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			  UserMapper mapper = session.getMapper(UserMapper.class);
			  List<User> user = mapper.selectUserByBlogID(1);
			  List<Blog> blogList = user.get(0).getBlog();
			  for(Blog b:blogList)
					System.out.println(b.getTitle()+b.getContent());
			} finally {
			  session.close();
			}
		}

}

package blog.ssm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.ssm.domain.Blog;
import blog.ssm.domain.Comment;
import blog.ssm.domain.User;
import blog.ssm.mapper.BlogMapper;
import blog.ssm.service.inter.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
	@Resource
	private BlogMapper blogMapper;
	
	@Override
	public List<Blog> selectBlogByUserID(int id) {
		// TODO Auto-generated method stub
		return blogMapper.selectBlogByUserID(id);
	}
	
	@Override
	public void blogSubmition(Blog blog) {
		// TODO Auto-generated method stub
		blogMapper.submitBlog(blog);
	}
	
	@Override
	public List<Blog> getUserArticleByPage(int ownerID, int startindex, int selectnum) {
		// TODO Auto-generated method stub
		return blogMapper.getUserArticleByPage(ownerID, startindex, selectnum);
	}
	
	@Override
	public void deleteBlogByArticleID(int blogid) {
		// TODO Auto-generated method stub
		blogMapper.deleteBlogByArticleID(blogid);
	}
	
	@Override
	public List<Blog> selectBlogByBlogId(int id) {
		// TODO Auto-generated method stub
		return blogMapper.selectBlogByBlogId(id);
	}
	@Override
	public void blogModify(int id,String title,String content,Date time,int access_authority,int artcat) {
		// TODO Auto-generated method stub
		blogMapper.modifyBlogById(id, title, content, time, access_authority,artcat);
	}
	
	@Override
	public List<Comment> selectCommentByBlogId(int id) {
		// TODO Auto-generated method stub
		return blogMapper.selectCommentByBlogId(id);
	}
	@Override
	public void submitBlogComment(Comment comment) {
		// TODO Auto-generated method stub
		blogMapper.submitBlogComment(comment);
	}
	@Override
	public List<Blog> selectBlogByUserIDasVisitor(int id) {
		// TODO Auto-generated method stub
		return blogMapper.selectBlogByUserIDasVisitor(id);
	}
	@Override
	public List<Blog> getUserArticleByPageasVisitor(int ownerID, int startindex, int selectnum) {
		// TODO Auto-generated method stub
		return blogMapper.getUserArticleByPageasVisitor(ownerID, startindex, selectnum);
	}
	@Override
	public void deleteMessageByMessageID(int messageid) {
		// TODO Auto-generated method stub
		blogMapper.deleteMessageByMessageID(messageid);
	}
	@Override
	public List<Blog> getFiveBlogs(int user_id) {
		// TODO Auto-generated method stub
		return blogMapper.getFiveBlogs(user_id);
	}
	@Override
	public List<Blog> selectBlogByPageAndCatId(int ownerID, int startindex, int selectnum, int catid) {
		// TODO Auto-generated method stub
		return blogMapper.selectBlogByPageAndCatId(ownerID, startindex, selectnum, catid);
	}
	@Override
	public List<Blog> selectBlogByUserIDAndCat(int id, int catid) {
		// TODO Auto-generated method stub
		return blogMapper.selectBlogByUserIDAndCat(id, catid);
	}
	@Override
	public List<Blog> selectBlogByUserIDAndCatasVisitor(int id, int catid) {
		// TODO Auto-generated method stub
		return blogMapper.selectBlogByUserIDAndCatasVisitor(id, catid);
	}
	@Override
	public List<Blog> selectBlogByPageAndCatIdasVisitor(int ownerID, int startindex, int selectnum, int catid) {
		// TODO Auto-generated method stub
		return blogMapper.selectBlogByPageAndCatIdasVisitor(ownerID, startindex, selectnum, catid);
	}
	
	
	
	
	
}

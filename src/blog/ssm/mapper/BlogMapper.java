package blog.ssm.mapper;

import java.util.Date;
import java.util.List;

import blog.ssm.domain.Blog;
import blog.ssm.domain.Comment;
import blog.ssm.domain.User;

public interface BlogMapper {

	public List<Blog> selectBlogByUserID(int id);
	public List<Blog> selectBlogByUserIDAndCat(int id, int catid);
	public void submitBlog(Blog blog);
	public List<Blog> getUserArticleByPage(int ownerID,int startindex,int selectnum);
	public void deleteBlogByArticleID(int blogID);
	public List<Blog> selectBlogByBlogId(int id);
	public void modifyBlogById(int id,String title,String content,Date time,int access_authority,int artcat);
	public List<Comment> selectCommentByBlogId(int id);
	public void submitBlogComment(Comment comment);
	public List<Blog> selectBlogByUserIDasVisitor(int id);
	public List<Blog> getUserArticleByPageasVisitor(int ownerID,int startindex,int selectnum);
	public void deleteMessageByMessageID(int messageid);
	public List<Blog> getFiveBlogs(int user_id);
	public List<Blog> selectBlogByPageAndCatId(int ownerID, int startindex, int selectnum, int catid);
	public List<Blog> selectBlogByUserIDAndCatasVisitor(int id, int catid);
	public List<Blog> selectBlogByPageAndCatIdasVisitor(int ownerID, int startindex, int selectnum, int catid);
	
}

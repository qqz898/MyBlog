package blog.ssm.service.inter;

import java.util.Date;
import java.util.List;

import blog.ssm.domain.Blog;
import blog.ssm.domain.Comment;
import blog.ssm.domain.User;

public interface BlogService {

	//通过用户（博主）ID查找博客
	public List<Blog> selectBlogByUserID(int id);
	//通过用户（博主）ID和博客分类查找博客
	public List<Blog> selectBlogByUserIDAndCat(int id,int catid);	
	//以游客身份通过用户（博主）ID和博客分类查找博客
	public List<Blog> selectBlogByUserIDAndCatasVisitor(int id,int catid);	
	//将用户提交的博客写入数据库
	public void blogSubmition(Blog blog);
	//分页查询用户博客
	public List<Blog> getUserArticleByPage(int ownerID,int startindex,int selectnum);
	//按博客ID删除博客
	public void deleteBlogByArticleID(int blogid);
	//按博客ID查找博客
	public List<Blog> selectBlogByBlogId(int id);
	//按博主ID和cat分页查找博客
	public List<Blog> selectBlogByPageAndCatId(int ownerID,int startindex,int selectnum,int catid);
	//以游客身份按博主ID和cat分页查找博客
	public List<Blog> selectBlogByPageAndCatIdasVisitor(int ownerID,int startindex,int selectnum,int catid);
	//按博客ID修改博客
	public void blogModify(int id,String title,String content,Date time,int access_authority,int artcat);
	//按博客ID查询评论
	public List<Comment> selectCommentByBlogId(int id);
	//提交博客中的评论
	public void submitBlogComment(Comment comment);
	//以游客身份通过ID查找博客，不显示私密博客
	public List<Blog> selectBlogByUserIDasVisitor(int id);
	//分页查询用户博客,以游客身份，不查询私密博客
	public List<Blog> getUserArticleByPageasVisitor(int ownerID,int startindex,int selectnum);
	//按留言ID删除留言
	public void deleteMessageByMessageID(int messageid);
	//查找五条最新博客(非私密)供主页展示用
	public List<Blog> getFiveBlogs(int user_id);
	
	
	
	
	
	
	
	
	
}

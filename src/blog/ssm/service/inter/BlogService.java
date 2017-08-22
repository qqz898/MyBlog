package blog.ssm.service.inter;

import java.util.Date;
import java.util.List;

import blog.ssm.domain.Blog;
import blog.ssm.domain.Comment;
import blog.ssm.domain.User;

public interface BlogService {

	//ͨ���û���������ID���Ҳ���
	public List<Blog> selectBlogByUserID(int id);
	//ͨ���û���������ID�Ͳ��ͷ�����Ҳ���
	public List<Blog> selectBlogByUserIDAndCat(int id,int catid);	
	//���ο����ͨ���û���������ID�Ͳ��ͷ�����Ҳ���
	public List<Blog> selectBlogByUserIDAndCatasVisitor(int id,int catid);	
	//���û��ύ�Ĳ���д�����ݿ�
	public void blogSubmition(Blog blog);
	//��ҳ��ѯ�û�����
	public List<Blog> getUserArticleByPage(int ownerID,int startindex,int selectnum);
	//������IDɾ������
	public void deleteBlogByArticleID(int blogid);
	//������ID���Ҳ���
	public List<Blog> selectBlogByBlogId(int id);
	//������ID��cat��ҳ���Ҳ���
	public List<Blog> selectBlogByPageAndCatId(int ownerID,int startindex,int selectnum,int catid);
	//���ο���ݰ�����ID��cat��ҳ���Ҳ���
	public List<Blog> selectBlogByPageAndCatIdasVisitor(int ownerID,int startindex,int selectnum,int catid);
	//������ID�޸Ĳ���
	public void blogModify(int id,String title,String content,Date time,int access_authority,int artcat);
	//������ID��ѯ����
	public List<Comment> selectCommentByBlogId(int id);
	//�ύ�����е�����
	public void submitBlogComment(Comment comment);
	//���ο����ͨ��ID���Ҳ��ͣ�����ʾ˽�ܲ���
	public List<Blog> selectBlogByUserIDasVisitor(int id);
	//��ҳ��ѯ�û�����,���ο���ݣ�����ѯ˽�ܲ���
	public List<Blog> getUserArticleByPageasVisitor(int ownerID,int startindex,int selectnum);
	//������IDɾ������
	public void deleteMessageByMessageID(int messageid);
	//�����������²���(��˽��)����ҳչʾ��
	public List<Blog> getFiveBlogs(int user_id);
	
	
	
	
	
	
	
	
	
}

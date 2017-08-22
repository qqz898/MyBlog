package blog.ssm.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Blog implements Serializable{

	private int blog_id;
	private User user;
	private String title;
	private String content;
	private int access_authority;
	private Date time;
	private String formatdate;
	private List<Comment> comment;
	private int comment_num;
	private String privateinfo;
	private int artcat;
	
	
	
	public int getArtcat() {
		return artcat;
	}
	public void setArtcat(int artcat) {
		this.artcat = artcat;
	}
	public String getPrivateinfo() {
		return privateinfo;
	}
	public void setPrivateinfo(String privateinfo) {
		this.privateinfo = privateinfo;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public int getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAccess_authority() {
		return access_authority;
	}
	public void setAccess_authority(int access_authority) {
		this.access_authority = access_authority;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getFormatdate() {
		return formatdate;
	}
	public void setFormatdate(String formatdate) {
		this.formatdate = formatdate;
	}
	
	
	
	
}

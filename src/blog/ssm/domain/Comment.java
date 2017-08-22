package blog.ssm.domain;

import java.util.Date;

public class Comment {

	private int comment_id;
	private int from_user_id;
	private int to_user_id;
	private	int comment_type;
	private int comment_blog_id;
	private User comment_writer;
	private String comment_context;
	private Date time;
	private String formatdate;
	
	
	
	public String getFormatdate() {
		return formatdate;
	}
	public void setFormatdate(String formatdate) {
		this.formatdate = formatdate;
	}
	public User getComment_writer() {
		return comment_writer;
	}
	public void setComment_writer(User comment_writer) {
		this.comment_writer = comment_writer;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getFrom_user_id() {
		return from_user_id;
	}
	public void setFrom_user_id(int from_user_id) {
		this.from_user_id = from_user_id;
	}
	public int getTo_user_id() {
		return to_user_id;
	}
	public void setTo_user_id(int to_user_id) {
		this.to_user_id = to_user_id;
	}
	public int getComment_type() {
		return comment_type;
	}
	public void setComment_type(int comment_type) {
		this.comment_type = comment_type;
	}
	public int getComment_blog_id() {
		return comment_blog_id;
	}
	public void setComment_blog_id(int comment_blog_id) {
		this.comment_blog_id = comment_blog_id;
	}
	public String getComment_context() {
		return comment_context;
	}
	public void setComment_context(String comment_context) {
		this.comment_context = comment_context;
	}
	
}

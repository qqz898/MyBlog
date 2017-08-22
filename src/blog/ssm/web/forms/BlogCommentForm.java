package blog.ssm.web.forms;

public class BlogCommentForm {

	private String blogid;
	private String commentcontext;
	private String commentwriterid;
	private String commentreceiver;
	public String getBlogid() {
		return blogid;
	}
	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}
	public String getCommentcontext() {
		return commentcontext;
	}
	public void setCommentcontext(String commentcontext) {
		this.commentcontext = commentcontext;
	}
	public String getCommentwriterid() {
		return commentwriterid;
	}
	public void setCommentwriterid(String commentwriterid) {
		this.commentwriterid = commentwriterid;
	}
	public String getCommentreceiver() {
		return commentreceiver;
	}
	public void setCommentreceiver(String commentreceiver) {
		this.commentreceiver = commentreceiver;
	}
	
	
}

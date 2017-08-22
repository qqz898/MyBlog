package blog.ssm.web.forms;

public class BlogSubmissionForm {

	private String blogtitle;
	private String blogcontext;
	private String blogoptions;
	private String blogcatelognum;
	
	
	public String getBlogcatelognum() {
		return blogcatelognum;
	}
	public void setBlogcatelognum(String blogcatelognum) {
		this.blogcatelognum = blogcatelognum;
	}
	public String getBlogtitle() {
		return blogtitle;
	}
	public void setBlogtitle(String blogtitle) {
		this.blogtitle = blogtitle;
	}
	public String getBlogcontext() {
		return blogcontext;
	}
	public void setBlogcontext(String blogcontext) {
		this.blogcontext = blogcontext;
	}
	public String getBlogoptions() {
		return blogoptions;
	}
	public void setBlogoptions(String blogoptions) {
		this.blogoptions = blogoptions;
	}
	
	
}

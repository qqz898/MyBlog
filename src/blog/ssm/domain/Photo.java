package blog.ssm.domain;

public class Photo {

	private int photo_id;
	private String photo_url;
	private int photo_album;
	private String photo_oldname;
	private String photo_storename;
	
	
	public String getPhoto_oldname() {
		return photo_oldname;
	}
	public void setPhoto_oldname(String photo_oldname) {
		this.photo_oldname = photo_oldname;
	}
	public String getPhoto_storename() {
		return photo_storename;
	}
	public void setPhoto_storename(String photo_storename) {
		this.photo_storename = photo_storename;
	}
	public int getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public int getPhoto_album() {
		return photo_album;
	}
	public void setPhoto_album(int photo_album) {
		this.photo_album = photo_album;
	}
	
	
}

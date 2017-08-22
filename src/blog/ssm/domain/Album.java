package blog.ssm.domain;

import java.util.List;

public class Album {

	private int album_id;
	private int album_owner;
	private String album_intro;
	private int album_authority;
	private String album_name;
	private String album_frontcover;
	private List<Photo> photo;
	
	
	public String getAlbum_frontcover() {
		return album_frontcover;
	}
	public void setAlbum_frontcover(String album_frontcover) {
		this.album_frontcover = album_frontcover;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public List<Photo> getPhoto() {
		return photo;
	}
	public void setPhoto(List<Photo> photo) {
		this.photo = photo;
	}
	public int getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	public int getAlbum_owner() {
		return album_owner;
	}
	public void setAlbum_owner(int album_owner) {
		this.album_owner = album_owner;
	}
	public String getAlbum_intro() {
		return album_intro;
	}
	public void setAlbum_intro(String album_intro) {
		this.album_intro = album_intro;
	}
	public int getAlbum_authority() {
		return album_authority;
	}
	public void setAlbum_authority(int album_authority) {
		this.album_authority = album_authority;
	}
	
	
}

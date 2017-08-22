package blog.ssm.mapper;

import java.util.List;

import blog.ssm.domain.Photo;

public interface PhotoMapper {

	public List<Photo> getPhotosByAlbumId(int album_id);
	public void deletePhotoByPhotoId(int photo_id);
	public void addPhotoToDb(Photo photo);
	public Photo selectPhotoByPhotoId(int photo_id);
}

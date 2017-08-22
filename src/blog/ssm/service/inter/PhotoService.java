package blog.ssm.service.inter;

import java.util.List;

import blog.ssm.domain.Photo;

public interface PhotoService {

	//根据album id查找其中的照片
	public List<Photo> getPhotosByAlbumId(int album_id);
	//根据photo_id删除photo;
	public void deletePhotoByPhotoId(int photo_id);
	//插入新图片
	public void addPhotoToDb(Photo photo);
	//根据照片id查找照片
	public Photo selectPhotoByPhotoId(int photo_id);
}

package blog.ssm.service.inter;

import java.util.List;

import blog.ssm.domain.Photo;

public interface PhotoService {

	//����album id�������е���Ƭ
	public List<Photo> getPhotosByAlbumId(int album_id);
	//����photo_idɾ��photo;
	public void deletePhotoByPhotoId(int photo_id);
	//������ͼƬ
	public void addPhotoToDb(Photo photo);
	//������Ƭid������Ƭ
	public Photo selectPhotoByPhotoId(int photo_id);
}

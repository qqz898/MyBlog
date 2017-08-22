package blog.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.ssm.domain.Photo;
import blog.ssm.mapper.AlbumMapper;
import blog.ssm.mapper.PhotoMapper;
import blog.ssm.service.inter.PhotoService;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService {

	@Resource
	private PhotoMapper photoMapper;
	@Override
	public List<Photo> getPhotosByAlbumId(int album_id) {
		// TODO Auto-generated method stub
		return photoMapper.getPhotosByAlbumId(album_id);
	}
	@Override
	public void deletePhotoByPhotoId(int photo_id) {
		// TODO Auto-generated method stub
		photoMapper.deletePhotoByPhotoId(photo_id);
	}
	@Override
	public void addPhotoToDb(Photo photo) {
		// TODO Auto-generated method stub
		photoMapper.addPhotoToDb(photo);
	}
	@Override
	public Photo selectPhotoByPhotoId(int photo_id) {
		// TODO Auto-generated method stub
		return photoMapper.selectPhotoByPhotoId(photo_id);
	}

}

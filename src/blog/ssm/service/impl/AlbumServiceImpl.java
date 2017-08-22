package blog.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.ssm.domain.Album;
import blog.ssm.mapper.AlbumMapper;
import blog.ssm.service.inter.AlbumService;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService {

	@Resource
	private AlbumMapper albumMapper;
	@Override
	public List<Album> getAlbumByOwnerId(int ownerid) {
		// TODO Auto-generated method stub
		return albumMapper.getAlbumByOwnerId(ownerid);
	}
	@Override
	public void BuildAlbum(Album album) {
		// TODO Auto-generated method stub
		albumMapper.BuildAlbum(album);
	}
	@Override
	public Album selectAlbumByAlbumId(int id) {
		// TODO Auto-generated method stub
		return albumMapper.selectAlbumByAlbumId(id);
	}
	@Override
	public void deleteAlbumByAlbumId(int id) {
		// TODO Auto-generated method stub
		albumMapper.deleteAlbumByAlbumId(id);
	}
	@Override
	public void SetFrontCover(int album_id, String url) {
		// TODO Auto-generated method stub
		albumMapper.SetFrontCover(album_id, url);
	}
	@Override
	public List<Album> getLastThreeAlbum(int owner_id) {
		// TODO Auto-generated method stub
		return albumMapper.getLastThreeAlbum(owner_id);
	}
	@Override
	public List<Album> getAllOpenAlbumByOwnerId(int ownerid) {
		// TODO Auto-generated method stub
		return albumMapper.getAllOpenAlbumByOwnerId(ownerid);
	}
}

package blog.ssm.mapper;

import java.util.List;

import blog.ssm.domain.Album;

public interface AlbumMapper {

	public List<Album> getAlbumByOwnerId(int id);
	public void BuildAlbum(Album album);
	public Album selectAlbumByAlbumId(int id);
	public void deleteAlbumByAlbumId(int id);
	public void SetFrontCover(int album_id,String url);
	public List<Album> getLastThreeAlbum(int owner_id);
	public List<Album> getAllOpenAlbumByOwnerId(int ownerid);
}

package blog.ssm.service.inter;

import java.util.List;

import blog.ssm.domain.Album;

public interface AlbumService {

	//根据所有者id查找相册(含相册图片)
	public List<Album> getAlbumByOwnerId(int ownerid);
	//新建相册，将相册信息写入数据库
	public void BuildAlbum(Album album);
	//根据相册id查找相册信息
	public Album selectAlbumByAlbumId(int id);
	//根据相册id删除相册
	public void deleteAlbumByAlbumId(int id);
	//将用户新上传的图片url存到相应相册的封面url字段
	public void SetFrontCover(int album_id,String url);
	//取出3个非私密相册供主页展示用
	public List<Album> getLastThreeAlbum(int owner_id);
	//根据所有者id查找所有非私密相册(访客用)
	public List<Album> getAllOpenAlbumByOwnerId(int ownerid);
}

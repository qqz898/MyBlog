package blog.ssm.service.inter;

import java.util.List;

import blog.ssm.domain.Album;

public interface AlbumService {

	//����������id�������(�����ͼƬ)
	public List<Album> getAlbumByOwnerId(int ownerid);
	//�½���ᣬ�������Ϣд�����ݿ�
	public void BuildAlbum(Album album);
	//�������id���������Ϣ
	public Album selectAlbumByAlbumId(int id);
	//�������idɾ�����
	public void deleteAlbumByAlbumId(int id);
	//���û����ϴ���ͼƬurl�浽��Ӧ���ķ���url�ֶ�
	public void SetFrontCover(int album_id,String url);
	//ȡ��3����˽����ṩ��ҳչʾ��
	public List<Album> getLastThreeAlbum(int owner_id);
	//����������id�������з�˽�����(�ÿ���)
	public List<Album> getAllOpenAlbumByOwnerId(int ownerid);
}

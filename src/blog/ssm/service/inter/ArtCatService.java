package blog.ssm.service.inter;

import java.util.List;

import blog.ssm.domain.ArtCat;

public interface ArtCatService {

	//����һ�����ͷ���
	public void BuildArtCat(ArtCat artCat);
	//�����û�id�����������в��ͷ���
	public List<ArtCat> selectArtCatListByUserId(int id);
	//���ݲ��ͷ����idɾ����Ӧ�ķ���
	public void DeleteArtCatByCatId(int userid,int catid);
	//���ݲ��ͷ����id���Ҹ÷������
	public ArtCat getAnArtCatByCatId(int userid,int catid); 
}

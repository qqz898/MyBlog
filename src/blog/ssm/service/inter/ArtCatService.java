package blog.ssm.service.inter;

import java.util.List;

import blog.ssm.domain.ArtCat;

public interface ArtCatService {

	//简历一个博客分类
	public void BuildArtCat(ArtCat artCat);
	//根据用户id查找他的所有博客分类
	public List<ArtCat> selectArtCatListByUserId(int id);
	//根据博客分类的id删除相应的分类
	public void DeleteArtCatByCatId(int userid,int catid);
	//根据博客分类的id查找该分类对象
	public ArtCat getAnArtCatByCatId(int userid,int catid); 
}

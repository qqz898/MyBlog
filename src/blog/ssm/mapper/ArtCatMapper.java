package blog.ssm.mapper;

import java.util.List;

import blog.ssm.domain.Album;
import blog.ssm.domain.ArtCat;
import blog.ssm.domain.Blog;

public interface ArtCatMapper {

	public void BuildArtCat(ArtCat artCat);
	public List<ArtCat> selectArtCatListByUserId(int id);
	public void DeleteArtCatByCatId(int userid, int catid);
	public ArtCat getAnArtCatByCatId(int userid, int catid);
}

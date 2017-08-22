package blog.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.ssm.domain.ArtCat;
import blog.ssm.mapper.AlbumMapper;
import blog.ssm.mapper.ArtCatMapper;
import blog.ssm.service.inter.ArtCatService;
@Service("artcatService")
public class ArtCatServiceImpl implements ArtCatService {

	@Resource
	private ArtCatMapper artcatMapper;
	@Override
	public void BuildArtCat(ArtCat artCat) {
		// TODO Auto-generated method stub
		artcatMapper.BuildArtCat(artCat);
	}
	@Override
	public List<ArtCat> selectArtCatListByUserId(int id) {
		// TODO Auto-generated method stub
		return artcatMapper.selectArtCatListByUserId(id);
	}
	@Override
	public void DeleteArtCatByCatId(int userid, int catid) {
		// TODO Auto-generated method stub
		artcatMapper.DeleteArtCatByCatId(userid, catid);
	}
	@Override
	public ArtCat getAnArtCatByCatId(int userid, int catid) {
		// TODO Auto-generated method stub
		return artcatMapper.getAnArtCatByCatId(userid, catid);
	}

}

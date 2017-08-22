package blog.ssm.Util;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * 文件类空间的demo
 */
public class DeleteAlbumPhoto {

	// 运行前先设置好以下三个参数
	private static final String BUCKET_NAME = "myblogphotos";
	private static final String OPERATOR_NAME = "qqz898";
	private static final String OPERATOR_PWD = "QQZ4444DDDD";

	

	private static UpYun upyun = null;
	public void deletePhotoInUpYun(String filename,String userid) throws Exception {

		
		// 初始化空间
		upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);
		// 设置是否开启debug模式，默认不开启
		upyun.setDebug(true);
		// upyun空间下存在的文件的路径
		String filePath = "/myblog/user/"+ userid + "/userphotos/" + filename;

		// 删除文件
		boolean result = upyun.deleteFile(filePath);		

	}

	
}

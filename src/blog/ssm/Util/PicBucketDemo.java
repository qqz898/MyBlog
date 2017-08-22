package blog.ssm.Util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/**
 * 图片类空间的demo，一般性操作参考文件空间的demo（FileBucketDemo.java）
 * <p>
 * 注意：直接使用部分图片处理功能后，将会丢弃原图保存处理后的图片
 */
public class PicBucketDemo {

	// 运行前先设置好以下三个参数
	private static final String BUCKET_NAME = "myblogphotos";
	private static final String OPERATOR_NAME = "qqz898";
	private static final String OPERATOR_PWD = "QQZ4444DDDD";

	/** 绑定的域名 */
	private static final String URL = "http://" + BUCKET_NAME
			+ ".b0.upaiyun.com";

	/** 根目录 */
	private static final String DIR_ROOT = "/myblog/user/usericon";

	/** 上传到upyun的图片名 */
	private static final String PIC_NAME = "sample.jpeg";

	/** 本地待上传的测试文件 */
	private static final String SAMPLE_PIC_FILE = System
			.getProperty("user.dir") + "/sample.jpeg";

	private static UpYun upyun = null;

	static {
		File picFile = new File(SAMPLE_PIC_FILE);

		if (!picFile.isFile()) {
			System.out.println("本地待上传的测试文件不存在!");
		}
	}

	public static void main(String[] args) throws Exception {

		// 初始化空间
		upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);		
		upyun.setDebug(false);
		// 1.上传文件（文件内容）
		testWriteFile();

	}

	/**
	 * 上传文件
	 * 
	 * @throws IOException
	 */
	public static void testWriteFile() throws IOException {

		// 要传到upyun后的文件路径
		String filePath = DIR_ROOT + PIC_NAME;

		// 本地待上传的图片文件
		File file = new File(SAMPLE_PIC_FILE);

		// 设置待上传文件的 Content-MD5 值
		// 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
		upyun.setContentMD5(UpYun.md5(file));

		upyun.setFileSecret("bac");
		// 设置待上传文件的"访问密钥"
		// 注意：
		// 仅支持图片空！，设置密钥后，无法根据原文件URL直接访问，需带URL后面加上（缩略图间隔标志符+密钥）进行访问
		// 举例：
		// 如果缩略图间隔标志符为"!"，密钥为"bac"，上传文件路径为"/folder/test.jpg"，
		// 那么该图片的对外访问地址为：http://空间域名 /folder/test.jpg!bac
		// 代码示例：upyun.setFileSecret("bac");

		// 上传文件，并自动创建父级目录（最多10级）
		boolean result = upyun.writeFile(filePath, file, true);

		System.out.println(filePath + " 上传" + isSuccess(result));

		// 获取上传文件后的信息（仅图片空间有返回数据）
		System.out.println("\r\n****** " + file.getName() + " 的图片信息 *******");
		// 图片宽度
		System.out.println("图片宽度:" + upyun.getPicWidth());
		// 图片高度
		System.out.println("图片高度:" + upyun.getPicHeight());
		// 图片帧数
		System.out.println("图片帧数:" + upyun.getPicFrames());
		// 图片类型
		System.out.println("图片类型:" + upyun.getPicType());

		System.out.println("****************************************\r\n");

		System.out.println("若设置过访问密钥(bac)，且缩略图间隔标志符为'!'，则可以通过以下路径来访问图片：");
		System.out.println(URL + filePath + "!bac");
		System.out.println();
	}

	private static String isSuccess(boolean result) {
		return result ? " 成功" : " 失败";
	}
}

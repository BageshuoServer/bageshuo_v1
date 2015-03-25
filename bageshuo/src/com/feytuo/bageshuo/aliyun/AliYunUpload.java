package com.feytuo.bageshuo.aliyun;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.fileupload.FileItem;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.feytuo.bageshuo.util.Constants;


public class AliYunUpload {

	private OSSClient client;
	public AliYunUpload() {
		// TODO Auto-generated constructor stub
		client = new OSSClient(Constants.endpoint, Constants.accessKeyId, Constants.accessKeySecret);
	}
	
  /**
   * 创建一个bucket
   * @param client
   * @param bucketName
   */
 public void createBucket() {

		// 获取Bucket的存在信息
		boolean exists = client.doesBucketExist(Constants.bucket);
			
		// 输出结果
		if (exists) {
		    System.out.println("Bucket exists");
		} else {
			  // 新建一个Bucket
		    client.createBucket(Constants.bucket);
		    System.out.println("Bucket not exists");
		}
	  
	}
	
	/**
	 * 上传一个文件
	 * @param client
	 * @param bucketName
	 * @param key
	 * @param filePath
	 * @throws IOException 
	 */
	public String putObject(FileItem fileItem,String savePath) throws IOException {

	    // 获取指定文件的输入流
	    InputStream content = fileItem.getInputStream();

	    // 创建上传Object的Metadata
	    ObjectMetadata meta = new ObjectMetadata();

	    // 必须设置ContentLength
	    meta.setContentLength(fileItem.getSize());
	    meta.setContentType("image/jpeg");
	    int index = fileItem.getName().lastIndexOf(".");
	    String keyFooter = fileItem.getName().substring(index);
	    System.out.println(keyFooter);
	    String key = savePath+"/"+System.currentTimeMillis()+keyFooter;
	    // 上传Object.
	    PutObjectResult result = client.putObject(Constants.bucket, key, content, meta);
    
	    System.out.println("http://"+Constants.bucket+".oss-cn-qingdao.aliyuncs.com/"+key);
	    // 打印ETag
	    System.out.println(result.getETag());
	    
	    return "http://"+Constants.bucket+".oss-cn-qingdao.aliyuncs.com/"+key;
	}
}

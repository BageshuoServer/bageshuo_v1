package com.feytuo.bageshuo.aliyun;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.fileupload.FileItem;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;


public class AliYunUpload {

	
  /**
   * 创建一个bucket
   * @param client
   * @param bucketName
   */
 public void createBucket(OSSClient client,String bucketName) {

		// 获取Bucket的存在信息
		boolean exists = client.doesBucketExist(bucketName);
			
		// 输出结果
		if (exists) {
		    System.out.println("Bucket exists");
		} else {
			  // 新建一个Bucket
		    client.createBucket(bucketName);
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
	public String putObject(OSSClient client,String bucketName, FileItem fileItem) throws IOException {

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
	    String key = "userHead/"+System.currentTimeMillis()+keyFooter;
	    // 上传Object.
	    PutObjectResult result = client.putObject(bucketName, key, content, meta);
    
	    System.out.println("http://"+bucketName+".oss-cn-qingdao.aliyuncs.com/"+key);
	    // 打印ETag
	    System.out.println(result.getETag());
	    
	    return "http://"+bucketName+".oss-cn-qingdao.aliyuncs.com/"+key;
	}
}

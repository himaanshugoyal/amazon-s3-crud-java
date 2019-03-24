package s3;

import java.io.File;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import s3.ConnectToS3;
public class UploadFile {

	public static void main(String[] args) {
		
		final AmazonS3 s3client = ConnectToS3.connectToS3();
		
		String bucket_name = "bucketName";
		String key_name = "uploadDocTest.txt";
		String filePath = "/Users/himanshugoyal/Untitled.sql";
		
		try{
			s3client.putObject(
					bucket_name, 
					key_name, 
					  new File(filePath)
					);
		} catch (AmazonS3Exception e){
			System.err.println(e.getErrorMessage());
		}
	}

}

package s3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import s3.ConnectToS3;
public class Read {

	public static void main(String[] args) {
		
		final AmazonS3 s3client = ConnectToS3.connectToS3();
		
		String bucket_name = "bucketName";
		String key_name = "uploadDocTest.txt";
		
		S3Object s3object = s3client.getObject(new GetObjectRequest(bucket_name, key_name));
		InputStream objectData = s3object.getObjectContent();
		
		try{
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
			String line;
			while((line = reader.readLine()) != null){
				System.out.println(line);
			}
			//FileUtils.copyInputStreamToFile(inputStream, new File("/Users/user/Desktop/hello.txt"));
			objectData.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}

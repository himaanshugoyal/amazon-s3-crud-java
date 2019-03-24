package s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import s3.ConnectToS3;
public class CreateBucket {

	public static void main(String[] args) {
		
		final AmazonS3 s3client = ConnectToS3.connectToS3();
		
		String bucket_name = "himan16";
		
		try{
			if(s3client.doesBucketExist(bucket_name)) {
			    System.out.println("Bucket name is not available."
			      + " Try again with a different Bucket name.");
			    return;
			}
			 
			s3client.createBucket(bucket_name);
		} catch (AmazonS3Exception e){
			System.err.println(e.getErrorMessage());
		}
	}

}

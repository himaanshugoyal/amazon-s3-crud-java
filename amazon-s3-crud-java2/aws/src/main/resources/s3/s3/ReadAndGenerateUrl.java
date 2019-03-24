package s3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import s3.ConnectToS3;
public class ReadAndGenerateUrl {

	public static void main(String[] args) {
		
		final AmazonS3 s3client = ConnectToS3.connectToS3();
		
		String bucket_name = "bucketName";
		String key_name = "uploadDocTest.txt";
		
		S3Object s3object = s3client.getObject(new GetObjectRequest(bucket_name, key_name));
		InputStream objectData = s3object.getObjectContent();
		
        // Set the presigned URL to expire after one hour.
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);


		try{
			
			// Generate the presigned URL.
            System.out.println("Generating pre-signed URL.");
            GeneratePresignedUrlRequest generatePresignedUrlRequest = 
                    new GeneratePresignedUrlRequest(bucket_name, key_name)
                    .withMethod(HttpMethod.GET)
                    .withExpiration(expiration);
            URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);
    
            System.out.println("Pre-Signed URL: " + url.toString());
		} catch (AmazonServiceException e){
			e.printStackTrace();
		}
	}

}

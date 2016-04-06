package com.cognizant.poc.service.store.impl;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.cognizant.poc.service.store.GenericStore;

public class S3Store implements GenericStore {

	private String awsAccessKey;

	private String awsSecretKey;

	private String awsBucketName;

	private String awsFolderName;

	private String awsLocalPath;

	public String getAwsAccessKey() {
		return awsAccessKey;
	}

	public void setAwsAccessKey(String awsAccessKey) {
		this.awsAccessKey = awsAccessKey;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}

	public String getAwsBucketName() {
		return awsBucketName;
	}

	public void setAwsBucketName(String awsBucketName) {
		this.awsBucketName = awsBucketName;
	}

	public String getAwsFolderName() {
		return awsFolderName;
	}

	public void setAwsFolderName(String awsFolderName) {
		this.awsFolderName = awsFolderName;
	}

	public String getAwsLocalPath() {
		return awsLocalPath;
	}

	public void setAwsLocalPath(String awsLocalPath) {
		this.awsLocalPath = awsLocalPath;
	}

	public boolean store(MultipartFile objToStore) {
		boolean success = false;
		AWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);

		TransferManager tm = new TransferManager(credentials);

		// Setting EndPoint
		AmazonS3 amazonS3Obj = tm.getAmazonS3Client();

		// Region regObj = Region.getRegion(Regions.AP_SOUTHEAST_1);
		// amazonS3Obj.setRegion(regObj);
		amazonS3Obj.setEndpoint("s3.amazonaws.com");

		/*
		 * String bucketName = awsDetails.get("awsBucketName"); String
		 * folderName = awsDetails.get("awsFolderName");
		 */
		String fileName = awsFolderName + "/" + objToStore.getOriginalFilename();
		// TransferManager processes all transfers asynchronously,
		// so this call will return immediately.
		com.amazonaws.services.s3.transfer.Upload upload = tm.upload(awsBucketName, fileName,
				new File(awsLocalPath + objToStore.getOriginalFilename()));

		try {
			upload.waitForCompletion();
			System.out.println("Upload complete.");
			success = true;

		} catch (AmazonServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AmazonClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}

}

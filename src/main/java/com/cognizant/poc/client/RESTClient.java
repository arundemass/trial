package com.cognizant.poc.client;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RESTClient {

	public void postfile() {
		RestTemplate rt = new RestTemplate();
		String url = "http://192.168.99.100:8888/objectstoreservice/poc/storeMultiPart";
	    Resource resource = new FileSystemResource(new File("D:\\Proposals\\Sophos\\Sophos_Security_Document.pdf"));
		MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
	    mvm.add("file", resource);
	    ResponseEntity<String> respEnt = rt.postForEntity(url, mvm, String.class);		
	    System.out.println(respEnt.getBody());
	}
	
	public static void main(String[] args) {
		RESTClient client = new RESTClient();
		client.postfile();
	}
}

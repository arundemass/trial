package com.cognizant.poc.service.store;

import org.springframework.web.multipart.MultipartFile;

public interface GenericStore {
	
	public boolean store( MultipartFile objToStore);
	
}

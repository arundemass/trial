package com.cognizant.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.cognizant.poc.service.store.GenericStore;

public class ObjectStoreService {

	@Autowired
	private GenericStore objectStore;
	
	public String store(MultipartFile objToStore) {
		return String.valueOf(objectStore.store(objToStore));		
	}
}

package com.cognizant.poc.service.store.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.cognizant.poc.service.store.GenericStore;

public class FileStore implements GenericStore {

	private String destinationFolder;

	public String getDestinationFolder() {
		return destinationFolder;
	}

	public void setDestinationFolder(String destinationFolder) {
		this.destinationFolder = destinationFolder;
	}

	public boolean store(MultipartFile objToStore) {
		boolean success = false;
		File newFile = new File(destinationFolder + File.separator + objToStore.getOriginalFilename());
		if (!newFile.exists())
			newFile.mkdirs();
		try {
			objToStore.transferTo(newFile);
			success = true;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;

	}

}

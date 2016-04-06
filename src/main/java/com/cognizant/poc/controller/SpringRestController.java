package com.cognizant.poc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cognizant.poc.service.ObjectStoreService;

@RestController
@RequestMapping("/poc")
public class SpringRestController {
	
	@Autowired
	ObjectStoreService objectStoreService;
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String hello(@PathVariable String name) {
		String result = "Hello " + name;
		return result;
	}

	@RequestMapping(value = "/storeMultiPart", method = RequestMethod.POST)
	public String storeMultiPart(@RequestParam MultipartFile file, HttpServletRequest request) throws Exception {
		return objectStoreService.store(file);

	}
	
}

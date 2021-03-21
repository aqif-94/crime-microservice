package com.app.crime.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CrimeInfoController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static String url = "https://data.police.uk/api/crime-categories";
	
	@GetMapping("/crime/categories")
	public List<Object> getCategories(){
		Object[] categories = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(categories);
		
	}
}

package com.app.crime.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
		
		if(categories.toString().isEmpty()) {
			throw new CrimeNotFoundException("No data found");
		}
		
		return Arrays.asList(categories);
	}
	
	//Adding exception handler
	@ExceptionHandler
	public ResponseEntity<CrimeErrorResponse> handleException(CrimeNotFoundException exception){
		CrimeErrorResponse error = new CrimeErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}

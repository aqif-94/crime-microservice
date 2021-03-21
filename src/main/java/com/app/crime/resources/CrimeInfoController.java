package com.app.crime.resources;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.crime.model.Postcode;
import com.app.crime.model.PostodeInfo;

@RestController
public class CrimeInfoController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static String urlCrimeCategories = "https://data.police.uk/api/crime-categories";
	
	@GetMapping("/crime/categories")
	public List<Object> getCategories(){
		Object[] categories = restTemplate.getForObject(urlCrimeCategories, Object[].class);
		
		if(categories.toString().isEmpty()) {
			throw new DataNotFoundException("No data found");
		}
		
		return Arrays.asList(categories);
	}
	
	@GetMapping("crimes/postcode={inputPostcode}&date={inputDate}")
	public Object getCrimesAtLocation(@PathVariable("inputPostcode") String inputPostcode, @PathVariable("inputDate") String inputDate) {
	
		if (!isDateValid(inputDate)) {
			throw new InvalidDataException("Invalid date");
		}
		
		BigDecimal latitude;
		BigDecimal longitude;

		if (isPostcodeValid(inputPostcode)) {
			PostodeInfo postcodeInfo = restTemplate.getForObject("https://api.postcodes.io/postcodes/" + inputPostcode,
					PostodeInfo.class);
			

			latitude = postcodeInfo.getResult().getLatitude();
			longitude = postcodeInfo.getResult().getLongitude();
		} else {
			throw new InvalidDataException("Invalid postcode");
		}
		
		String urlCrimeLocation = "https://data.police.uk/api/crimes-at-location?date=" + inputDate + "&lat=" + latitude + "&lng="  + longitude;
		
		Object locationCrimes = restTemplate.getForObject(urlCrimeLocation, Object.class);
		
		if(locationCrimes.toString().length() == 2) { // where the response returns [] empty, .isEmpty() & .isBlank() not working
			throw new DataNotFoundException("No data found");
		}
		return locationCrimes;
		
		
	}
	
	public boolean isPostcodeValid(String inputPostcode) {
		Postcode postcodeObject = restTemplate.getForObject("https://api.postcodes.io/postcodes/"+ inputPostcode +"/validate",Postcode.class);
		if (!postcodeObject.getResult()) {
			return false;
		}	
		return true;
	}
	
	public boolean isDateValid(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM");
		
		try {
			formatter.parse(date);
			return true;
		}
		catch (Exception e) {
			return false;
		}
		
	}
	
	//Adding exception handlers
	
	@ExceptionHandler
	public ResponseEntity<CrimeErrorResponse> handleDataException(DataNotFoundException exception){
		CrimeErrorResponse error = new CrimeErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<CrimeErrorResponse> handleInvalidException(InvalidDataException exception){
		CrimeErrorResponse error = new CrimeErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}

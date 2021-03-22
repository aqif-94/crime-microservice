package com.app.crime.resources;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest()
class CrimeInfoControllerIT {
	
	@Autowired
	private MockMvc mvc;

	@Test
	void getCategories_PerformSuccessfulGetRequestAndDataFound_CategoryDataReturned() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/crime/categories");
		MvcResult result = mvc.perform(request).andReturn();
		assertTrue(!result.getResponse().getContentAsString().isEmpty());
	}
	
	@Test
	void getCrimesAtLocation_PerformSuccessfulGetRequestAndDataFound_CategoryDataReturned() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/crimes/postcode=B461JT&date=2018-05");
		MvcResult result = mvc.perform(request).andReturn();
		assertTrue(!result.getResponse().getContentAsString().isEmpty());
	}
}

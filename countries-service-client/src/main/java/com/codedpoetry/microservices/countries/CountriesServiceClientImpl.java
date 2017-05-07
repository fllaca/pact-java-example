package com.codedpoetry.microservices.countries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class CountriesServiceClientImpl implements CountriesServiceClient {

	private RestTemplate restTemplate = new RestTemplate();
	
	private String countriesServiceBaseUrl;
	
	public CountriesServiceClientImpl(String countriesServiceBaseUrl) {
		super();
		this.countriesServiceBaseUrl = countriesServiceBaseUrl;
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>(1);
		converters.add(new MappingJackson2HttpMessageConverter());
		
		//restTemplate.setMessageConverters(converters);
	}

	private static final String GET_COUNTRIES_ENDPOINT = "/api/1/country/";
	
	public Country getCountry(String countryCode) {
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		
		
		String url = countriesServiceBaseUrl + GET_COUNTRIES_ENDPOINT + countryCode;
		//ResponseEntity<Country> response = restTemplate.getForEntity(url, Country.class, countryCode);
		ResponseEntity<Country> response = restTemplate.exchange(url, HttpMethod.GET, entity, Country.class);
		
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			return response.getBody();
		} else if (HttpStatus.NOT_FOUND.equals(response.getStatusCode())){
			return null;
		} else {
			throw new RuntimeException("Unexpected error");
		}
	}

}

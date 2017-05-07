package com.codedpoetry.microservices.countries;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;

import au.com.dius.pact.consumer.ConsumerPactTest;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;

public class CountriesServiceClientImplTest extends ConsumerPactTest {
	
	@Override
    protected PactFragment createFragment(PactDslWithProvider builder) {

        Map<String, String> responseHeaders = new HashMap<String, String>();
        responseHeaders.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        
        Map<String, String> requestHeaders = new HashMap<String, String>();
        requestHeaders.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
        
        
		return builder
            .given("Found") 
            .uponReceiving("Country exists")
                .path("/api/1/country/ESP")
                .method("GET")
                .headers(requestHeaders)
            .willRespondWith()
                .status(200)
                .headers(responseHeaders )
                .body("{\"code\": \"ESP\", \"name\": \"Spain\"}")
            /*.given("Not Found") 
            .uponReceiving("Country DOES NOT exists")
	            .path("/api/1/country/UNKNOWN")
	            .method("GET")
	            .headers(requestHeaders)
	        .willRespondWith()
	            .status(404)*/
            .toFragment();
    }


    @Override
    protected String providerName() {
        return "test_provider";
    }

    @Override
    protected String consumerName() {
        return "test_consumer";
    }

	@Override
	protected void runTest(String url) throws IOException {
		
		CountriesServiceClientImpl countriesServiceClientImpl = new CountriesServiceClientImpl(url);
		
        String countryCode = "ESP";
        String countryName = "Spain";
		
        Country country = countriesServiceClientImpl.getCountry(countryCode);
		assertEquals(countryCode, country.getCode());
		assertEquals(countryName, country.getName());
	}

}

package com.codedpoetry.microservices.countries;

/**
 * Hello world!
 *
 */
public interface CountriesServiceClient {
	
	public Country getCountry(String countryCode);
}

package com.techelevator.tenmo.services;

import org.springframework.web.client.RestTemplate;

public class AccountService {

	private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
	
	public AccountService(String url) {
        this.BASE_URL = url;
    }
}

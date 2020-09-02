package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Transfer;

public class TransferServices {

	private String BASE_URL;
	private RestTemplate restTemplate = new RestTemplate();
	private String token;
	
	
	public TransferServices(String url) {
		this.BASE_URL = url + "account/transfers";
	}
	
	public Transfer createTransfer(String token, Transfer transfer) {
//    	HttpHeaders headers = new HttpHeaders();

//		HttpEntity<Transfer> entity = new HttpEntity<>(transfer);
		ResponseEntity<Transfer> responseEntity = restTemplate.exchange(BASE_URL + "transfer",  HttpMethod.POST, makeAuthEntity(), Transfer.class);
		return responseEntity.getBody();
		

	}
	
	
	/**
	 * Returns an {HttpEntity} with the `Authorization: Bearer:` header
	 * 
	 * @return {HttpEntity}
	 */
	private HttpEntity makeAuthEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(this.token);
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}

	public void setToken(String token) {
		this.token = token;
	}
}




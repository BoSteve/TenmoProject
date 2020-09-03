package com.techelevator.tenmo.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Transfer;

public class TransferServices {

	private String BASE_URL;
	private RestTemplate restTemplate = new RestTemplate();
	private String token;

	public TransferServices(String url) {
		this.BASE_URL = url;
	}

//	====NOT NEEDED RIGHT NOW===========

//	public Transfer createTransfer(String token, Transfer transfer) {
////    	HttpHeaders headers = new HttpHeaders();
//
////		HttpEntity<Transfer> entity = new HttpEntity<>(transfer);
//		ResponseEntity<Transfer> responseEntity = restTemplate.exchange(BASE_URL + "transfer",  HttpMethod.POST, makeAuthEntity(), Transfer.class);
//		return responseEntity.getBody();
//		
//
//	}

	public Transfer getTransferById(Long transferId) {
		Transfer transfer = restTemplate
				.exchange(BASE_URL + "transfer/" + transferId, HttpMethod.GET, makeAuthEntity(), Transfer.class)
				.getBody();
		return transfer;

	}

	public List<Transfer> historyOfTransfers() {
		ResponseEntity<List<Transfer>> responseEntity = restTemplate.exchange(BASE_URL + "transfer", HttpMethod.GET,
				makeAuthEntity(), new ParameterizedTypeReference<List<Transfer>>() {
				});
		List<Transfer> transferHist = responseEntity.getBody();
		return transferHist;
	}

	public Transfer sendTransfer(Transfer transfer) {
//		Transfer transfer = new Transfer();
		transfer = restTemplate
				.exchange(BASE_URL + "transfer", HttpMethod.POST, makeTransferEntity(transfer), Transfer.class)
				.getBody();
		return transfer;
	}

	/**
	 * Returns an {HttpEntity} with the `Authorization: Bearer:` header
	 * 
	 * @return {HttpEntity}
	 */

	private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(this.token);
		HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
		return entity;
	}

	private HttpEntity<Object> makeAuthEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(this.token);
		HttpEntity<Object> entity = new HttpEntity<>(headers);
		return entity;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

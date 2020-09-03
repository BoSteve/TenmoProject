package com.techelevator.tenmo.services;

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
	
	public Transfer getTransferById(Long accountId, Long transferId) { 
		Transfer transfer = restTemplate.exchange(BASE_URL + "accounts/" + accountId + "/transfer/" + transferId, HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
	return transfer;
	
	}
	
// ================COME BACK TO THIS==================================
	
	
	
//	public Transfer[] historyOfTransfers(String token) {
//		Transfer[] transferHist = null;
//		transferHist = restTemplate.exchange(BASE_URL + "transfer/history/", HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
//	return transferHist;
//	}
	
	
	public Transfer sendTransfer(Transfer transfer) {
//		Transfer transfer = new Transfer();
		transfer = restTemplate.exchange(BASE_URL+"transfer", HttpMethod.POST, makeTransferEntity(transfer), Transfer.class).getBody();
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




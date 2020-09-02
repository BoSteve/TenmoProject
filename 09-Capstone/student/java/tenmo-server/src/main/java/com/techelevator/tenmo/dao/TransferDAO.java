package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	void addTransfer(Long transferTypeId, Long statusId, Long aFrom, Long aTo, BigDecimal amount);
	
	List<Transfer> transfersByAccount(Long accountId);
	
	List<Transfer> getTransfersById(Long transferId);
	
	
}

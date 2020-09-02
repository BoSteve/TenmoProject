package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.tenmo.model.Transfer;

@Service
public class TransferSqlDAO implements TransferDAO {

	private JdbcTemplate jdbcTemplate;

	public TransferSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addTransfer(Long transferTypeId, Long statusId, Long aFrom, Long aTo, BigDecimal amount) {
		// TODO Auto-generated method stub
		String addSqlTransfer = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) "
				+ "VALUES (2, 2, ?, ?, ?)";

		jdbcTemplate.update(addSqlTransfer, aFrom, aTo, amount);
	}

	@Override
	public List<Transfer> getTransfersById(Long transferId) {
		List<Transfer> transferIdList = new ArrayList<Transfer>();
		String sql = "SELECT * FROM transfers WHERE transfer_id = ?";

		SqlRowSet output = jdbcTemplate.queryForRowSet(sql, transferId);

		while (output.next()) {
			Transfer idTransfer = AddRowToTransfer(output);
			transferIdList.add(idTransfer);
		}
		return transferIdList;
	}

	@Override
	public List<Transfer> transfersByAccount(Long accountId) {

		List<Transfer> transferList = new ArrayList<Transfer>();
		String sqlTransfer = "SELECT * FROM transfers WHERE account_from = ? OR account_to = ?";

		SqlRowSet output = jdbcTemplate.queryForRowSet(sqlTransfer, accountId, accountId);

		while (output.next()) {
			Transfer newTransfer = AddRowToTransfer(output);
			transferList.add(newTransfer);

		}
		return transferList;
	}

	private Transfer AddRowToTransfer(SqlRowSet output) {
		Transfer newTransfer = new Transfer();

		newTransfer.setTransferId(output.getLong("transfer_id"));
		newTransfer.setTransferTypeId(output.getLong("transfer_type_id"));
		newTransfer.setTransferStatusId(output.getLong("transfer_status_id"));
		newTransfer.setAccountFrom(output.getLong("account_from"));
		newTransfer.setAccountTo(output.getLong("account_to"));
		newTransfer.setAmount(output.getBigDecimal("amount"));
		return newTransfer;
	}

}

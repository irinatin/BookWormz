package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCPrizeDAO implements PrizeDAO{

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean createNewPrize(String blingBling) {
		long prizeId = getNextPrizeId();
		String insertIntoPrize = "INSERT INTO prize VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(insertIntoPrize, prizeId, blingBling, blingBling.getPrizeDescription(), 
				milestone, userGroup, numOfPrizes, startDate, endDate);
		return true;
	}

	@Override
	public Prize editExistingPrize(Prize blingBlung) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePrize(long prizeId) {
		String deleteFromPrize = "DELETE FROM prize WHERE prizeId = ?";
		jdbcTemplate.update(deleteFromPrize, prizeId.getPrizeId());
	
		return true;
	}

	@Override
	public List<Prize> getAllPrizes() {
		List<Prize> allPrizes = new ArrayList<Prize>();
		String getAllPrizes = "SELECT *"
				+			  "FROM prize";
		Prize blingBling = new Prize();
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllPrizes);
		
		while(results.next()) {
			blingBling = mapRowToPrize(results);
			allPrizes.add(blingBling);
		}
		
		return null;
	}
	
	public long getNextPrizeId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet
				("SELECT nextval('prize_prize_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1);
		}
		else {
			throw new RuntimeException("Something went wrong with prize sequence");
		}
	}
	
	private Prize mapRowToPrize(SqlRowSet results) {
		Prize blingBling = new Prize();
	}
}

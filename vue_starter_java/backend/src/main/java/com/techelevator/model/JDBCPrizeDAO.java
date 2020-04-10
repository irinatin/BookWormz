package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCPrizeDAO implements PrizeDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCPrizeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean createNewPrize(Prize blingBling) {
		long prizeId = getNextPrizeId();
		String insertIntoPrize = "INSERT INTO prize VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(insertIntoPrize, prizeId, blingBling.getPrizeName(), blingBling.getPrizeDescription(),
				blingBling.getMilestone(), blingBling.getUserGroup(), blingBling.getNumOfPrizes(),
				blingBling.getStartDate(), blingBling.getEndDate());
		return true;
	}

	@Override
	public Prize editExistingPrize(Prize blingBlung) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePrize(Prize blingBling) {
		String deleteFromPrize = "DELETE FROM prize WHERE prizeId = ?";
		jdbcTemplate.update(deleteFromPrize, blingBling.getPrizeId());

		return true;
	}

	@Override
	public List<Prize> getAllPrizes() {
		List<Prize> allPrizes = new ArrayList<Prize>();
		String getAllPrizes = "SELECT *" + "FROM prize";
		Prize blingBling = new Prize();
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllPrizes);

		while (results.next()) {
			blingBling = mapRowToPrize(results);
			allPrizes.add(blingBling);
		}

		return null;
	}

	public long getNextPrizeId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('prize_prize_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong with prize sequence");
		}
	}

	// Date, LocalDate?? See Prize.java

	private Prize mapRowToPrize(SqlRowSet results) {
		Prize blingBling = new Prize();
		blingBling.setPrizeId(results.getLong("prize_id"));
		blingBling.setPrizeName(results.getString("prize_name"));
		blingBling.setPrizeDescription(results.getString("prize_description"));
		blingBling.setMilestone(results.getInt("milestone"));
		blingBling.setUserGroup(results.getString("user_group"));
		blingBling.setNumOfPrizes(results.getInt("max_prizes"));
		blingBling.setStartDate(results.getDate("start_date"));
		blingBling.setEndDate(results.getDate("end_date"));

		return blingBling;

	}
}

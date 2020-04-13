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
		if(blingBling.getUserGroup().toLowerCase().equals("parent")) {
			blingBling.setUserGroup("user");
		}
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
	public boolean deletePrize(long id) {
		String deleteFromPrize = "DELETE FROM prize WHERE prizeId = ?";
		jdbcTemplate.update(deleteFromPrize, id);

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
	
	public List<PrizeListInfo> getPrizeListInfo(String role, Long userId) {
		List<PrizeListInfo> dummyPrizeInfo = new ArrayList<PrizeListInfo>();
		String getAllPrizes = "SELECT prize.prize_name, prize.milestone, prize.user_group FROM prize WHERE prize.user_group = ?";
		SqlRowSet nextPrizeResult = jdbcTemplate.queryForRowSet(getAllPrizes, role);
		String readingTime = "SELECT SUM(user_book.reading_time) AS totaltime FROM user_book WHERE user_id = ?";
		SqlRowSet readingTimeResult = jdbcTemplate.queryForRowSet(readingTime, userId);
		while(nextPrizeResult.next()) {
			PrizeListInfo prize = new PrizeListInfo();
			prize.setPrizeName(nextPrizeResult.getString("prize_name"));
			prize.setMilestone(nextPrizeResult.getInt("milestone"));
			prize.setUserGroup(nextPrizeResult.getString("user_group"));
			dummyPrizeInfo.add(prize);
		}
		int i = 0;
		while(readingTimeResult.next()) {
			int totalReadingTime = readingTimeResult.getInt("totaltime");
			dummyPrizeInfo.get(i).setReadingTime(totalReadingTime);
			i++;
		}
		return dummyPrizeInfo;
	}

	@Override
	public List<String> getPrizesPerUser(Long userId) {
		List<String> prizesPerUser = new ArrayList<String>();
		String getAllPrizesPerUser = "SELECT prize.prize_name FROM prize JOIN user_prize ON prize.prize_id = user_prize.prize_id WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllPrizesPerUser, userId);
		while(results.next()) {
			prizesPerUser.add(results.getString(1));
		}
		return prizesPerUser;
	}
}

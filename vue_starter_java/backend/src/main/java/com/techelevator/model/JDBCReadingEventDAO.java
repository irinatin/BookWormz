package com.techelevator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCReadingEventDAO implements ReadingEventDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCReadingEventDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<ReadingEvent> getAllReadingEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadingEvent addReadingEvent(ReadingEvent reads) {
		reads.setReadingEventId(getReadingEventId());
		String sqlSaveReadingEvent = "INSERT INTO user_book VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlSaveReadingEvent, reads.getReadingEventId(), reads.getUserId(), reads.getBookId(),
				reads.getReadingTime(), LocalDate.parse(reads.getReadingDate()), reads.getFormat(),
				reads.isCompleted());
		return reads;
	}

	@Override
	public ReadingEvent addChildReadingEvent(ReadingEvent reads, boolean completed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadingActivity getReadingActivity(Long userId, String userRole) {
		
		ReadingActivity familyReadingActivity = new ReadingActivity();

		String sqlTotalBooksCompleted = "SELECT COUNT(*) FROM user_book WHERE completed = true AND user_id = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTotalBooksCompleted, userId);
		
		results.next();
		familyReadingActivity.setCompletedBooks(results.getInt(1));
		
		String sqlSumTotalMinutes = "SELECT SUM(reading_time) FROM user_book WHERE user_id = ?";
		
		SqlRowSet resultsMins = jdbcTemplate.queryForRowSet(sqlSumTotalMinutes, userId);
		resultsMins.next();
		familyReadingActivity.setTotalReadingTime(resultsMins.getInt(1));
		
		
		String sqlCurrentBooks = "SELECT book.title, user_book.completed FROM user_book JOIN book ON book.book_id = user_book.book_id WHERE user_book.user_id = ?";
		
		SqlRowSet resultsCurrentBooks = jdbcTemplate.queryForRowSet(sqlCurrentBooks, userId);
		Map<String, Boolean> currentBooks = new HashMap<String, Boolean>();
		List<String> currentBooksTitles = new ArrayList<String>();
		
		while(resultsCurrentBooks.next()) {
			currentBooks.put(resultsCurrentBooks.getString(1), resultsCurrentBooks.getBoolean(2));
		}
		
		Set<String> keys = currentBooks.keySet();
		for(String key : keys) {
			if(currentBooks.get(key) == false) {
				currentBooksTitles.add(key);
			}
		}
		
		familyReadingActivity.setCurrentBooks(currentBooksTitles);
		
		String sqlProgressTowardsPrize = "SELECT prize_name, milestone FROM prize WHERE user_group = ? AND start_date < ? AND end_date > ?";
		
		SqlRowSet resultsPrizes = jdbcTemplate.queryForRowSet(sqlProgressTowardsPrize, userRole, LocalDate.now(), LocalDate.now());
		
		Map<String, Integer> progress = new HashMap<String, Integer>();
		
		
		
		while(resultsPrizes.next()) {
			double resultsMinutes = (Double.valueOf(resultsMins.getInt(1)) / resultsPrizes.getInt(2)) * 100;
			Integer resultsPercentage = (int)(resultsMinutes);
			progress.put(resultsPrizes.getString(1), resultsPercentage);
		}
		
		familyReadingActivity.setProgressTowardsPrize(progress);

		return familyReadingActivity;
	}

	private long getReadingEventId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('user_book_reading_event_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1); // changed from int to long
		} else {
			throw new RuntimeException("Something went wrong with book sequence");
		}
	}

}

package com.techelevator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	public List<ReadingActivity> getReadingActivity(Long userId) {
		List<ReadingActivity> familyReadingActivity = new ArrayList<ReadingActivity>();

		String sqlGetBooksAndTimePeruser = "SELECT book.title, user_book.reading_time " + "FROM book "
				+ "JOIN user_book ON user_book.book_id = book.book_id " + "WHERE user_book.user_id = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetBooksAndTimePeruser, userId);

		while (results.next()) {
			ReadingActivity newReadingActivity = new ReadingActivity();
			newReadingActivity.setTitle(results.getString("title"));
			newReadingActivity.setReadingTime(results.getInt("reading_time"));
			familyReadingActivity.add(newReadingActivity);
		}

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

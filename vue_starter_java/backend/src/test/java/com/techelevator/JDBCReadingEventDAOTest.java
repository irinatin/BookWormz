package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.ReadingEvent;
import com.techelevator.model.ReadingEventDAO;

import junit.framework.Assert;

public class JDBCReadingEventDAOTest {
	
	private ReadingEventDAO readingEventDAO;
	
	private JdbcTemplate jdbcTemplate;
	private ReadingEvent myReadingEvent;
	
	@Before
	public void setUp() throws Exception {
		myReadingEvent = new ReadingEvent();
	}
	
	@Test
	public void test_get_and_set_reading_event_id() {
		long readingEventId = 5;
		myReadingEvent.setReadingEventId(readingEventId);
		assertEquals(5, myReadingEvent.getReadingEventId());
	}
	
	

}

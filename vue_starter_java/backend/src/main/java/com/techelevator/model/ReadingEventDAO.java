package com.techelevator.model;

import java.util.List;

public interface ReadingEventDAO {
	
	public List<ReadingEvent> getAllReadingEvents();
	
	public ReadingEvent addReadingEvent(ReadingEvent reads);
	
	public List<ReadingEvent> getReadingEventsByUser(long userId);
	

}

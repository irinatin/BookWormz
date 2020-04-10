package com.techelevator.model;

import java.util.List;

public interface ReadingEventDAO {
	
	public List<ReadingEvent> getAllReadingEvents();
	
	public ReadingEvent addReadingEvent(ReadingEvent reads, boolean completed);
	public ReadingEvent addChildReadingEvent(ReadingEvent reads, boolean completed);
	
	public List<ReadingEvent> getReadingEventsByUser(long userId);
	

}

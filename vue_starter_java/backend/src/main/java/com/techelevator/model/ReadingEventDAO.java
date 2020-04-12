package com.techelevator.model;

import java.util.List;

public interface ReadingEventDAO {
	
	public List<ReadingEvent> getAllReadingEvents();
	
	public ReadingEvent addReadingEvent(ReadingEvent reads);
	public ReadingEvent addChildReadingEvent(ReadingEvent reads, boolean completed);


	List<ReadingActivity> getReadingActivity(Long userId);
	

}

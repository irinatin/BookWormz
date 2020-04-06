package com.techelevator.model;

import java.time.LocalDate;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class ReadingEvent {
	
	private long readingEventId;
	
	@NotBlank(message = "User is required")
	private long userId;
	
	@NotBlank(message = "ISBN is required")
	private int isbn;
	
	@NotBlank(message = "Reading time is required")
	@Min(value = 1, message = "Please enter valid time in minutes")
	private int readingTime;
	
	@NotBlank(message = "Date is required")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private LocalDate readingDate;
	
	@NotBlank(message = "Formate is required")
	private String format;
	
	private String notes;

	public long getReadingEventId() {
		return readingEventId;
	}

	public void setReadingEventId(long readingEventId) {
		this.readingEventId = readingEventId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public int getReadingTime() {
		return readingTime;
	}

	public void setReadingTime(int readingTime) {
		this.readingTime = readingTime;
	}

	public LocalDate getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(LocalDate readingDate) {
		this.readingDate = readingDate;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	

}

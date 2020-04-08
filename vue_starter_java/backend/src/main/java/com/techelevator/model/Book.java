package com.techelevator.model;

import org.hibernate.validator.constraints.NotBlank;

public class Book {
	
	@NotBlank(message = "ISBN is required")
    private long isbn;
	
	@NotBlank(message = "Username is required")
    private String title;
	
	@NotBlank(message = "Author is required")
    private String author;
	
	
	private long id;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	

	
}

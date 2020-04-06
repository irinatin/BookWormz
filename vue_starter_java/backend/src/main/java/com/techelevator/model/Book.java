package com.techelevator.model;

import org.hibernate.validator.constraints.NotBlank;

public class Book {
	
	@NotBlank(message = "ISBN is required")
    private String isbn;
	
	@NotBlank(message = "Username is required")
    private String title;
	
	@NotBlank(message = "Author is required")
    private String author;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
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

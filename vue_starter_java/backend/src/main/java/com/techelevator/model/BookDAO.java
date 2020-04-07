package com.techelevator.model;

import java.util.List;

public interface BookDAO {
	
	public Book addNewBook(Book bookie);
	
	public List<Book> getAllBooks();

}

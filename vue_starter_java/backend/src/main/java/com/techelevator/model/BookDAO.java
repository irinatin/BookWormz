package com.techelevator.model;

import java.util.List;

public interface BookDAO {
	
	public boolean addNewBook(Book bookie);
	
	public List<Book> getAllBooksPerUser(long userId);
	
	public boolean deleteBook(Book bookie);
	
	public Book findBookByTitle(String title);
	
	public Book findBookByAuthor(String author);
	
	public Book findBookByIsbn(int Isbn);
	
	public Book findBookById(long id);
	

}

package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCBookDAO implements BookDAO{
	
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public boolean addNewBook(Book bookie) {
		long bookId = getNextBookId();
		String insertIntoBook = "INSERT INTO book VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(insertIntoBook, bookId, bookie.getIsbn(), bookie.getTitle(), bookie.getAuthor()); 
		
		return true;
	}

	@Override
	public List<Book> getAllBooksPerUser(long userId) { //per user
		
		List<Book> allBooksPerUser = new ArrayList<Book>();
		
		String getAllBooks = "SELECT * "
				+ 			" FROM book "
				+			" JOIN user_book ON user_book.book_id = book.book.id "
				+			" WHERE user_book.user_id = ?";
		
		Book bookie = new Book();
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllBooks);
		
		while(results.next()) {
			bookie = mapRowToBook(results);
			allBooksPerUser.add(bookie);
			
		}
				
		return allBooksPerUser;
	}

	@Override
	public boolean deleteBook(Book bookie) {
		
		String deleteFromBook = "DELETE FROM book WHERE title = ?";
		jdbcTemplate.update(deleteFromBook, bookie.getTitle());
		
		return true;
	}

	@Override
	public Book findBookByTitle(String title) {
		
		List<Book> getAllBooksByTitle = new ArrayList<Book>();
		
		String getAllBooks = "SELECT "
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findBookByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findBookByIsbn(int Isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findBookById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public long getNextBookId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('book_book_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1); //changed from int to long
		}
		else {
			throw new RuntimeException("Something went wrong with book sequence");
		}
	}
	
	private Book mapRowToBook(SqlRowSet results) {
		Book bookie = new Book();
		bookie.setId(results.getLong("book_id"));
		bookie.setIsbn(results.getString("isbn"));
		bookie.setTitle(results.getString("title"));
		bookie.setAuthor(results.getString("author"));
		
		
		return bookie;
	}
	
	

}

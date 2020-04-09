package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCBookDAO implements BookDAO{
	
	private JdbcTemplate jdbcTemplate;

	
	@Autowired
	public JDBCBookDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

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
	public String findBookByTitle(String title) {

		
		String getTheBookByTitle = "SELECT title FROM book WHERE title LIKE ?"; //not sure if LIKE will work here
		//if they look for a book by one word that can be a part of a title for multiple books
	
		SqlRowSet results = jdbcTemplate.queryForRowSet(getTheBookByTitle, title);
		
		results.next();
		String bookTitle = results.getString(3); //not sure if index is right, need to update my db
		
		return bookTitle;
	}
	

	@Override
	public List<Book> findBookByAuthor(String author) {
		
		List<Book> booksByAuthor = new ArrayList<Book>();
		String getBooksByAuthor = "SELECT * FROM book WHERE author = ?";
		
		Book bookie = new Book();
		SqlRowSet results = jdbcTemplate.queryForRowSet(getBooksByAuthor);
		
		while(results.next()) {
			bookie = mapRowToBook(results);
			booksByAuthor.add(bookie);
		}
		
		return booksByAuthor;
	}

	@Override
	public String findBookByIsbn(long isbn) {
		
		String getBookByIsbn = "SELECT title FROM book WHERE isbn = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(getBookByIsbn, isbn);
		results.next();
		String bookTitle = results.getString(3);
		
		return bookTitle;
	}

	@Override
	public String findBookById(long id) {
		
		String getBookById = "SELECT title FROM book WHERE id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(getBookById, id);
		results.next();
		String bookTitle = results.getString(3);
		
		return bookTitle;
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
		bookie.setIsbn(results.getLong("isbn"));
		bookie.setTitle(results.getString("title"));
		bookie.setAuthor(results.getString("author"));
		
		
		return bookie;
	}
	
	

}

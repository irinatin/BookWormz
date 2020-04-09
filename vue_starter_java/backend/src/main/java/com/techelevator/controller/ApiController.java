package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.Book;
import com.techelevator.model.BookDAO;
import com.techelevator.model.FamilyDAO;
import com.techelevator.model.ReadingEvent;
import com.techelevator.model.ReadingEventDAO;
import com.techelevator.model.UserInfo;
import com.techelevator.model.UserInfoDao;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;

/**
 * ApiController
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AuthProvider authProvider;
    
    @Autowired
    private BookDAO bookDAO;
    
    @Autowired 
    private ReadingEventDAO reDAO;
    
    @Autowired
	private AuthProvider auth;
    
    @Autowired
	private UserInfoDao user;
    
    @Autowired
   	private FamilyDAO family;
    
    
    @Autowired
    private UserDao userDAO;
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String authorizedOnly() throws UnauthorizedException {
        /*
        You can lock down which roles are allowed by checking
        if the current user has a role.
        
        In this example, if the user does not have the admin role
        we send back an unauthorized error.
        */
        if (!authProvider.userHasRole(new String[] { "admin" })) {
            throw new UnauthorizedException();
        }
        return "Success";
    }
    
    @RequestMapping(path = "/addBook", method = RequestMethod.POST)
    public boolean addBook(@RequestBody Book newBook) {
    	return bookDAO.addNewBook(newBook);
    }
    
    @RequestMapping(path = "/addReadingEvent", method = RequestMethod.POST)
    public ReadingEvent addReadingEvent(@RequestBody ReadingEvent reads) {
    	return reDAO.addReadingEvent(reads);
    }
    
    @RequestMapping(path = "/getFamilyList", method = RequestMethod.GET)
	public List<UserInfo> getFamilyList(){
    	Long familyId = user.getFamilyId(auth.getCurrentUser().getId());
    	List<UserInfo> familyMembers = family.getAllFamilyMembers(familyId);
		return familyMembers;
	}
    @RequestMapping(path = "/getAllBooks", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
    	return bookDAO.getAllBooks();
    }
    
    @RequestMapping(path = "/getUser", method = RequestMethod.GET)
    public List<User> getAllUsers() {
    	return userDAO.getAllUsers();
    }
    
    
}
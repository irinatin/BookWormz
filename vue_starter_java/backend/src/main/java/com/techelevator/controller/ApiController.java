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
import com.techelevator.model.ChildInfo;
import com.techelevator.model.FamilyDAO;
import com.techelevator.model.Prize;
import com.techelevator.model.PrizeDAO;
import com.techelevator.model.ReadingEvent;
import com.techelevator.model.ReadingEventDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;
import com.techelevator.model.UserInfo;
import com.techelevator.model.UserInfoDao;

/**
 * ApiController
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AuthProvider authProviderDAO;
    
    @Autowired
    private BookDAO bookDAO;
    
    @Autowired 
    private ReadingEventDAO reDAO;
    
    @Autowired
	private AuthProvider authDAO;
    
    @Autowired
	private UserInfoDao userInfoDAO;
    
    @Autowired
   	private FamilyDAO familyDAO;
    
    @Autowired
    private UserDao userDAO;
    
    @Autowired 
    private PrizeDAO prizeDAO;
    
    
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String authorizedOnly() throws UnauthorizedException {
        /*
        You can lock down which roles are allowed by checking
        if the current user has a role.
        
        In this example, if the user does not have the admin role
        we send back an unauthorized error.
        */
        if (!authProviderDAO.userHasRole(new String[] { "admin" })) {
            throw new UnauthorizedException();
        }
        return "Success";
    }
    
    @RequestMapping(path = "/addBook", method = RequestMethod.POST)
    public boolean addBook(@RequestBody Book newBook) {
    	return bookDAO.addNewBook(newBook, userInfoDAO.getFamilyId(authDAO.getCurrentUser().getId()));
    }
    
    @RequestMapping(path = "/addReadingEvent", method = RequestMethod.POST)
    public ReadingEvent addReadingEvent(@RequestBody ReadingEvent reads) {
    	return reDAO.addReadingEvent(reads, false);
    }
    
    @RequestMapping(path = "/getFamilyList", method = RequestMethod.GET)
	public List<UserInfo> getFamilyList(){
    	Long familyId = userInfoDAO.getFamilyId(authDAO.getCurrentUser().getId());
    	List<UserInfo> familyMembers = familyDAO.getAllFamilyMembers(familyId);
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
    
    @RequestMapping(path = "/addPrize", method = RequestMethod.POST)
    public boolean addPrize(@RequestBody Prize newPrize) {
    	return prizeDAO.createNewPrize(newPrize);
    }
    
    @RequestMapping(path = "/addChild", method = RequestMethod.POST)
    public boolean addChild(@RequestBody ChildInfo child) {
    	userDAO.saveUser(child.getUsername(), child.getPassword(), "child");
    	long childId = userDAO.getUserByUsername(child.getUsername()).getId();
    	Long familyId = userInfoDAO.getFamilyId(authDAO.getCurrentUser().getId());
    	userInfoDAO.saveUserInfo(child.getFirstName(), child.getLastName(), familyId, childId);
    	
    	return true;
    }
    
}
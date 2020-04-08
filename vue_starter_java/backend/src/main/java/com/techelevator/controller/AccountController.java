package com.techelevator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.JwtTokenHandler;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.authentication.UserCreationException;
import com.techelevator.model.User;
import com.techelevator.model.UserInfo;
import com.techelevator.model.UserInfoDao;

/**
 * AccountController
 */

@CrossOrigin
@RestController
public class AccountController {
	
    @Autowired
    private AuthProvider auth;
    
    @Autowired UserInfoDao userInfo;

    @Autowired
    private JwtTokenHandler tokenHandler;
    
    String userName;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user, RedirectAttributes flash) throws UnauthorizedException {
        if (auth.signIn(user.getUsername(), user.getPassword())) {
            User currentUser = auth.getCurrentUser();
            return tokenHandler.createToken(user.getUsername(), currentUser.getRole());
        } else {
            throw new UnauthorizedException();
        }
        
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@Valid @RequestBody User user, BindingResult result) throws UserCreationException {
    	this.userName = user.getUsername();
        if (result.hasErrors()) {
            String errorMessages = "";
            for (ObjectError error : result.getAllErrors()) {
                errorMessages += error.getDefaultMessage() + "\n";
            }
            throw new UserCreationException(errorMessages);
        }
        auth.register(user.getUsername(), user.getPassword(), user.getRole());
        return "{\"success\":true}";
    }
    
    @RequestMapping(path = "/logoff", method = RequestMethod.POST)
    public String logoff() {
    	if(auth.isLoggedIn()) {
    		auth.logOff();
    	}
    	return "{\"success\":true}";
    }
    
    @RequestMapping(path = "/userinfo", method = RequestMethod.POST)
    public String userinfo(@RequestBody UserInfo info) {
    	userInfo.getUserId(userName);
    	userInfo.saveUserInfo(info.getFirstName(), info.getLastName(), info.getFamilyName(), userName);
    	return "{\"success\":true}";
    }

}
package com.techelevator.model;

public interface UserInfoDao {
	
	public UserInfo saveUserInfo(String firstName, String lastName, String familyName, String username);
	
	public Long getUserId (String userName);

}

package com.techelevator.model;

public interface UserInfoDao {
	
	public boolean saveUserInfo(UserInfo info, Long familyId, Long userId);
	
	public Long getUserId (String userName);
	
	public Long getFamilyId(Long userId);
	

}

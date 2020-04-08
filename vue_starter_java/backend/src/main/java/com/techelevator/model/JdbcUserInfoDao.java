package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserInfoDao implements UserInfoDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcUserInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	

	@Override
	public UserInfo saveUserInfo(String firstName, String lastName, String familyName, String username) {
		Long userId = getUserId(username);
		
		String sqlSaveFamily = "INSERT INTO family VALUES (DEFAULT, ?)";
		jdbcTemplate.update(sqlSaveFamily, familyName);
		
		String sqlFamilyId = "SELECT family_id from family WHERE family_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFamilyId, familyName);
		results.next();
		Long familyId = results.getLong(1);
		
		String sqlSaveUserInfo = "INSERT INTO user_info VALUES (DEFAULT, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlSaveUserInfo, userId, firstName, lastName, familyId);
		
		String sqlUserInfoId = "SELECT user_info_id from user_info WHERE user_id = ?";
		SqlRowSet results2 = jdbcTemplate.queryForRowSet(sqlUserInfoId, userId);
		
		results2.next();
		Long userInfoId = results2.getLong(1);
		
		
		UserInfo userInfo = new UserInfo();
		
		userInfo.setFamilyName(familyName);
		userInfo.setFamilyId(familyId);
		userInfo.setFirstName(firstName);
		userInfo.setLastName(lastName);
		userInfo.setUserId(userId);
		userInfo.setUserInfoId(userInfoId);
		
		return userInfo;
		
		
	}



	@Override
	public Long getUserId(String userName) {
		
		String sqlUserId = "SELECT id from users WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlUserId, userName);
	
		results.next();
		Long userId = results.getLong(1);
		return userId;
	}
	
	

}

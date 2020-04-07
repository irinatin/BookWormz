package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;



public class JdbcFamilyDAO implements FamilyDAO{

	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Family> getAllFamilies() {
		List<Family> allFamilies = new ArrayList<Family>();
		String getAllFamilies = "SELECT family_id, family_name FROM family";
		Family newFamily = new Family();
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllFamilies);
		
		while(results.next()) {
			newFamily = mapRowToFamily(results);
			allFamilies.add(newFamily);
			
		}
		return allFamilies;
	}
	

	@Override
	public boolean createFamily(String familyName) {
			int	 familyId = getNextFamilyId();
			String insertIntoFamily = "INSERT INTO family VALUES (?, ?)";
			jdbcTemplate.update(insertIntoFamily, familyId, familyName);

		return true;
	}
	
	
	@Override
	public String getFamilyNameById(long familyId) {
		
		String getFamilyName = "SELECT family_name FROM family WHERE family_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getFamilyName, familyId);
		
		results.next();
		String familyName = results.getString(1);
		return familyName;
	}
	
	@Override
	public long getFamilyIdByName(String familyName) {
		
		long getFamilyId = "SELECT family_id FROM family WHERE family_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getFamilyId, familyName);
		
		results.next();
		long familyId = results.getLong(2);
		
		return familyId;
	}
	
	
	
	
	
	public int getNextFamilyId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('family_family_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getInt(1);
		}
		else {
			throw new RuntimeException("Something went wrong with family sequence");
		}
	}
	private Family mapRowToFamily(SqlRowSet results) {
		Family newFamily = new Family();
		newFamily.setFamilyId(results.getLong("family_id"));
		newFamily.setFamilyName(results.getString("family_name"));
		
		return newFamily;
	}
}
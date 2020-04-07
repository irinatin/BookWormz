package com.techelevator.model;

import java.util.List;

public interface FamilyDAO {

	public List<Family> getAllFamilies();
	public boolean createFamily(String familyName);
	String getFamilyNameById(long familyId);
}

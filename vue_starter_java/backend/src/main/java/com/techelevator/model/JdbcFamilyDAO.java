package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcFamilyDAO implements FamilyDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcFamilyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Family> getAllFamilies() {
		List<Family> allFamilies = new ArrayList<Family>();
		String getAllFamilies = "SELECT family_id, family_name FROM family";
		Family newFamily = new Family();
		SqlRowSet results = jdbcTemplate.queryForRowSet(getAllFamilies);

		while (results.next()) {
			newFamily = mapRowToFamily(results);
			allFamilies.add(newFamily);

		}
		return allFamilies;
	}

	@Override
	public boolean createFamily(String familyName) {
		String insertIntoFamily = "INSERT INTO family VALUES (DEFAULT, ?)";
		jdbcTemplate.update(insertIntoFamily, familyName);
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

		String getFamilyId = "SELECT family_id FROM family WHERE family_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getFamilyId, familyName);

		results.next();
		long familyId = results.getLong(1);

		return familyId;
	}

	public boolean doesFamilyExist(String familyName) {

		String sqlFamilyExist = "SELECT COUNT(family_id) FROM family WHERE family_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFamilyExist, familyName);

		results.next();
		if (results.getLong(1) == 0) {
			return false;
		} else {
			return true;
		}

	}

	private Family mapRowToFamily(SqlRowSet results) {
		Family newFamily = new Family();
		newFamily.setFamilyId(results.getLong("family_id"));
		newFamily.setFamilyName(results.getString("family_name"));

		return newFamily;
	}
}
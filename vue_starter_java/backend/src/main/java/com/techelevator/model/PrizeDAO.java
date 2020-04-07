package com.techelevator.model;

import java.util.List;

public interface PrizeDAO {

	public boolean createNewPrize(String blingBling);
	public Prize editExistingPrize(Prize blingBlung);
	public boolean deletePrize(long prizeId);
	public List<Prize> getAllPrizes();
	
}

package com.techelevator.model;

import java.util.List;

public interface PrizeDAO {

	public Prize createNewPrize(Prize blingBling);
	public Prize editExistingPrize(Prize blingBlung);
	public void deletePrize(long prizeId);
	public List<Prize> getAllPrizes();
	
}

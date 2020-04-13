package com.techelevator.model;

import java.util.List;

public interface PrizeDAO {

	public boolean createNewPrize(Prize blingBling);
	public Prize editExistingPrize(Prize blingBlung);
	public boolean deletePrize(Prize blingBling);
	public List<Prize> getAllPrizes();
	public List<PrizeListInfo> getPrizeListInfo(String role, Long userId);
}

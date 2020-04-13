package com.techelevator.model;

import java.util.List;

public interface PrizeDAO {

	public boolean createNewPrize(Prize blingBling);
	public boolean editExistingPrize(Prize blingBlung);
	public boolean deletePrize(Prize blingBling);
	public List<Prize> getAllPrizes();
	public Prize getPrize(long prizeId);
//	public List<PrizeListInfo> getPrizeListInfo(String role, Long userId);
	public List<String> getPrizesPerUser(Long userId);
	
	
}

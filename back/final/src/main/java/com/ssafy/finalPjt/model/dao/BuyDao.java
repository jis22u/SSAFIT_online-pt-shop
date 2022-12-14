package com.ssafy.finalPjt.model.dao;

import com.ssafy.finalPjt.model.dto.Buy;

public interface BuyDao {

  int insertBuy(Buy buy);

  int insertBuyDetail(Buy buy);
}


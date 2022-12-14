package com.ssafy.finalPjt.model.service.buy;

import com.ssafy.finalPjt.model.dto.Buy;

public interface BuyService {

  // 구매하기
  public int registBuy(Buy buy, String[] v_id);
}

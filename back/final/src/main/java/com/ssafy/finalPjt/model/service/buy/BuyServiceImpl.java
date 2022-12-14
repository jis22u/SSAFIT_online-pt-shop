package com.ssafy.finalPjt.model.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalPjt.model.dao.BuyDao;
import com.ssafy.finalPjt.model.dto.Buy;

@Service
public class BuyServiceImpl implements BuyService {

  @Autowired
  BuyDao buyDao;

  @Override
  public int registBuy(Buy buy, String[] v_ids) {
    int result = buyDao.insertBuy(buy);
    if (result < 0)
      return -1;

    for (String id : v_ids) {
      System.out.println(id);
      buy.setV_id(id);

      result = buyDao.insertBuyDetail(buy);

      if (result < 0)
        return -1;
    }
    return 1;
  }

}

package com.ssafy.finalPjt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.finalPjt.model.dto.Buy;
import com.ssafy.finalPjt.model.service.buy.BuyService;

@Controller
@RequestMapping("/api/buyApi/")
public class BuyRestController {

  @Autowired
  BuyService buyService;

  @PostMapping("/")
  public ResponseEntity<?> buy(Buy buy, @RequestBody String[] v_ids) {
    System.out.println("구매등록 시작");

    if (buyService.registBuy(buy, v_ids) > 0)
      return new ResponseEntity<>(HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

}

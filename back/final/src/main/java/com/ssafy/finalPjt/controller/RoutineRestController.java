package com.ssafy.finalPjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.finalPjt.model.dto.Routine;
import com.ssafy.finalPjt.model.service.routine.RoutineService;

@RestController
@RequestMapping("/api/routineApi")
public class RoutineRestController {

  @Autowired
  RoutineService routineService;

  @PostMapping("/")
  public ResponseEntity<?> registRoute(@RequestBody Routine[] routine, String r_name, String u_id) {
    System.out.println(r_name);
    System.out.println(u_id);

    // routine 테이블
    int result = routineService.registList(r_name, u_id);

    if (result <= 0)
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    // routine_detail 테이블
    for (Routine route : routine) {
      System.out.println(route);
      route.setR_id(result);
      routineService.registDetail(route);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/")
  public ResponseEntity<List<Routine>> getListById(String u_id) {
    System.out.println(u_id);
    return new ResponseEntity<>(routineService.getListById(u_id), HttpStatus.OK);
  }

  @GetMapping("/{r_id}")
  public ResponseEntity<List<Routine>> getDetailListById(@PathVariable int r_id) {
    System.out.println(r_id);
    return new ResponseEntity<>(routineService.getDetailListById(r_id), HttpStatus.OK);
  }
}

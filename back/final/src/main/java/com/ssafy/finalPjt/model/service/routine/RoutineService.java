package com.ssafy.finalPjt.model.service.routine;

import java.util.List;

import com.ssafy.finalPjt.model.dto.Routine;

public interface RoutineService {
  int registList(String r_name, String u_id);

  public int registDetail(Routine routine);

  public List<Routine> getListById(String u_id);

  public List<Routine> getDetailListById(int r_id);

}

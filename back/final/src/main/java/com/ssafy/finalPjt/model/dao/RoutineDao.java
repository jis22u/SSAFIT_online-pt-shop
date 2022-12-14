package com.ssafy.finalPjt.model.dao;

import java.util.List;

import com.ssafy.finalPjt.model.dto.Routine;

public interface RoutineDao {
  public int insertList(Routine routine);

  public int insertDetail(Routine routine);

  public List<Routine> selectListById(String u_id);

  public List<Routine> selectDetailListByR_Id(int r_id);
}

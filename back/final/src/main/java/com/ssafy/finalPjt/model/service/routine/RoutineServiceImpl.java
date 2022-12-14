package com.ssafy.finalPjt.model.service.routine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalPjt.model.dao.RoutineDao;
import com.ssafy.finalPjt.model.dto.Routine;

@Service
public class RoutineServiceImpl implements RoutineService {
  @Autowired
  private RoutineDao routineDao;

  @Override
  public int registList(String r_name, String u_id) {
    Routine rout = new Routine();
    rout.setR_name(r_name);
    rout.setU_id(u_id);
    routineDao.insertList(rout);
    return rout.getR_id();
  }

  public int registDetail(Routine routine) {
    return routineDao.insertDetail(routine);
  }

  @Override
  public List<Routine> getDetailListById(int r_id) {
    return routineDao.selectDetailListByR_Id(r_id);
  }

  @Override
  public List<Routine> getListById(String u_id) {
    return routineDao.selectListById(u_id);
  }

}

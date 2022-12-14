package com.ssafy.finalPjt.model.service.trainer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalPjt.model.dao.TrainerDao;
import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.dto.Video;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	TrainerDao trainerDao;

	@Override
	public List<Video> getTrainerVideoList(List<User> trainerList) {
		return trainerDao.selectTrainerVideoList(trainerList);
	}

	@Override
	public int registTrainer(User user) {
		return trainerDao.insert(user);
	}

	@Override
	public List<User> getTrainerList(String t_name) {
		return trainerDao.selectTrainerList(t_name);
	}
	
	@Override
	public User getTrainerOne(String t_name) {
		return trainerDao.selectTrainerOne(t_name);
	}

	@Override
	public List<Video> getVideoIncomByTName(String t_name) {
		return trainerDao.selectVideoIncomeByTName(t_name);
	}

	@Override
	public List<Video> getVideoListByTName(String t_name) {
		return trainerDao.selectVideoByTName(t_name);
	}

	@Override
	public List<String> getTrainerMajor(String u_id) {
		return trainerDao.selectMajor(u_id);
	}

	
}

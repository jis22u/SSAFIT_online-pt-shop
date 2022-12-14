package com.ssafy.finalPjt.model.service.trainer;

import java.util.List;

import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.dto.Video;

public interface TrainerService {
	List<Video> getTrainerVideoList(List<User> trainerList);

	int registTrainer(User user);

	List<User> getTrainerList(String t_name);

	User getTrainerOne(String t_name);

	List<Video> getVideoIncomByTName(String t_name);

	List<Video> getVideoListByTName(String t_name);

	List<String> getTrainerMajor(String u_id);
}

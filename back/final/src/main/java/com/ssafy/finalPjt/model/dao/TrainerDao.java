package com.ssafy.finalPjt.model.dao;

import java.util.List;

import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.dto.Video;

public interface TrainerDao {
	List<Video> selectTrainerVideoList(List<User> trainerList);

	// 트레이너로 등록
	int insert(User user);

	List<User> selectTrainerList(String t_name);

	User selectTrainerOne(String t_name);
	
	// 트레이너의 이름으로 그 트레이너의 비디오별 총 수익 찾기
	List<Video> selectVideoIncomeByTName(String t_name);

	// 트레이너의 이름으로 올린 모든 비디오 리스트
	List<Video> selectVideoByTName(String t_name);

	// 트레이너 전문뷴야 찾기
	List<String> selectMajor(String u_id);
}

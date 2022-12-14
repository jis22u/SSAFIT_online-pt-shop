package com.ssafy.finalPjt.model.dao;

import java.util.List;

import com.ssafy.finalPjt.model.dto.Follow;
import com.ssafy.finalPjt.model.dto.User;

public interface FollowDao {

	// 팔로우 하기
	int insert(Follow follow);
	
	// 팔로우 취소
	int delete(Follow follow);
	
	// 팔로우 or 팔로워 조회
	List<User> selectUser(Follow follow);
	
	// 내가 팔로우한 트레이너만 조회
	List<User> selectFollowTrainer(String userId);
}

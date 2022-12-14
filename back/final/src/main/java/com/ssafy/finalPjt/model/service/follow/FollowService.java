package com.ssafy.finalPjt.model.service.follow;

import java.util.List;

import com.ssafy.finalPjt.model.dto.Follow;
import com.ssafy.finalPjt.model.dto.User;

public interface FollowService {
	// 팔로우 하기
	int doFollow(Follow follow);
	
	// 팔로우 취소
	int unFollow(Follow follow);
	
	// 팔로우 or 팔로워 조회
	List<User> getFollowList(Follow follow);
	
	// 내가 팔로우한 트레이너만 조회
	List<User> getFollowTrainerList(String userId);
}

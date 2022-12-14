package com.ssafy.finalPjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.finalPjt.model.dto.Follow;
import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.service.follow.FollowService;

@RestController
@RequestMapping("/api/followApi")
public class FollowRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private FollowService followService;

	@PostMapping("/toggle")
	public ResponseEntity<String> toggleFollow(Follow follow) {
		System.out.println("토글 입장");

		String msg = "등록했습니다";
		try {
			followService.doFollow(follow);
		} catch (Exception e) {
			msg = "삭제했습니다";
			unFollow(follow);
		} finally {
			System.out.println(msg);
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	// 팔로우 하기
	@PostMapping("/")
	public ResponseEntity<String> doFollow(Follow follow) {
		int result = followService.doFollow(follow);

		if (result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	// 팔로우 취소
	@DeleteMapping("/")
	public ResponseEntity<String> unFollow(Follow follow) {
		System.out.println(follow);
		int result = followService.unFollow(follow);

		if (result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	// 팔로우 or 팔로워 조회
	@GetMapping("/")
	public ResponseEntity<List<User>> selectList(String from_user, String to_user) {
		Follow follow = new Follow();
		follow.setFrom_user(from_user);
		follow.setTo_user(to_user);

		return new ResponseEntity<List<User>>(followService.getFollowList(follow), HttpStatus.OK);
	}

	// @GetMapping("/trainer")
	// public ResponseEntity<List<User>> selectTrainerList(String userId) {
	// return new
	// ResponseEntity<List<User>>(followService.getFollowTrainerList(userId),
	// HttpStatus.OK);
	// }

}

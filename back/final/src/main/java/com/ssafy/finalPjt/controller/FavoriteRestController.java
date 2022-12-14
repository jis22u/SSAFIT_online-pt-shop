package com.ssafy.finalPjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.finalPjt.model.dto.Favorite;
import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.dto.Video;
import com.ssafy.finalPjt.model.service.favorite.FavoriteService;

@RestController
@RequestMapping("/api/favoriteApi")
public class FavoriteRestController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private FavoriteService favoriteService;

	// 좋아요
	@PostMapping("/favoriteUp")
	public ResponseEntity<String> favoriteUp(Favorite favorite) {
		System.err.println("con" + favorite.toString());
		if (favoriteService.like(favorite)) {
			System.out.println("success");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	// 좋아요 취소
	@PostMapping("/favoriteDown")
	public ResponseEntity<String> favoriteDown(Favorite favorite) {
		System.err.println("con" + favorite.toString());
		if (favoriteService.disLike(favorite)) {
			System.out.println("success2");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	// 좋아요한 사람들
	@GetMapping("/favoritePeople")
	public ResponseEntity<List<User>> favoritePeople(Favorite favorite) {
		System.err.println("con---" + favorite.toString());
		List<User> favoriteUser = favoriteService.likePeople(favorite);
		if (favoriteService.likePeople(favorite).size() != 0) {
			System.out.println("있습니다");
			return new ResponseEntity<List<User>>(favoriteUser, HttpStatus.OK);
		} else {
			System.out.println("없습니다");
			return new ResponseEntity<List<User>>(favoriteUser, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Video>> favoriteVideos(@RequestParam(name = "u_id") String u_id) {
		System.out.println("-- favorite video list search : " + u_id);
		List<Video> list = favoriteService.getVideoList(u_id);

		if (list.size() == 0)
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}

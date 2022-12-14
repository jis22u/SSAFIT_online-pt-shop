package com.ssafy.finalPjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.dto.Video;
import com.ssafy.finalPjt.model.service.follow.FollowService;
import com.ssafy.finalPjt.model.service.trainer.TrainerService;

@RestController
@RequestMapping("/api/trainerApi")
public class TrainerRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private TrainerService trainerService;

	@Autowired
	private FollowService followService;

	/**
	 * 해당 사용자가 팔로우한 트레이너의 영상 리스트 찾아오기
	 * 
	 * @param userId 사용자 아이디
	 * @return List<Video>
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<List<Video>> followTrainerVideoList(@PathVariable String userId) {
		// 해당 사용자가 팔로우한 사람들 다 찾기
		List<User> trainerList = followService.getFollowTrainerList(userId);

		List<Video> videoList = trainerService.getTrainerVideoList(trainerList);

		if (videoList.size() == 0) {
			System.out.println("fail");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			System.out.println("success");
			return new ResponseEntity<>(videoList, HttpStatus.OK);
		}
	}

	/**
	 * 해당 트레이너가 등록한 온라인 PT 모두 불러오기
	 * 
	 * @PathVariable 안붙이고 받아봄
	 * @param t_name
	 * @return
	 */
	@GetMapping("/trainerVideos")
	public ResponseEntity<List<Video>> trainerVideoList(String t_name) {
		// 해당 t_name을 가진 User 찾기, 1명
		List<User> trainerList = trainerService.getTrainerList(t_name);
		System.out.println(trainerList.get(0).getT_name());
		List<Video> videoList = trainerService.getTrainerVideoList(trainerList);

		if (videoList.size() == 0) {
			System.out.println("fail");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			System.out.println("success");
			return new ResponseEntity<>(videoList, HttpStatus.OK);
		}

	}

	@PostMapping("/")
	public ResponseEntity<?> registTrainer(@RequestBody User user) {
		System.out.println(user);
		int result = trainerService.registTrainer(user);
		if (result == 1) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}

	}

	// 트레이너 이름으로 트레이너 리스트 검색하기
	@GetMapping("/")
	public ResponseEntity<List<User>> selectTrainers(String t_name) {
		System.out.println(t_name);
		List<User> list = trainerService.getTrainerList(t_name);
		if (list.size() == 0)
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// 트레이너 1명 검색
	@GetMapping("/trainerOne")
	public ResponseEntity<User> selectTrainerOne(String t_name) {
		System.out.println(t_name);
		User user = trainerService.getTrainerOne(t_name);
		if (user == null)
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// 수입보기
	@GetMapping("/income")
	public ResponseEntity<List<Video>> searchMyIncome(String t_name) {
		List<Video> list = trainerService.getVideoIncomByTName(t_name);

		if (list.size() == 0)
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// 내가 올린 비디오 리스트
	@GetMapping("/video")
	public ResponseEntity<List<Video>> searchMyVideo(String t_name) {
		List<Video> list = trainerService.getVideoListByTName(t_name);
		if (list.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * 트래이너의 전공분야 찾기 프론트에서 axios ({... params:{} }) params로 보내주기 때문에 @Pathvariable
	 * 안해도됨
	 * 
	 * @param u_id
	 * @return
	 */
	@GetMapping("/major")
	public ResponseEntity<List<String>> searchMajor(String u_id) {
		List<String> majorList = trainerService.getTrainerMajor(u_id);
		if (majorList.size() == 0)
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(majorList, HttpStatus.OK);
	}
}

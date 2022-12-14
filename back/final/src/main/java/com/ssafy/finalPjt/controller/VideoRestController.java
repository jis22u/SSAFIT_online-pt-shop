package com.ssafy.finalPjt.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.finalPjt.model.dto.Video;
import com.ssafy.finalPjt.model.dto.VideoSearchCondition;
import com.ssafy.finalPjt.model.service.user.UserService;
import com.ssafy.finalPjt.model.service.video.VideoService;

@RestController
@RequestMapping("/api/videoApi")
public class VideoRestController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private VideoService videoService;

	@Autowired
	private UserService userService;

	@GetMapping("/video/{id}")
	public ResponseEntity<Video> videoDetail(@PathVariable String id) {

		videoService.viewCntup(id);

		return new ResponseEntity<Video>(videoService.readVideo(id), HttpStatus.OK);
	}

	/**
	 * 사용자가 입력한 조건에 따라 영상 검색하기
	 * 
	 * @param category
	 * @param keyword
	 * @param range
	 * @return
	 */
	@GetMapping("/video")
	public ResponseEntity<List<Video>> videoList(String[] category, @RequestParam(defaultValue = "") String keyword,
			String range) {
		// HashMap<String,String> params=new HashMap<>();
		System.out.println(category);
		System.out.println(keyword);
		System.out.println(range);

		// 키워드 없으면 리턴
		// if (keyword.equals("")) {
		// return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		// }

		String[] ranges = new String[0];

		if (range.equals("전체")) {
			ranges = new String[2];
			ranges[0] = "title";
			ranges[1] = "t_name";
		} else if (range.equals("제목")) {
			ranges = new String[1];
			ranges[0] = "title";

		} else if (range.equals("트레이너")) {
			ranges = new String[1];
			ranges[0] = "t_name";
		} else {
			ranges = new String[0];
		}

		if (category == null) {
			category = new String[0];
		}

		List<Video> videoList = videoService.findVideoList(new VideoSearchCondition(category, ranges, keyword));

		if (videoList.size() == 0) {
			System.out.println("fail");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			System.out.println("success");
			return new ResponseEntity<>(videoList, HttpStatus.OK);
		}
		// params.put("category", category);
		// params.put("keyword",keyword);
		// System.out.println("cont"+keyword);
		// try {
		// return new
		// ResponseEntity<List<Video>>(videoService.findVideoList(),HttpStatus.OK);
		// }catch(Exception e){
		// throw new VideoNotFoundException(e+" 해당 비디오는 없습니다.");
		// }

	}

	/**
	 * 구매 높은 순으로 영상 리스트 가져오기
	 * 
	 * @return
	 */
	@GetMapping("/orderOfBuy")
	public ResponseEntity<List<Video>> videoListByBuyCnt() {
		List<Video> videoList = videoService.getVideoByBuyCnt();

		if (videoList.size() == 0) {
			System.out.println("fail");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			System.out.println("success");
			return new ResponseEntity<>(videoList, HttpStatus.OK);
		}
	}

	/**
	 * 최신순으로 영상 리스트 가져오기
	 */
	@GetMapping("/orderOfReg")
	public ResponseEntity<List<Video>> videoListByReg() {
		List<Video> videoList = videoService.getVideoByReg();

		if (videoList.size() == 0) {
			System.out.println("fail");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			System.out.println("success");
			return new ResponseEntity<>(videoList, HttpStatus.OK);
		}
	}

	/**
	 * 사용자의 운동목적에 맞게 영상 리스트 가져오기
	 * 
	 * @return
	 */
	@GetMapping("/purposeVideo/{userId}")
	public ResponseEntity<List<Video>> videoListByPurpose(@PathVariable String userId) {
		List<String> purposeList = userService.getUserPurpose(userId);

		if (purposeList.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		// mybatis 정규표현식 사용을 위해
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < purposeList.size(); i++) {
			// System.out.println(purposeList.get(i));
			sb.append(purposeList.get(i)).append("|");
		}
		sb.delete(sb.length() - 1, sb.length());
		String input = sb.toString();

		List<Video> videoList = videoService.getVideoByPurpose(input);
		// System.out.println(videoList.size());

		if (videoList.size() == 0) {
			System.out.println("fail");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			System.out.println("success");
			return new ResponseEntity<>(videoList, HttpStatus.OK);
		}
	}

	/**
	 * @param video
	 * @param file
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<?> registVideo(Video video, MultipartFile file, MultipartFile imgFile) {
		System.out.println(video);
		System.out.println(file.getOriginalFilename());
		System.out.println(imgFile.getOriginalFilename());

		String uploadPath = "C:/finalUpload/videos/";
		String uploadPath2 = "C:/finalUpload/images/";
		String uuid = UUID.randomUUID().toString();
		String prefixName = uuid + "_" + file.getOriginalFilename();

		if (!new File(uploadPath).exists()) {
			new File(uploadPath).mkdirs();
		}
		if (!new File(uploadPath2).exists()) {
			new File(uploadPath2).mkdirs();
		}

		File target = new File(uploadPath, prefixName);
		File target2 = new File(uploadPath2, prefixName + ".png");
		System.out.println(target.toPath());

		video.setV_id(prefixName);

		try {
			FileCopyUtils.copy(file.getBytes(), target);

			FileCopyUtils.copy(imgFile.getBytes(), target2);
			videoService.registVideo(video);
			System.out.println("성공");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}

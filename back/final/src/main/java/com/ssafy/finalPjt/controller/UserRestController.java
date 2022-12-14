package com.ssafy.finalPjt.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.dto.Video;
import com.ssafy.finalPjt.model.service.user.UserService;
import com.ssafy.finalPjt.util.JwtUtil;

@RestController
@RequestMapping("/api/userApi")
public class UserRestController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String WRONG = "wrong";

	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtil jwtUtil;

	// 회원 등록
	@PostMapping("/regist")
	public ResponseEntity<String> userRegist(@RequestBody User user) {
		System.out.println(user);

		userService.registUser(user);

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 로그인
	@GetMapping("/login")
	public ResponseEntity<Map<String, Object>> userLogin(String id, String pw) {

		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		// id, pw 유효성 처리?
		System.out.println(id + " " + pw);
		String ans = userService.loginUser(id, pw); // 성공, 실패, 불일치

		try {
			if (ans.equals("success")) {
				result.put("access-token", jwtUtil.createToken("id", id));
				result.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else if (ans.equals("wrong")) {
				result.put("message", WRONG);
				status = HttpStatus.OK;
			} else { // ans.equals("fail")
				result.put("message", FAIL);
				status = HttpStatus.OK;
			}
		} catch (UnsupportedEncodingException e) {
			result.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		System.out.println(result);
		return new ResponseEntity<Map<String, Object>>(result, status);

	}

	// 회원정보 상세
	@GetMapping("/detail")
	public ResponseEntity<User> userDetail(String id) {
		System.out.println(id + " 이건 아이디");
		User user = userService.datailUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 탈퇴
	@DeleteMapping("/delete")
	public ResponseEntity<String> userDelete(String id) {
		userService.dropUser(id);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 수정
	@PutMapping("/update")
	public ResponseEntity<String> userUpdate(@RequestBody User user) {
//		System.out.println(user);
		userService.modifyUser(user);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 회원중복 체크
	@GetMapping("/check")
	public ResponseEntity<String> checkDuplicateId(String u_id) {
		boolean chk_id = userService.checkId(u_id);
		System.out.println("회원 중복체크 " + chk_id);
		if (chk_id) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@GetMapping("/email")
	public ResponseEntity<Boolean> checkDuplidcateEmail(String email) {
		return new ResponseEntity<>(userService.checkEmail(email), HttpStatus.OK);
	}

	@GetMapping("/nickname")
	public ResponseEntity<Boolean> checkDuplidcateNickname(String nickname) {
		return new ResponseEntity<>(userService.checkNickname(nickname), HttpStatus.OK);
	}

	@GetMapping("/phone_no")
	public ResponseEntity<Boolean> checkDuplidcatePhone(String phone_no) {
		return new ResponseEntity<>(userService.checkPhone(phone_no), HttpStatus.OK);
	}

	// 아이디 또는 닉네임으로 사용자 찾기
	@GetMapping("/")
	public ResponseEntity<List<User>> getUserList(String u_id, String nickname) {
		List<User> list;

		System.out.println(u_id);
		System.out.println(nickname);

		if (u_id != null)
			list = userService.getUserListById(u_id);
		else
			list = userService.getUserListByNickname(nickname);

		System.out.println(list.size());

		if (list.size() == 0)
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// 아이디로 사용자의 구매 리스트 찾기
	@GetMapping("/buyList")
	public ResponseEntity<List<Video>> getBuyVideo(String u_id) {
		if (u_id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(userService.getBuyListById(u_id), HttpStatus.OK);
	}

	@GetMapping("/purpose")
	public ResponseEntity<List<Map<String, Object>>> getPurposeList(String u_id) {
		return new ResponseEntity<>(userService.getPurposeList(u_id), HttpStatus.OK);
	}

	// 삭제도 함
	@PostMapping("/purpose")
	public ResponseEntity<?> addPurposeList(int p_id, String u_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("u_id", u_id);
		map.put("p_id", p_id);

		return new ResponseEntity<>(userService.addPurpose(map), HttpStatus.OK);
	}

	// 프로필 사진 수정
	@PutMapping("/profile")
	public ResponseEntity<String> modifyProfile(MultipartFile profile, String u_id) throws IOException {
		System.out.println(profile);
		System.out.println(profile.getOriginalFilename());

		String uploadPath = "";

		// try {

		uploadPath = "C:/finalUpload/images/";

		// resLoader.getResource("C:/finalUpload/images/").getURI().getPath();

		System.out.println(uploadPath);

		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		// String fileName = profile.getOriginalFilename();

		String prefixName = u_id + "_" + profile.getOriginalFilename();

		if (!new File(uploadPath).exists()) {
			new File(uploadPath).mkdirs();
		}

		File target = new File(uploadPath, prefixName);
		System.out.println(target.toPath());
		User user = new User();
		user.setU_id(u_id);
		user.setProfile(prefixName);

		try {
			FileCopyUtils.copy(profile.getBytes(), target);
			userService.modifyUser(user);
			System.out.println("성공");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(prefixName, HttpStatus.OK);
	}

}

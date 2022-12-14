package com.ssafy.finalPjt.model.service.user;

import java.util.List;
import java.util.Map;

import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.dto.Video;

public interface UserService {

	// 회원 등록
	boolean registUser(User user);

	// 로그인
	String loginUser(String id, String pw);

	// 회원정보 수정
	boolean modifyUser(User user);

	// 회원정보 상세
	User datailUser(String id);

	// 탈퇴
	void dropUser(String id);

	// 아이디 중복확인
	boolean checkId(String id);

	boolean checkEmail(String email);

	boolean checkNickname(String nickname);

	boolean checkPhone(String phone_no);

	List<User> getUserListById(String id);

	List<User> getUserListByNickname(String nickname);

	List<Video> getBuyListById(String u_id);

	// 회원의 운동목적 찾기
	List<String> getUserPurpose(String id);

	List<Map<String, Object>> getPurposeList(String id);

	int addPurpose(Map<String, Object> map);

}

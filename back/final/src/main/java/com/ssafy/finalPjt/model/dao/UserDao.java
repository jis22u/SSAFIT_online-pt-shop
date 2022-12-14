package com.ssafy.finalPjt.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.dto.Video;

public interface UserDao {

	// 회원 등록
	int createUser(User user);

	// 로그인, 회원정보 상세
	User findUser(String id);

	// 회원정보 수정
	int updateUser(User user);

	// 탈퇴
	void deleteUser(String id);

	// 유저리스트 검색
	List<User> selectUserListById(String id);

	List<User> selectUserListByNickname(String nickname);

	// 회원 중복 확인
	int chkUserID(String id);

	int chkEmail(String id);

	int chkPhoneNo(String id);

	int chkNickname(String id);

	List<Video> selectBuyVideo(String u_id);

	// 회원의 운동목적 찾기
	List<String> selectUserPurpose(String id);

	List<Map<String, Object>> selectPurpose(String u_id);

	int insertPurpose(Map<String, Object> map);

	int deletePurpose(Map<String, Object> map);
}

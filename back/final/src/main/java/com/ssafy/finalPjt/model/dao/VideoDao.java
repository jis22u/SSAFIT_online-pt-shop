package com.ssafy.finalPjt.model.dao;

import java.util.List;

import com.ssafy.finalPjt.model.dto.Video;
import com.ssafy.finalPjt.model.dto.VideoSearchCondition;

public interface VideoDao {

	// 한개 조회
	Video selectOne(String id);

	// 카테고리 + 검색
	List<Video> getVideoList(VideoSearchCondition condition);

	// 조회수 증가
	void updateCnt(String id);

	// 구매순으로 영상 리스트 검색
	List<Video> selectVideoByBuyCnt();

	// 최신순으로 영상 리스트 검색
	List<Video> selectVideoByReg();

	// 사용자의 운동목적에 맞게 영상 리스트 검색
	List<Video> selectVideoByPurpose(String input);

	// 영상 등록
	int insertVideo(Video video);

}

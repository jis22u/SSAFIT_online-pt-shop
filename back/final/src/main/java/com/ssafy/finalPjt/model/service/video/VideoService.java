package com.ssafy.finalPjt.model.service.video;

import java.util.List;

import com.ssafy.finalPjt.model.dto.Video;
import com.ssafy.finalPjt.model.dto.VideoSearchCondition;

public interface VideoService {

	// 한개 조회
	Video readVideo(String id);

	// 카테고리 + 검색
	List<Video> findVideoList(VideoSearchCondition condition);

	// 조회수 증가
	void viewCntup(String id);

	// 구매순으로 영상 리스트 검색
	List<Video> getVideoByBuyCnt();

	// 최신순으로 영상 리스트 검색
	List<Video> getVideoByReg();

	// 사용자 운동목적에 맞게 영상 리스트 검색
	List<Video> getVideoByPurpose(String input);

	// 운동 영상 업로드
	int registVideo(Video video);
}

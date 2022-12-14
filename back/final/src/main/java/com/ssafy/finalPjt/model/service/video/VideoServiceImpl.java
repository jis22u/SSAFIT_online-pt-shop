package com.ssafy.finalPjt.model.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalPjt.model.dao.VideoDao;
import com.ssafy.finalPjt.model.dto.Video;
import com.ssafy.finalPjt.model.dto.VideoSearchCondition;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoDao videoDao;

	@Override
	public Video readVideo(String id) {
		return videoDao.selectOne(id);
	}

	@Override
	public List<Video> findVideoList(VideoSearchCondition condition) {
		System.out.println("serv : " + condition);
		// System.out.println(videoDao.getVideoList(condition));
		return videoDao.getVideoList(condition);
	}

	@Override
	public void viewCntup(String id) {
		videoDao.updateCnt(id);
	}

	@Override
	public List<Video> getVideoByBuyCnt() {
		return videoDao.selectVideoByBuyCnt();
	}

	@Override
	public List<Video> getVideoByReg() {
		return videoDao.selectVideoByReg();
	}

	@Override
	public List<Video> getVideoByPurpose(String input) {
		return videoDao.selectVideoByPurpose(input);
	}

	@Override
	public int registVideo(Video video) {
		return videoDao.insertVideo(video);
	}

}

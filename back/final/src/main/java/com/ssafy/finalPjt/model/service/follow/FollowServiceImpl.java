package com.ssafy.finalPjt.model.service.follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalPjt.model.dao.FollowDao;
import com.ssafy.finalPjt.model.dto.Follow;
import com.ssafy.finalPjt.model.dto.User;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowDao followDao;
	
	
	@Override
	public int doFollow(Follow follow) {
		return followDao.insert(follow);
	}

	@Override
	public int unFollow(Follow follow) {
		return followDao.delete(follow);
	}

	@Override
	public List<User> getFollowList(Follow follow) {
		return followDao.selectUser(follow);
	}

	@Override
	public List<User> getFollowTrainerList(String userId) {
		return followDao.selectFollowTrainer(userId);
	}

}

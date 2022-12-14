package com.ssafy.finalPjt.model.service.favorite;

import java.util.List;

import com.ssafy.finalPjt.model.dto.Favorite;
import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.dto.Video;

public interface FavoriteService {

	// 좋아요 증가
	boolean like(Favorite favorite);

	boolean disLike(Favorite favorite);

	List<User> likePeople(Favorite favorite);

	List<Video> getVideoList(String u_id);
}

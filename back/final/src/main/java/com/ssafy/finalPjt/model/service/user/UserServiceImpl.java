package com.ssafy.finalPjt.model.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalPjt.model.dao.UserDao;
import com.ssafy.finalPjt.model.dto.User;
import com.ssafy.finalPjt.model.dto.Video;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean registUser(User user) {
		System.out.println(user.toString());
		return userDao.createUser(user) == 1;
	}

	@Override
	public String loginUser(String id, String pw) {

		// sha512 암호화

		// -------
		System.out.println(id);
		User found_user = userDao.findUser(id);
		System.out.println(found_user);

		if (found_user == null)
			return "fail";
		else {
			String DB_pw = found_user.getPw();
			System.out.println(DB_pw.equals(pw));
			if (DB_pw.equals(pw))
				return "success";
			else
				return "wrong";
		}
	}

	// LEFT JOIN
	@Override
	public User datailUser(String id) {
		return userDao.findUser(id);
	}

	@Override
	public void dropUser(String id) {
		System.out.println(id);
		userDao.deleteUser(id);
	}

	@Override
	public boolean modifyUser(User user) {
		return userDao.updateUser(user) == 1;
	}

	@Override
	public boolean checkId(String id) {
		return (0 == userDao.chkUserID(id));
	}

	@Override
	public boolean checkEmail(String email) {
		return 0 == userDao.chkEmail(email);
	}

	@Override
	public boolean checkNickname(String nickname) {
		return 0 == userDao.chkNickname(nickname);
	}

	@Override
	public boolean checkPhone(String phone_no) {
		return 0 == userDao.chkPhoneNo(phone_no);
	};

	@Override
	public List<User> getUserListById(String id) {
		return userDao.selectUserListById(id);
	}

	@Override
	public List<User> getUserListByNickname(String nickname) {
		return userDao.selectUserListByNickname(nickname);
	}

	@Override
	public List<Video> getBuyListById(String u_id) {
		return userDao.selectBuyVideo(u_id);
	}


	@Override
	public List<String> getUserPurpose(String id) {
		return userDao.selectUserPurpose(id);
	}

	@Override
	public List<Map<String, Object>> getPurposeList(String u_id) {
		System.out.println("지금 이것 바로 : " + u_id);
		return userDao.selectPurpose(u_id);
	}

	@Override
	public int addPurpose(Map<String, Object> map) {
		for (Map result : userDao.selectPurpose((String) map.get("u_id"))) {
			if ((int) result.get("p_id") == (int) map.get("p_id")) {
				return userDao.deletePurpose(map);
			}
			;
		}

		return userDao.insertPurpose(map);
	}

}

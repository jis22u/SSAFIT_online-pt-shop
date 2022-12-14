package com.ssafy.finalPjt.model.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalPjt.model.dao.CommentDao;
import com.ssafy.finalPjt.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentDao commentDao;
	
	@Override
	public int registComment(Comment comment) {
		return commentDao.insertComment(comment);
	}

	@Override
	public List<Comment> getCommentList(Comment comment) {
		return commentDao.selectCommentList(comment);
	}
	
	@Override
	public Comment getComment(int c_id) {
		return commentDao.selectOne(c_id) ;
	}

	@Override
	public int modifyComment(Comment comment) {
		return commentDao.updateComment(comment);
	}

	@Override
	public int removeComment(int c_id) {
		return commentDao.deleteComment(c_id);
	}

}

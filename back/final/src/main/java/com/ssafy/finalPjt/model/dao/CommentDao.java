package com.ssafy.finalPjt.model.dao;

import java.util.List;

import com.ssafy.finalPjt.model.dto.Comment;

public interface CommentDao {

	// 댓글 작성
	int insertComment(Comment comment);
	
//	// 1) 비디오별 조회
//	List<Comment> selectByVideo(String v_id);
//	
//	// 2) 개인별 조회
//	List<Comment> selectByUser(String u_id);
	
	// 위의  1),2)를 합침
	List<Comment> selectCommentList(Comment comment);
	
	// 코멘트 1개 조회
	Comment selectOne(int c_id);
	
	// 댓글 수정
	// 수정 : content, reg_date(현재시각으로)
	int updateComment(Comment comment);
	
	// 댓글 삭제
	int deleteComment(int c_id);
	
}

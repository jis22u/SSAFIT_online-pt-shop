package com.ssafy.finalPjt.model.service.comment;

import java.util.List;

import com.ssafy.finalPjt.model.dto.Comment;

public interface CommentService {
	
	// 댓글 작성
	int registComment(Comment comment);
	
	// 조회
	List<Comment> getCommentList(Comment comment);
	
	// 1개만 조회
	Comment getComment(int c_id);
	
	// 댓글 수정
	int modifyComment(Comment comment);
	
	// 댓글 삭제
	int removeComment(int c_id);
	
	
}

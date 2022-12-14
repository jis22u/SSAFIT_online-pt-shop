package com.ssafy.finalPjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.finalPjt.model.dto.Comment;
import com.ssafy.finalPjt.model.service.comment.CommentService;

@RestController
@RequestMapping("/api/commentApi")
public class CommentRestController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CommentService commentService;

	// 댓글 작성
	@PostMapping("/")
	public ResponseEntity<String> regist(Comment comment) {
		int result = commentService.registComment(comment);

		if (result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	// 댓글 리스트 조회 
	// 1) 비디오별 조회 2) 개인별 조회
	@GetMapping("/")
	public ResponseEntity<List<Comment>> selectList(String v_id, String u_id) {
		Comment comment = new Comment();
		comment.setV_id(v_id);
		comment.setU_id(u_id);
		
		return new ResponseEntity<List<Comment>>(commentService.getCommentList(comment), HttpStatus.OK);
	}
	
	// 댓글 1개 조회
	@GetMapping("/{c_id}")
	public ResponseEntity<Comment> selectOne(@PathVariable int c_id) {
		return new ResponseEntity<Comment>(commentService.getComment(c_id), HttpStatus.OK);
	}
	
	
	// 댓글 수정
	@PutMapping("/")
	public ResponseEntity<String> modify(Comment comment) {
		int result = commentService.modifyComment(comment);

		if (result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	
	// 댓글 삭제
	@DeleteMapping("/{c_id}")
	public ResponseEntity<String> delete(@PathVariable int c_id) {
		int result = commentService.removeComment(c_id);

		if (result == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}

package com.ssafy.finalPjt.model.dto;

public class Comment {
	private int c_id;			// PK, autoincrement
	private String u_id;		// FK
	private String nickname;	// 프론트단에 닉네임을 띄워주기 위해 필요
	private String v_id;		// FK
	private String content;		// 리뷰내용
	private String reg_date;	// 등록일자 
	
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getV_id() {
		return v_id;
	}
	public void setV_id(String v_id) {
		this.v_id = v_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "Comment [c_id=" + c_id + ", u_id=" + u_id + ", nickname=" + nickname + ", v_id=" + v_id + ", content="
				+ content + ", reg_date=" + reg_date + "]";
	}
	
}

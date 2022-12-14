package com.ssafy.finalPjt.model.dto;

public class Favorite {
	
	private String u_id;		// 복합키
	private String v_id;		// 복합키
	
	
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
	@Override
	public String toString() {
		return "Favorite [u_id=" + u_id + ", v_id=" + v_id + "]";
	}

	

	
}

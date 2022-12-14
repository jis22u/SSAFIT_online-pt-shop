package com.ssafy.finalPjt.model.dto;

public class Follow {
	private String from_user;	// u_id
	private String to_user;		// u_id
	
	public String getFrom_user() {
		return from_user;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public String getTo_user() {
		return to_user;
	}
	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}
	
	@Override
	public String toString() {
		return "Follow [from_user=" + from_user + ", to_user=" + to_user + "]";
	}	
	
}

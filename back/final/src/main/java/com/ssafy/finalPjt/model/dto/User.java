package com.ssafy.finalPjt.model.dto;

public class User {

	// 일반사용자
	private String u_id; // PK
	private String pw;
	private String name;
	private String email;
	private String gender;
	private String phone_no;
	private String nickname;
	private String profile;

	// 트레이너
	private String t_name;
	private int career;
	private String t_content;
	private String youtube;
	private String insta;

	// 일반사용자 Setter&Getter
	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	// 트레이너 Setter & Getter
	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public int getCareer() {
		return career;
	}

	public void setCareer(int career) {
		this.career = career;
	}

	public String getT_content() {
		return t_content;
	}

	public void setT_content(String t_content) {
		this.t_content = t_content;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getInsta() {
		return insta;
	}

	public void setInsta(String insta) {
		this.insta = insta;
	}

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", gender=" + gender
				+ ", phone_no=" + phone_no + ", nickname=" + nickname + ", profile=" + profile + ", t_name=" + t_name
				+ ", career=" + career + ", t_content=" + t_content + ", youtube=" + youtube + ", insta=" + insta + "]";
	}

}

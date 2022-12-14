package com.ssafy.finalPjt.model.dto;

public class Video {
	private String v_id; // PK
	private String title;
	private String t_name;
	private String reg_date;
	private int view_cnt;
	private int favorite_cnt;
	private String category;
	private int price;

	public String getV_id() {
		return v_id;
	}

	public void setV_id(String v_id) {
		this.v_id = v_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public int getFavorite_cnt() {
		return favorite_cnt;
	}

	public void setFavorite_cnt(int favorite_cnt) {
		this.favorite_cnt = favorite_cnt;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Video [v_id=" + v_id + ", title=" + title + ", t_name=" + t_name + ", reg_date=" + reg_date
				+ ", view_cnt=" + view_cnt + ", favorite_cnt=" + favorite_cnt + ", category=" + category + ", price="
				+ price + "]";
	}

}

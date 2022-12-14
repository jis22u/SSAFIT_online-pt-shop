package com.ssafy.finalPjt.model.dto;

import java.util.Arrays;

public class VideoSearchCondition {
	private String[] category;
	private String[] range;
	private String keyword;
	
	public VideoSearchCondition() {};
	
	public VideoSearchCondition(String[] category, String[] range, String keyword) {
		super();
		this.category = category;
		this.range = range;
		this.keyword = keyword;
	}
	
	public String[] getCategory() {
		return category;
	}
	public void setCategory(String[] category) {
		this.category = category;
	}
	public String[] getRange() {
		return range;
	}
	public void setRange(String[] range) {
		this.range = range;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "VideoSearchCondition [category=" + Arrays.toString(category) + ", range=" + Arrays.toString(range)
				+ ", keyword=" + keyword + "]";
	}
}

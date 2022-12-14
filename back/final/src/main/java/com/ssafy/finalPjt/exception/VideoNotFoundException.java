package com.ssafy.finalPjt.exception;

public class VideoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 539738256344484418L;

	public VideoNotFoundException(String msg) {
		super(msg);
	};
}

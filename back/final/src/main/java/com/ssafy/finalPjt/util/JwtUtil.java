package com.ssafy.finalPjt.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static final String SALT = "SSA_FIT";
	
	//토큰 생성 
	public String createToken(String claimId,String data) throws UnsupportedEncodingException {
		return Jwts.builder()
				.setHeaderParam("algorithmn","HS256")
				.setHeaderParam("type", "JWT")
				.setExpiration(new Date(System.currentTimeMillis()+3600000))
				.claim(claimId,data)
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
				.compact();
	}
	
	//유효성 검사
	public void valid(String token) throws Exception{
		Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
	}
	
}


package com.ssafy.happyhouse.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.happyhouse.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	
	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
	
	private static final String SECRET_KEY = "SSAFY_happyhouse_10";
	private static final int EXPIRE_DURATION_MILLIS= 1000 * 60 * 60;
	private static final Map<String, Object> headers = new HashMap<>();
	

	public JwtServiceImpl() {
		headers.put("typ", "JWT");
		headers.put("alg", "HS256");
	}

	// 새로운 토큰 생성
	@Override
	public <T> String create(String key, T data, String subject) {
		Map<String, Object> payloads = new HashMap<>();
		payloads.put(key, data);
		
		long currentTimeMillis = System.currentTimeMillis();
		Date expiredMillis = new Date(currentTimeMillis + EXPIRE_DURATION_MILLIS);
		
		String jwt = Jwts.builder()
				.setHeader(headers)
				.setClaims(payloads)
				.setExpiration(expiredMillis)
				.setSubject(subject)
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact();
		return jwt;
	}
	
	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SECRET_KEY.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}

		return key;
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UnauthorizedException();
		}
		Map<String, Object> value = claims.getBody();
		logger.info("value : {}", value);
		return value;
	}

	@Override
	public String getUserId() {
		return (String) this.get("user").get("userid");
	}

	// 토큰 유효성 확인
	@Override
	public boolean isUsable(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

}

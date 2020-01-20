package com.abhi.productManagement.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.abhi.productManagement.domain.UserInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JWtUtils {
	
	public String extractUserName(String token){
		return extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token){
		return extractClaims(token, Claims::getExpiration);
	}

	private <T> T extractClaims(String token, Function <Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		
		return Jwts.parser().setSigningKey(WebAppConstant.JWT_SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public static String generateToken(UserInfo userInfo) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userInfo.getUsername() );
	}

	private static String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis())).
		setExpiration(new Date(System.currentTimeMillis()+6000*10*5)).signWith(SignatureAlgorithm.HS512, WebAppConstant.JWT_SECRET_KEY).compact();
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return ( userName.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
		
	}
	

	public static void main(String[] args) {
		String password = "admin@123";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("Encoded password is " + passwordEncoder.encode(password));
	}

	public static String getUserIdFromJWT(String token) {
		//String plainToken = getPlainToken(token);
		Claims claims = Jwts.parser().setSigningKey(WebAppConstant.JWT_SECRET_KEY).parseClaimsJws(token).getBody();
		log.info("Subject:"+claims.getSubject());
				
		return claims.getSubject();
	}
	
	public static String getPlainToken(String bearerToken) {

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
	

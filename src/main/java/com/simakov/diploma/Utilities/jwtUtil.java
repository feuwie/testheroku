package com.simakov.diploma.Utilities;

import java.util.HashMap;
import java.util.Map;

import com.simakov.diploma.Model.User;
import com.simakov.diploma.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class jwtUtil {

	private final static String Key = "axWDVrgnYJil";
	private final static String ISSUER = "ADMIN_SHOPPING";
	private final static String SUBJECT = "USER_SHOPPING";
	private final static String SES_PHONE = "SESSION_PHONE";
	private final static String SES_PASS = "SESSION_PASS";
	private final static String SES_TYPE = "SESSION_TYPE";
	private final static String SES_BAN = "SESSION_BAN";

	@Autowired
	private UserRepository userRepo;

	// public String createToken(String session_phone, String session_pass, String
	// session_type) {
	// Map<String, Object> map = new HashMap<>();
	// map.put(SES_PHONE, session_phone);
	// map.put(SES_PASS, session_pass);
	// map.put(SES_TYPE, session_type);

	// SignatureAlgorithm signAlg = SignatureAlgorithm.HS256;
	// String br =
	// Jwts.builder().setIssuer(ISSUER).setClaims(map).setSubject(SUBJECT).signWith(signAlg,
	// Key)
	// .compact();

	// return br;
	// }

	// public User checkToken(String token) {
	// try {
	// Claims claim =
	// Jwts.parser().setSigningKey(Key).parseClaimsJws(token).getBody();
	// User user =
	// userRepo.findByPhoneAndPasswordAndUsertype(claim.get(SES_PHONE).toString(),
	// claim.get(SES_PASS).toString(), claim.get(SES_TYPE).toString());
	// return user;
	// } catch (Exception err) {
	// return null;
	// }
	// }
	public String createToken(String session_phone, String session_pass, String session_type, int session_ban) {
		Map<String, Object> map = new HashMap<>();
		map.put(SES_PHONE, session_phone);
		map.put(SES_PASS, session_pass);
		map.put(SES_TYPE, session_type);
		map.put(SES_BAN, session_ban);

		SignatureAlgorithm signAlg = SignatureAlgorithm.HS256;
		String br = Jwts.builder().setIssuer(ISSUER).setClaims(map).setSubject(SUBJECT).signWith(signAlg, Key)
				.compact();
		return br;
	}

	public User checkToken(String token) {
		try {
			Claims claim = Jwts.parser().setSigningKey(Key).parseClaimsJws(token).getBody();
			User user = userRepo.findByPhoneAndPasswordAndUsertypeAndBan(claim.get(SES_PHONE).toString(),
					claim.get(SES_PASS).toString(), claim.get(SES_TYPE).toString(), (Integer) claim.get(SES_BAN));
			return user;
		} catch (Exception err) {
			return null;
		}
	}

}
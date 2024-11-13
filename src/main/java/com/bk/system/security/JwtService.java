package com.bk.system.security;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bk.system.common.Mapper;
import com.bk.system.model.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {
	
	@Value("${jwt.secret}")
    private String secret;
	
	@Autowired
	Mapper mapper;

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(jwtToken).getPayload();
    }

    private SecretKey getSigningKey() {
        byte [] bytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    public String generateToken(User u) {
        return createToken(u.getEmail());
    }

    private String createToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigningKey())
                .compact();
    }
    
    public String extractEmailFromToken(String jwttoken) throws JsonMappingException, JsonProcessingException {
    	 String token = jwttoken.replace("Bearer ", "");
    	 String[] bytes = token.split("\\.");
    	 String payload  = new String(Base64.getUrlDecoder().decode(bytes[1]));
    	 ObjectMapper mapper = new ObjectMapper();
         Map<String, Object> payloadMap = mapper.readValue(payload, Map.class);
         String sub = (String) payloadMap.get("sub");
		return sub;
        

}
}

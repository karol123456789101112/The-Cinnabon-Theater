//package org.example.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.example.domain.AppUserRole;
//import org.springframework.stereotype.Component;
//import io.jsonwebtoken.security.Keys;
//import java.security.Key;
//
//import java.util.Date;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.example.domain.AppUser;
//
//@Component
//public class JwtUtil {
//    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 godzina
//
//    public String generateToken(AppUser user) {
//
//        Set<String> roles = user.getAppUserRole()
//                .stream()
//                .map(AppUserRole::getRole)
//                .collect(Collectors.toSet());
//
//        return Jwts.builder()
//                .setSubject(user.getEmail())
//                .claim("roles", roles)
//                .claim("userId", user.getId())
//                .claim("firstName", user.getFirstName())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
//    public Long getUserIdFromToken(String token) {
//        Claims claims = getClaims(token);
//        return claims.get("userId", Long.class);
//    }
//
//    public String extractUsername(String token) {
//        return getClaims(token).getSubject();
//    }
//
//    public boolean validateToken(String token, String username) {
//        return extractUsername(token).equals(username) && !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return getClaims(token).getExpiration().before(new Date());
//    }
//
//    private Claims getClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//    public String getRoleFromToken(String token) {
//        Claims claims = getClaims(token);
//        return claims.get("role", String.class);
//    }
//}
//

package com.liuxin.spring_boot_blog.admin.utils;

import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxin
 * @date 2019/7/5 15:44
 * @desc
 */
@Slf4j
public class JWTUtil {
    private static final String SECRET_KEY = "f9251e38-7f92-469a-8c04-7c8d2f9a7edc";

    public static String encode(String userId) {
        Integer ept = 10080;  // 一周
        return JWTUtil.encode(userId, ept);
    }

    // 加密Token
    public static String encode(String userId, Integer exceptionTime) {
        Map<String, Object> claims = new HashMap<>();
        long nowMillis = System.currentTimeMillis();
        long expirationMillis = nowMillis + exceptionTime * 60000L;
        claims.put("userId", userId);
        return Jwts.builder()
                .setSubject("subValue")
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expirationMillis))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    // 解密Token
    public static String decode(String accessToken) throws GlobalException {
        log.info("Token 开始解密");
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(accessToken).getBody();
            return (String) claims.get("userId");
        } catch (Exception e) {  // 解密失败，返回null
            return null;
        }
    }
}

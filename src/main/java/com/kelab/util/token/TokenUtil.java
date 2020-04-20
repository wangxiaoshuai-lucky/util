package com.kelab.util.token;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class TokenUtil {

    public static String tokens(Map<String, Object> claims, String secretKey, int millisecond, String jwtIssuer, String jwtAud) {

        //获取当前的时间
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        //向后退后秒数
        calendar.add(Calendar.MILLISECOND, millisecond);
        Date endTime = calendar.getTime();
        JwtBuilder builder = Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(endTime)
                .setIssuer(jwtIssuer)
                .setAudience(jwtAud);
        return builder.compact();
    }

    public static Object tokenValueOf(String jwt, String secretKey, String key) {
        Claims claims = parse(jwt, secretKey);
        if (claims == null || claims.get(key) == null)
            return null;
        return claims.get(key);
    }

    public static Claims parse(String jwt, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt).getBody();
    }
}

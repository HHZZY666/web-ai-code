package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {


    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
         dataMap.put("username", "admin");

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==") //指定加密算法，密钥
                .addClaims(dataMap) //添加自定义信息
                .setExpiration(new Date( System.currentTimeMillis() + 30 * 1000)) //设置过期时间
                .compact(); //生成令牌
         System.out.println(jwt);

    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void  testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0ODc0NTUxNX0.3cVRe036tqCmj7SzC19gFMUOeQdj4PrNx0sV4yQqFu4";
        Claims claims = Jwts.parser()
                .setSigningKey("aXRoZWltYQ==")
                .parseClaimsJws(token)
                .getBody();
        System.out.println( claims);
    }
}




















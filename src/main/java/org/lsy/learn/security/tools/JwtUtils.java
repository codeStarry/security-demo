package org.lsy.learn.security.tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;

@Slf4j
public class JwtUtils {

    private JwtUtils() {}

    private static final String JWT_KEY = "ZHeJIANg01HANGZhouXULIN";
    private static final String USER_ID = "id";

    /**
     * 采用Base64加密密钥
     *
     * @return
     */
    public static SecretKey generalKey() {
        String strkey = JWT_KEY;
        //本地的密码解码
        byte[] encodeKey = encodeBase64(strkey.getBytes());
        //根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        return key;
    }

    /**
     * 生成Token
     *
     * @param id
     * @return
     */
    public static String createToken(Long id) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey key = generalKey();
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = Jwts.builder()
                .setHeader(map)
                .setId(UUID.randomUUID().toString())
                .claim(USER_ID, id)
                .setIssuer(JWT_KEY)
                .setIssuedAt(new Date())
                .signWith(signatureAlgorithm, key).compact();
        return token;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Claims parsToken(String token) throws Exception{
        SecretKey key = generalKey();

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }


    /**
     * 获取用户ID
     * @param token
     * @return
     */
    public static Integer getUserId(String token) throws Exception{

        //try {
            Claims claims = parsToken(token);

            Integer userId = Integer.parseInt(claims.get(USER_ID).toString());

            return userId;
        /*} catch (Exception e) {

            log.info("token解析异常：{}", e.getMessage());
            throw new JwtTokenException("token被恶意程序篡改，请注意网络安全!");
        }*/
    }

    public static Integer getUser() throws Exception{
        HttpServletRequest request = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = request.getHeader("Authorizations");

        return getUserId(token);
    }
}

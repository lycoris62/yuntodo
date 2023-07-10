package me.yuntodo.global.config.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import me.yuntodo.domain.user.dao.UserRepository;
import me.yuntodo.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JwtProvider {

    private final Key key;
    private final JwtParser jwtParser;
    private static final int ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;

    private final UserRepository userRepository;

    public JwtProvider(@Value("${jwt.secret}") String secretKey, UserRepository userRepository) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        this.userRepository = userRepository;
    }

    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setHeader(createHeader())
                .setClaims(Map.of("username", username))
                .setExpiration(createExpiredDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = jwtParser.parseClaimsJws(accessToken).getBody();
        String username = (String) claims.get("username");

        User user = getUser(username);
        JwtUserDetails jwtUserDetails = new JwtUserDetails(user);

        return new UsernamePasswordAuthenticationToken(
                jwtUserDetails,
                user.getPassword(),
                List.of(user::getRole));
    }

    private User getUser(String username) {
        if (username == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("없는 유저"));
    }

    private static Map<String, Object> createHeader() {
        return Map.of(
                "typ", "JWT",
                "alg", "HS256"
        );
    }

    private static Date createExpiredDate() {
        Date ext = new Date();
        ext.setTime(ext.getTime() + ACCESS_TOKEN_EXPIRE_TIME);
        return ext;
    }
}

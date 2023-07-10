package me.yuntodo.domain.user.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yuntodo.domain.user.dao.UserRepository;
import me.yuntodo.domain.user.domain.User;
import me.yuntodo.domain.user.dto.LoginRequest;
import me.yuntodo.domain.user.dto.LoginResponse;
import me.yuntodo.global.config.security.jwt.JwtProvider;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("없는 유저"));

        String accessToken = jwtProvider.createToken(user.getUsername());
        log.info("login success : {}, accessToken : {}", request.getUsername(), accessToken);
        return new LoginResponse(accessToken);
    }
}

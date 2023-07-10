package me.yuntodo.domain.user.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yuntodo.domain.user.dao.UserRepository;
import me.yuntodo.domain.user.domain.User;
import me.yuntodo.domain.user.dto.SignUpRequest;
import me.yuntodo.global.common.response.SuccessResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SuccessResponse signup(SignUpRequest request) {

        validateUsername(request);
        validateConfirmPassword(request);

        String password = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getUsername(), password, "ROLE_USER");
        userRepository.save(user);

        log.info("signup success : {}", request.getUsername());
        return new SuccessResponse();
    }

    private void validateUsername(SignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("동일 유저네임 존재");
        }
    }

    private void validateConfirmPassword(SignUpRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("다른 확인 비밀번호");
        }
    }
}

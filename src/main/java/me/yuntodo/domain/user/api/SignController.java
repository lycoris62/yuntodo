package me.yuntodo.domain.user.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yuntodo.domain.user.application.LoginService;
import me.yuntodo.domain.user.application.SignUpService;
import me.yuntodo.domain.user.dto.LoginRequest;
import me.yuntodo.domain.user.dto.LoginResponse;
import me.yuntodo.domain.user.dto.SignUpRequest;
import me.yuntodo.global.common.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignController {

    private final SignUpService signUpService;
    private final LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse> signup(@RequestBody @Valid SignUpRequest request) {
        SuccessResponse successResponse = signUpService.signup(request);
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse loginResponse = loginService.login(request);
        return ResponseEntity.ok(loginResponse);
    }
}

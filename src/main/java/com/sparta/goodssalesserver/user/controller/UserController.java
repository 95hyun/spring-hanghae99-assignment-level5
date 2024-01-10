package com.sparta.goodssalesserver.user.controller;

import com.sparta.goodssalesserver.user.dto.SignupRequestDto;
import com.sparta.goodssalesserver.user.dto.SignupResponseDto;
import com.sparta.goodssalesserver.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public SignupResponseDto signup(
            @Valid @RequestBody SignupRequestDto requestDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            throw new IllegalArgumentException("유효한 형식이 아니어서 회원가입에 실패하였습니다.");
        }
        return userService.signup(requestDto);
    }
}

package com.sparta.week05miniprojectbackend.controller;


import com.sparta.week05miniprojectbackend.dto.requestDto.IdCheckRequestDto;
import com.sparta.week05miniprojectbackend.dto.requestDto.LoginRequestDto;
import com.sparta.week05miniprojectbackend.dto.requestDto.UserRequestDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.ResponseDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.UserResponseDto;
import com.sparta.week05miniprojectbackend.jwt.util.JwtUtil;
import com.sparta.week05miniprojectbackend.security.user.UserDetailsImpl;
import com.sparta.week05miniprojectbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    // 입력값 @RequestBody 로 받을 것인지 다시 한번 확인!
    // @Valid 값 컨펌받기!
    @PostMapping("/user/signup")
    public ResponseDto<?> signup(@RequestBody @Valid UserRequestDto userRequestDto){
        return userService.signup(userRequestDto);
    }

    @PostMapping("/user/idCheck")
    public ResponseDto<?> idCheck(@RequestBody @Valid IdCheckRequestDto idCheckRequestDto){
        return userService.idCheck(idCheckRequestDto);
    }

    // login Dto 를 따로 만들지 UserRequestDto 로 같이 쓸지????????????
//    @PostMapping("/user/login")
//    public ResponseEntity<ResponseDto<UserResponseDto>> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response){
//        return userService.login(loginRequestDto, response);
//    }

//    @PostMapping("/user/login")
//    public ResponseDto<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response){
////        System.out.println(userService.login(loginRequestDto, response));
//        return userService.login(loginRequestDto, response);
//    }


    @PostMapping("/user/login")
    public ResponseDto<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response){
//        System.out.println(userService.login(loginRequestDto, response));
        return userService.login(loginRequestDto, response);
    }

//    @PostMapping
//    public HttpHeaders setHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
//        return headers;
//    }

//    @GetMapping("/issue/token")
//    public UserResponseDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
//        response.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userDetails.getUser().getUserId(), "Access"));
//        return new UserResponseDto("Success IssuedToken", HttpStatus.OK.value());
//    }
}



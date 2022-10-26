package com.sparta.week05miniprojectbackend.dto.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Getter
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordCheck;
    @NotBlank
    private String nickName;


    public UserRequestDto(String userId) {
        this.userId = userId;
    }

    public UserRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UserRequestDto(String userId, String password, String passwordCheck, String nickName) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.nickName = nickName;
    }

    // 인코딩된 패스워드 값을 password 에 입력
    public void setEncodePwd(String encodePwd) {
        this.password = encodePwd;
    }
}

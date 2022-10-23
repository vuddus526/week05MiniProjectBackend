package com.sparta.week05miniprojectbackend.Util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class SecurityUtil{  //현제 로그인한 사람의 아이디를 가지고 올수있다.

    public static Long getCurrentUserId(){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null){
            throw new RuntimeException("인증 정보가 존재하지 않습니다.");
        }

        return Long.parseLong(authentication.getName());
    }

}

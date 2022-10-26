package com.sparta.week05miniprojectbackend.dto.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class IdCheckRequestDto {
    private String userId;

    public IdCheckRequestDto(String userId) {
        this.userId = userId;
    }
}

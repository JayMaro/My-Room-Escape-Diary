package com.maro.roomescapediary.dto;

import com.maro.roomescapediary.enums.JwtCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtDto {

    private String jwt;
    private String jti;
    private JwtCode jwtCode;

}

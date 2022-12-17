package com.maro.roomescapediary.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtCode {
    ACCESS_TOKEN("MashiMaro", 60 * 60 * 24 * 365 * 1L)
    , REFRESH_TOKEN("MaroMashi", 60 * 60 * 24 * 365 * 2L)
    ;

    private final String key;
    private final long time;
}

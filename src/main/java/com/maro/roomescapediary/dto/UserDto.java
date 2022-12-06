package com.maro.roomescapediary.dto;

import com.maro.roomescapediary.entity.Users;
import com.maro.roomescapediary.enums.JoinCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

    private int seq;

    private JoinCode joinCode;

    private String id;

    private String password;

    private String nickName;

    private boolean useFlag;

    public Users toEntity() {
        return Users.builder()
            .joinCode(joinCode)
            .id(id)
            .password(password)
            .nickName(nickName)
            .build();
    }
}

package com.maro.roomescapediary.dto;

import com.maro.roomescapediary.entity.Users;
import com.maro.roomescapediary.enums.JoinCode;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
public class UserDto {

    private int seq;

    private JoinCode joinCode;
    @NotBlank @Length(max = 100)
    private String id;
    @NotBlank
    private String password;
    @NotBlank @Length(max = 50)
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

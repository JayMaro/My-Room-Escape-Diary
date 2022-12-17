package com.maro.roomescapediary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @NotBlank
    private JoinCode joinCode;
    @NotBlank @Length(max = 100)
    private String id;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String salt;
    @NotBlank @Length(max = 50)
    private String nickName;

    private boolean useFlag;

    public Users toEntity() {
        return Users.builder()
            .joinCode(joinCode)
            .id(id)
            .password(password)
            .salt(salt)
            .nickName(nickName)
            .build();
    }
}

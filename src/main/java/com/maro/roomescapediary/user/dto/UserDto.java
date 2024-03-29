package com.maro.roomescapediary.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maro.roomescapediary.user.entity.Authority;
import com.maro.roomescapediary.user.entity.Users;
import com.maro.roomescapediary.user.enums.JoinCode;
import java.util.Set;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @NotBlank @Length(max = 50)
    private String nickName;

    private Set<Authority> authorities;

    private boolean useFlag;

    public Users toEntity() {
        return Users.builder()
            .joinCode(joinCode)
            .id(id)
            .password(password)
            .nickName(nickName)
            .authority(authorities)
            .build();
    }
}

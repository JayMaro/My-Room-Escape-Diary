package com.maro.roomescapediary.user.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class LoginDto {

    @NotBlank
    @Length(max = 100)
    private String id;
    @NotBlank
    private String password;

}

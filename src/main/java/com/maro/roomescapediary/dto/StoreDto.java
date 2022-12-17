package com.maro.roomescapediary.dto;

import com.maro.roomescapediary.entity.Store;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class StoreDto {

    private int seq;
    @NotBlank @Length(max = 100)
    private String address;
    @NotBlank @Length(max = 50)
    private String name;
    @NotBlank @Length(max = 50)
    private String branchName;
    @NotBlank @Length(max = 500)
    private String url;

    private boolean useFlag;

    public Store toEntity() {
        return Store.builder()
            .address(address)
            .name(name)
            .branchName(branchName)
            .url(url)
            .build();
    }
}

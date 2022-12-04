package com.maro.roomescapediary.dto;

import com.maro.roomescapediary.entity.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoreDto {

    private Integer seq;

    private String address;

    private String name;

    private String branchName;

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

package com.maro.roomescapediary.dto;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreSearchDto implements SearchDto{

    private String name;

    private String branchName;

    @Override
    public void checkAndSetValues() {
        if (Objects.isNull(name)) {
            name = "";
        }
        if (Objects.isNull(branchName)) {
            branchName = "";
        }
    }

}

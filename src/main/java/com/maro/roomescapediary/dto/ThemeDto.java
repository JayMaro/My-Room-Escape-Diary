package com.maro.roomescapediary.dto;

import com.maro.roomescapediary.entity.Store;
import com.maro.roomescapediary.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ThemeDto {

    private int seq;

    private int storeSeq;

    private String name;

    private int price;

    private double difficultyRating;

    private String desc;

    private String url;

    private boolean useFlag;

    public Theme toEntity(Store store) {
        return Theme.builder()
            .store(store)
            .name(name)
            .price(price)
            .difficultyRating(difficultyRating)
            .desc(desc)
            .url(url)
            .build();
    }
}

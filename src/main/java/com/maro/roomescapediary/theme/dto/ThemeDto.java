package com.maro.roomescapediary.theme.dto;

import com.maro.roomescapediary.store.entity.Store;
import com.maro.roomescapediary.theme.entity.Theme;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ThemeDto {

    private int seq;
    @Positive
    private int storeSeq;
    @NotBlank @Length(max = 100)
    private String name;
    @Positive
    private int price;
    @Positive @Digits(integer = 1, fraction = 1) @Max(5)
    private double difficultyRating;
    @NotBlank
    private String desc;
    @NotBlank @Length(max = 500)
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

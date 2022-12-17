package com.maro.roomescapediary.dto;


import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ThemeSearchDto implements SearchDto{

    private String name;

    private Integer highPrice;

    private Integer lowPrice;

    private Double highRating;

    private Double lowRating;

    @Override
    public void checkAndSetValues() {
        if (Objects.isNull(name)) {
            name = "";
        }
        if (Objects.isNull(highPrice)) {
            highPrice = 1_000_000;
        }
        if (Objects.isNull(lowPrice)) {
            lowPrice = 0;
        }
        if (Objects.isNull(highRating)) {
            highRating = 5D;
        }
        if (Objects.isNull(lowRating)) {
            lowRating = 0D;
        }
    }
}

package com.maro.roomescapediary.review.dto;


import com.maro.roomescapediary.common.dto.SearchDto;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@AllArgsConstructor
public class ReviewSearchDto implements SearchDto {

    private String themeName;

    private String reviewTitle;

    private Integer memberCount;

    private Boolean successFlag;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate endDate;

    private Double reviewRating;

    @Override
    public void checkAndSetValues() {
        if (Objects.isNull(themeName)) {
            themeName = "";
        }
        if (Objects.isNull(reviewTitle)) {
            reviewTitle = "";
        }
    }
}

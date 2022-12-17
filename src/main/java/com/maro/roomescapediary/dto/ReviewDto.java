package com.maro.roomescapediary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maro.roomescapediary.entity.Review;
import com.maro.roomescapediary.entity.Theme;
import com.maro.roomescapediary.entity.Users;
import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class ReviewDto {

    private int seq;
    @Positive
    private int themeSeq;
    @Positive
    private int userSeq;
    @NotBlank @Length(max = 100)
    private String title;
    @Positive
    private int memberCount;

    private boolean successFlag;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate visitDate;
    @Positive @Max(5)
    private double reviewRating;
    @Positive @Max(5)
    private double difficultyRating;
    @Positive @Max(5)
    private double activityRating;
    @Positive @Max(5)
    private double fearRating;
    @NotBlank
    private String memo;

    private boolean useFlag;

    public Review toEntity(Theme theme, Users user) {
        return Review.builder()
            .theme(theme)
            .user(user)
            .title(title)
            .memberCount(memberCount)
            .successFlag(successFlag)
            .visitDate(visitDate)
            .reviewRating(reviewRating)
            .difficultyRating(difficultyRating)
            .activityRating(activityRating)
            .fearRating(fearRating)
            .memo(memo)
            .build();
    }

}

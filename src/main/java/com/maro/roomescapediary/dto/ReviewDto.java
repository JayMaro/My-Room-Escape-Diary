package com.maro.roomescapediary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maro.roomescapediary.entity.Review;
import com.maro.roomescapediary.entity.Theme;
import com.maro.roomescapediary.entity.Users;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDto {

    private int seq;

    private int themeSeq;

    private int userSeq;

    private String title;

    private int memberCount;

    private boolean successFlag;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate visitDate;

    private double reviewRating;

    private double difficultyRating;

    private double activityRating;

    private double fearRating;

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

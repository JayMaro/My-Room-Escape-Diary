package com.maro.roomescapediary.entity;

import com.maro.roomescapediary.dto.ReviewDto;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_seq")
    private Integer seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_seq")
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private Users user;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "member_count", nullable = false)
    private Integer memberCount;

    @Column(name = "success_flag", nullable = false)
    private Boolean successFlag;

    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    @Column(name = "review_rating", nullable = false)
    private Double reviewRating;

    @Column(name = "difficulty_rating", nullable = false)
    private Double difficultyRating;

    @Column(name = "activity_rating", nullable = false)
    private Double activityRating;

    @Column(name = "fear_rating", nullable = false)
    private Double fearRating;

    @Column(name = "memo")
    private String memo;

    @Builder
    public Review(Theme theme, Users user, String title, Integer memberCount, Boolean successFlag, LocalDate visitDate, Double reviewRating,
        Double difficultyRating, Double activityRating, Double fearRating, String memo) {
        this.theme = theme;
        this.user = user;
        this.title = title;
        this.memberCount = memberCount;
        this.successFlag = successFlag;
        this.visitDate = visitDate;
        this.reviewRating = reviewRating;
        this.difficultyRating = difficultyRating;
        this.activityRating = activityRating;
        this.fearRating = fearRating;
        this.memo = memo;
    }

    public ReviewDto toDto() {
        return ReviewDto.builder()
            .seq(seq)
            .themeSeq(theme.getSeq())
            .userSeq(user.getSeq())
            .title(title)
            .memberCount(memberCount)
            .successFlag(successFlag)
            .visitDate(visitDate)
            .reviewRating(reviewRating)
            .difficultyRating(difficultyRating)
            .activityRating(activityRating)
            .fearRating(fearRating)
            .memo(memo)
            .useFlag(getUseFlag())
            .build();
    }

    public void updateReview(ReviewDto reviewDto, Theme theme, Users user) {
        this.theme = theme;
        this.user = user;
        this.title = reviewDto.getTitle();
        this.memberCount = reviewDto.getMemberCount();
        this.successFlag = reviewDto.isSuccessFlag();
        this.visitDate = reviewDto.getVisitDate();
        this.reviewRating = reviewDto.getReviewRating();
        this.difficultyRating = reviewDto.getDifficultyRating();
        this.activityRating = reviewDto.getActivityRating();
        this.fearRating = reviewDto.getFearRating();
        this.memo = reviewDto.getMemo();
    }
}

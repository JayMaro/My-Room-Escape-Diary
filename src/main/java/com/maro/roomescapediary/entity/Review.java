package com.maro.roomescapediary.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theme")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_seq")
    private Integer seq;

    @Column(name = "theme_seq", nullable = false)
    private Integer themeSeq;

    @Column(name = "user_seq", nullable = false)
    private Integer userSeq;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "member_count", nullable = false)
    private Integer memberCount;

    @Column(name = "success_flag", nullable = false)
    private Boolean successFlag;

    @Column(name = "visit_date", nullable = false)
    private LocalDateTime visitDate;

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
}
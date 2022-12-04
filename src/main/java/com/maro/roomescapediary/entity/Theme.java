package com.maro.roomescapediary.entity;


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
public class Theme extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_seq")
    private Integer seq;

    @Column(name = "store_seq", nullable = false)
    private Integer storeSeq;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "difficulty_rating", nullable = false)
    private Double difficultyRating;

    @Column(name = "desc")
    private String desc;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

}

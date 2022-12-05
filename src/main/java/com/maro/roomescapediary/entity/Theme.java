package com.maro.roomescapediary.entity;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "theme")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theme extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_seq")
    private Integer seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_seq")
    private Store store;

    @OneToMany(mappedBy = "theme")
    private List<Review> reviews;

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

package com.maro.roomescapediary.entity;

import com.maro.roomescapediary.enums.TableCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_seq")
    private Integer seq;

    @Column(name = "type_seq", nullable = false)
    private Integer typeSeq;

    @Column(name = "table_code", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TableCode tableCode;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

}

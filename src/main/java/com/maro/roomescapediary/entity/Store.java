package com.maro.roomescapediary.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_seq")
    private Integer seq;

    @OneToMany(mappedBy = "store")
    private List<Theme> themes = new ArrayList<>();

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "branch_name", nullable = false, length = 50)
    private String branchName;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

}

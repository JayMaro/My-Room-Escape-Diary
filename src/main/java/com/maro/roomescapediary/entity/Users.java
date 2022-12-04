package com.maro.roomescapediary.entity;

import com.maro.roomescapediary.domain.common.JoinCode;
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
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Integer seq;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @Column(name = "join_code", nullable = false, length = 50)
    private JoinCode joinCode;

    @Column(name = "id", nullable = false, unique = true, length = 100)
    private String id;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "nick_name", nullable = false, unique = true, length = 50)
    private String nickName;
}

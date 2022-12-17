package com.maro.roomescapediary.user.entity;

import com.maro.roomescapediary.common.entity.BaseEntity;
import com.maro.roomescapediary.user.dto.UserDto;
import com.maro.roomescapediary.user.enums.JoinCode;
import com.maro.roomescapediary.review.entity.Review;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Integer seq;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "join_code", nullable = false, length = 50)
    private JoinCode joinCode;

    @Column(name = "id", nullable = false, unique = true, length = 100)
    private String id;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "salt", nullable = false, length = 100)
    private String salt;

    @Column(name = "nick_name", nullable = false, unique = true, length = 50)
    private String nickName;

    @Builder
    public Users(JoinCode joinCode, String id, String password, String salt, String nickName) {
        this.joinCode = joinCode;
        this.id = id;
        this.password = password;
        this.salt = salt;
        this.nickName = nickName;
    }

    public UserDto toDto() {
        return UserDto.builder()
            .seq(seq)
            .joinCode(joinCode)
            .id(id)
            .nickName(nickName)
            .useFlag(getUseFlag())
            .build();
    }

    public void updateUser(UserDto userDto) {
        this.joinCode = userDto.getJoinCode();
        this.id = userDto.getId();
        this.password = userDto.getPassword();
        this.salt = userDto.getSalt();
        this.nickName = userDto.getNickName();
    }

}

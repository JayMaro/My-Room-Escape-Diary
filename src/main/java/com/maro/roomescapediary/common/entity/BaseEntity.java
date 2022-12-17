package com.maro.roomescapediary.common.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(name = "reg_date", updatable = false, nullable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    @Column(name = "user_flag", nullable = false)
    private Boolean useFlag = true;

    public void restore() {
        useFlag = true;
    }

    public void delete() {
        useFlag = false;
    }
}

package com.maro.roomescapediary.dto;

import com.maro.roomescapediary.entity.Image;
import com.maro.roomescapediary.enums.TableCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageDto {

    private int seq;

    private int typeSeq;

    private TableCode tableCode;

    private String url;

    private boolean useFlag;

    public Image toEntity() {
        return Image.builder()
            .typeSeq(typeSeq)
            .tableCode(tableCode)
            .url(url)
            .build();
    }
}

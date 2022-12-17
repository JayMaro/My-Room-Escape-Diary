package com.maro.roomescapediary.image.dto;

import com.maro.roomescapediary.image.entity.Image;
import com.maro.roomescapediary.image.enums.TableCode;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
public class ImageDto {

    private int seq;
    @Positive
    private int typeSeq;
    @NotNull
    private TableCode tableCode;
    @NotBlank @Length(max = 500)
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

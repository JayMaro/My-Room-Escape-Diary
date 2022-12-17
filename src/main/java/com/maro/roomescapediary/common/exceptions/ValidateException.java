package com.maro.roomescapediary.common.exceptions;

import lombok.Getter;

@Getter
public class ValidateException extends RuntimeException{

    public ValidateException(String message) {
        super(message);
    }

}

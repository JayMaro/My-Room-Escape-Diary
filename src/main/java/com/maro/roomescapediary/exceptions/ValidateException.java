package com.maro.roomescapediary.exceptions;

import lombok.Getter;

@Getter
public class ValidateException extends RuntimeException{

    public ValidateException(String message) {
        super(message);
    }

}

package com.peppa.common.core.exception;

import com.peppa.common.core.enums.ExceptionEnums;
import lombok.Getter;

/**
 * @Author: peppa
 * @Description: 自定义的 API 异常
 * @Date: Created in 9:20 2024/8/28
 */
@Getter
public class ApiException extends RuntimeException{

    private final Integer code;

    public ApiException(String message, Integer code) {
        super(message);
        this.code = code;
    }


    public ApiException(ExceptionEnums enums) {
        super(enums.getMsg());
        this.code = enums.getCode();
    }
}

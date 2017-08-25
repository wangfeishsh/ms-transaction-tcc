package com.bao.exception;

import com.bao.domain.BaseCode;
import com.bao.domain.Response;
import lombok.Data;

@Data
public class BaseException extends RuntimeException {

    public String code;

    public BaseException(BaseCode baseCode) {
        super(baseCode.getMessage());
        this.setCode(baseCode.getCode());
    }

    public BaseException(BaseCode baseCode, Response response) {
        super(response.getCode() + ":" + response.getMsg());
        this.setCode(baseCode.getCode());
    }

    public BaseException(String code, String message) {
        super(message);
        this.setCode(code);
    }

}

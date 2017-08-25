package com.bao.common;


import com.bao.domain.BaseCode;

/**
 * Created by nannan on 2017/6/9.
 */
public enum BusinessCode implements BaseCode {

    SUCCESS("8000", "SUCCESS"),
    SELF("8020", "SELF"),
    ERROR_PARTICIPANT("8020",  "参与者错误");


    BusinessCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

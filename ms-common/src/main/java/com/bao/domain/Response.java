package com.bao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by nannan on 2017/6/9.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private String code;
    private String msg;
    private T result;

    public static Response success(Object data) {
        Response response = new Response();
        response.setMsg("success");
        response.setCode("8000");
        response.setResult(data);
        return response;
    }

    public static Response failure(BaseCode code) {
        return failure(code, (Object) null);
    }

    public static Response failure(BaseCode code, Object data) {
        Response response = new Response();
        response.setMsg(code.getMessage());
        response.setCode(code.getCode());
        response.setResult(data);
        return response;
    }

    public boolean isSuccess() {
        return this.getCode().equals("8000");
    }

}

package com.cp26.soft3888_m11_04_group4.vo;

public class JsonResult <T> {
    private Integer code;
    private String msg;
    private T data;

    public JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> JsonResult<T> build(Integer code, String msg, T data){
        return new JsonResult(code, msg, data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

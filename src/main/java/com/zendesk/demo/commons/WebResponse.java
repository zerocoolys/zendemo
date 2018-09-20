package com.zendesk.demo.commons;

public class WebResponse {

    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public WebResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public WebResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public WebResponse setData(Object data) {
        this.data = data;
        return this;
    }
}

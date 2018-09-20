package com.zendesk.demo.web;

import com.zendesk.demo.commons.WebResponse;

public interface BaseController {


    default WebResponse ok() {
        return new WebResponse();
    }

    default WebResponse ok(Object obj) {
        return new WebResponse().setData(obj);
    }

    default WebResponse err(int code) {
        return new WebResponse().setCode(code);
    }

    default WebResponse err(int code, String msg) {
        return err(code).setMsg(msg);
    }
}

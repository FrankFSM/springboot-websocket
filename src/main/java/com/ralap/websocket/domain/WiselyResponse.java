package com.ralap.websocket.domain;

import lombok.Data;

/**
 * Created by ralap on 2017/11/11.
 */
@Data
public class WiselyResponse {

    private String responseManage;


    public WiselyResponse(String s) {
        this.responseManage = s;
    }
}

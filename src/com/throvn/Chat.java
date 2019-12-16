package com.throvn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat {
    private String msg;
    private String user;
    private String time;

    public Chat(String user, String msg, String time) {
        this.msg = msg;
        this.time = time.split(" ")[1];
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public String getTime() {
        return time;
    }

    public String getUser() {
        return user;
    }
}

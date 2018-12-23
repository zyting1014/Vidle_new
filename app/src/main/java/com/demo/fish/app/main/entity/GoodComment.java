package com.demo.fish.app.main.entity;

import cn.bmob.v3.BmobObject;

public class GoodComment extends BmobObject {
    private String uname;//评价人编号
    private String gname;//商品编号
    private String comment;//评价信息

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

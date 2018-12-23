package com.demo.fish.app.main.entity;

import java.util.Date;

import cn.bmob.v3.BmobObject;

public class Order extends BmobObject {
    private String uname;//用户名
    private String gname;//商品名称
    private Integer gnumber;//商品数量
    private Float order_amount;//订单金额
    private Date date;//下单日期

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

    public Integer getGnumber() {
        return gnumber;
    }

    public void setGnumber(Integer gnumber) {
        this.gnumber = gnumber;
    }

    public Float getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(Float order_amount) {
        this.order_amount = order_amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

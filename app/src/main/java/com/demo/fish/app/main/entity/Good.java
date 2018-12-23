package com.demo.fish.app.main.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Good extends BmobObject {
    private String gname;//商品名称
    private String publisher;//发布人
    private Integer warehouse_amount;//库存数量
    private float price;//商品价格
    private BmobFile photo;//图片

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getWarehouse_amount() {
        return warehouse_amount;
    }

    public void setWarehouse_amount(Integer warehouse_amount) {
        this.warehouse_amount = warehouse_amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo.getFileUrl();
    }

    public void setPhoto(BmobFile photo1) {
        this.photo = photo;
    }

}

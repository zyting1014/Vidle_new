package com.demo.fish.app.main.entity;

import android.databinding.BaseObservable;


public class BannerEntity extends BaseObservable {



    private String imageUrl;
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

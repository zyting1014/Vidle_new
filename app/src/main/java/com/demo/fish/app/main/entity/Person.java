package com.demo.fish.app.main.entity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class Person extends BmobUser {
    /*
    private String password;
    private String id;
    */
    private BmobFile profile_photo;
    private int balance;
    /*
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    */
    public double getBalance(){
        return balance;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }
    public String getProfile_photo() { return profile_photo.getFileUrl(); }
    public void setProfile_photo(BmobFile profile_photo) { this.profile_photo = profile_photo; }
}

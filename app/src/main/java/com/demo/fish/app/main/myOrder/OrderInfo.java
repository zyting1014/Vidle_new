package com.demo.fish.app.main.myOrder;

/**
 * Created by Administrator on 2018/1/22.
 */

public class OrderInfo {
    private int ID;
    private String Name;
    private boolean DeleteState;
    private boolean EvaluateState;
    private  int img;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isDeleteState() {
        return DeleteState;
    }

    public void setDeleteState(boolean deleteState) {
        DeleteState = deleteState;
    }

    public boolean isEvaluateState() {
        return EvaluateState;
    }

    public void setEvaluateState(boolean evaluateState) {
        EvaluateState = evaluateState;
    }
    public void setIcon(int url){
        this.img=url;
    }
    public int getImg(){
        return img;
    }
}

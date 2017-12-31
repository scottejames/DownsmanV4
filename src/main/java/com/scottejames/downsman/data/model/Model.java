package com.scottejames.downsman.data.model;

public class Model {
    private int id = 0;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public boolean isPersisted() {
        return id != 0;
    }


}

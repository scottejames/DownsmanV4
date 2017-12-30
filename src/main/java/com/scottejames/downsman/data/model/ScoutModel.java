package com.scottejames.downsman.data.model;

public class ScoutModel extends Model {

    private String name = null;

    public ScoutModel(String name) {

        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

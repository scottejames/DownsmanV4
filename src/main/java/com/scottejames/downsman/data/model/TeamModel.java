package com.scottejames.downsman.data.model;

import com.scottejames.downsman.data.exceptions.NonEntityModel;

import java.util.ArrayList;
import java.util.List;

public class TeamModel extends Model{
    private List<ScoutModel> entries = new ArrayList<>();
    private String name = null;

    public TeamModel() {
        this.name = "Undefined";
    }

    public TeamModel(String name) {
        this.name = name;
    }

    public void addTeamMember(ScoutModel scout) throws NonEntityModel {

        this.entries.add(scout);
    }

    public List<ScoutModel> getEntries() {
        return entries;
    }

    public String getName() {

        return name;
    }

    public void removeTeamMember(ScoutModel scout){
        int id = 0;
        for (int i = 0; i < this.entries.size(); i++){
            if (entries.get(i).getId() == scout.getId()){
                id = i;
            }

        }
        this.entries.remove(id);

    }

    public int teamSize(){
        return this.entries.size();
    }
}

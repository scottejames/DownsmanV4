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
        if (scout.getId() == 0 ) {
            throw new NonEntityModel("Trying to add a scout to team model that is not added to scout service");
        }
        this.entries.add(scout);
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

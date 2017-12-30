package com.scottejames.downsman.data.service;

import com.scottejames.downsman.data.model.ScoutModel;

public class ServiceManager {

    private static ServiceManager instance = null;
    public static synchronized ServiceManager getInstance() {
        if (instance == null){
            instance = new ServiceManager();
        }
        return instance;
    }

    private ScoutService scoutService = new ScoutService();
    private TeamService teamService = new TeamService();

    public ScoutService getScoutService() {
        return scoutService;
    }

    public void setScoutService(ScoutService scoutService) {
        this.scoutService = scoutService;
    }

    public TeamService getTeamService() {
        return teamService;
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}

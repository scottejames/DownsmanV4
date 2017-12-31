package com.scottejames.downsman.data.service;

import com.scottejames.downsman.data.exceptions.NonEntityModel;
import com.scottejames.downsman.data.model.ScoutModel;
import com.scottejames.downsman.data.model.TeamModel;

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

    public void createTestData() {

        TeamModel teamA = new TeamModel("TeamA");
        TeamModel teamB = new TeamModel("TeamB");

        teamService.add(teamA);
        teamService.add(teamB);

        ScoutModel scott = new ScoutModel("Scott", "James", "scottejames@gmail.com");
        ScoutModel andrew = new ScoutModel("Andrew", "Pain", "bob@scouts.com");
        ScoutModel george = new ScoutModel("George", "frown", "frank@bob.com");

        scoutService.add(scott);
        scoutService.add(andrew);
        scoutService.add(george);

        try {
            teamB.addTeamMember(scott);
            teamB.addTeamMember(andrew);

            teamA.addTeamMember(george);

        } catch (NonEntityModel nonEntityModel) {
            nonEntityModel.printStackTrace();
            System.exit(-1);
        }

    }
}

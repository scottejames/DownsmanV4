package com.scottejames.downsman;

import javax.servlet.annotation.WebServlet;

import com.scottejames.downsman.data.model.ScoutModel;
import com.scottejames.downsman.data.model.TeamModel;
import com.scottejames.downsman.data.service.ServiceManager;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.List;

@Theme("mytheme")
public class MyUI extends UI {

    private int currentTeamId = 0;

    private ServiceManager sm = ServiceManager.getInstance();
    private Grid<ScoutModel> grid = new Grid<>(ScoutModel.class);
    ComboBox<TeamModel> teamSelection = new ComboBox<>();
    private AddScoutForm c = new AddScoutForm(this);

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();

        teamSelection.setItemCaptionGenerator(TeamModel::getName);

        teamSelection.setItems(sm.getTeamService().getAll());
        teamSelection.setEmptySelectionAllowed(false);

        teamSelection.addValueChangeListener(event -> {this.teamSelectionChanged(event.getValue().getId());});

        Button addScoutButton = new Button("Add Scout");
        addScoutButton.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setScout(new ScoutModel());

        });
        HorizontalLayout toolBar = new HorizontalLayout(teamSelection,addScoutButton);

        grid.setColumns("firstName", "lastName", "email");


        HorizontalLayout main = new HorizontalLayout(grid,form);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid,1);

        layout.addComponents(toolBar,main);
        updateList();

        setContent(layout);

        form.setVisible(false);
        grid.asSingleSelect().addValueChangeListener( event -> {
            if (event.getValue() == null) {
                form.setVisible(false);
            } else {
                form.setScout(event.getValue());
            }
        });
    }

    private void teamSelectionChanged(int id) {

       this.currentTeamId = id;
       this.form.setTeam(sm.getTeamService().getById(id));
       this.updateList();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    public void updateList() {
        //filterText.getValue()
        if (currentTeamId != 0) {
            List<ScoutModel> scouts = sm.getTeamService().getById(currentTeamId).getEntries();
            grid.setItems(scouts);
        }
    }
}

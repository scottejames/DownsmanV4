package com.scottejames.downsman;

import com.scottejames.downsman.data.exceptions.NonEntityModel;
import com.scottejames.downsman.data.model.ScoutModel;
import com.scottejames.downsman.data.model.TeamModel;
import com.scottejames.downsman.data.service.ServiceManager;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class AddScoutForm  extends FormLayout {
    private TextField firstName = new TextField("first name");
    private TextField lastName = new TextField("last name");
    private TextField email = new TextField("Email");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private ServiceManager sm = ServiceManager.getInstance();
    private MyUI myUI;
    private ScoutModel scout = null;
    private TeamModel team = null;
    private Binder<ScoutModel> binder = new Binder<>(ScoutModel.class);

    public AddScoutForm(MyUI myUI) {
        this.team = team;
        this.myUI = myUI;
        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);

        addComponents(firstName, lastName, email,buttons);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        binder.bindInstanceFields(this);
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
    }

    public void setTeam(TeamModel team){
        this.team = team;
    }

    public void setScout(ScoutModel scout){
        this.scout = scout;
        binder.setBean(scout);
        delete.setVisible(scout.isPersisted());
        setVisible(true);
        firstName.selectAll();
    }
    private void delete(){
        sm.getScoutService().remove(scout);
        team.removeTeamMember(scout);
        myUI.updateList();
        setVisible(false);

    }
    private void save() {
        sm.getScoutService().add(scout);
        try {
            team.addTeamMember(scout);
        } catch (NonEntityModel nonEntityModel) {
            nonEntityModel.printStackTrace();
        }
        myUI.updateList();
        setVisible(false);
    }
}

package com.scottejames.downsman;

import javax.servlet.annotation.WebServlet;

import com.scottejames.downsman.data.model.ScoutModel;
import com.scottejames.downsman.data.model.TeamModel;
import com.scottejames.downsman.data.service.ServiceManager;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

@Theme("mytheme")
public class MyUI extends UI {
    private ServiceManager sm = ServiceManager.getInstance();

    private Grid<ScoutModel> grid = new Grid<>(ScoutModel.class);
    private TextField filterText = new TextField();

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        // Create some sample data
        sm.createTestData();

        final VerticalLayout layout = new VerticalLayout();

        filterText.setPlaceholder("filter by filter...");
        filterText.addValueChangeListener(e -> updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn  = new Button (VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e-> filterText.clear());

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addScoutButton = new Button("Add Scout");
        addScoutButton.addClickListener(e -> {
            grid.asSingleSelect().clear();
        });
        HorizontalLayout toolBar = new HorizontalLayout(filtering,addScoutButton);

        grid.setColumns("firstName", "lastName", "email");


        HorizontalLayout main = new HorizontalLayout(grid);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid,1);

        layout.addComponents(toolBar,main);
        updateList();

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    public void updateList() {
        //filterText.getValue()
        List<ScoutModel> scouts = sm.getScoutService().getAll();
        grid.setItems(scouts);
    }
}

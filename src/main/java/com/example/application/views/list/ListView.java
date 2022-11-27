package com.example.application.views.list;


import com.example.application.data.entity.ServicesList;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@PageTitle(" Services | FixMan")
@Route(value = "services")
public class ListView extends VerticalLayout {

    Grid<ServicesList> grid = new Grid<>(ServicesList.class);
    TextField filterText = new TextField();
    ServiceForm form;
    private CrmService service;

    public ListView(CrmService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
          getToolbar(),
          getContent()
        );

        updateList();
    }

    private void updateList() {
        grid.setItems(service.findAllServices(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        form = new ServiceForm();
        form.setWidth("25em");
    }


    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addServiceButton = new Button("Add Service");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addServiceButton);
        toolbar.addClassName("toolbar");
        return toolbar;

    }

    private void configureGrid() {
        addClassName("services-grid");
        grid.setSizeFull();
        grid.setColumns("serviceName", "serviceDescr", "price");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }


}





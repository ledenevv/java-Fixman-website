package com.example.application.views.list;

import com.example.application.data.entity.ServicesList;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.awt.*;

public class ServiceForm extends FormLayout {

    Binder<ServicesList> binder = new BeanValidationBinder<>(ServicesList.class);

    TextField serviceName = new TextField("Service name");
    TextField serviceDescr = new TextField("Service description");
    TextField price = new TextField("Service price");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private ServicesList service;

    public ServiceForm() {
        addClassName("service-form");
        binder.bindInstanceFields(this);

        add(
                serviceName,
                serviceDescr,
                price,
                createButtonLayout()
        );
    }

    public void setService(ServicesList service) {
        this.service = service;
        binder.readBean(service);
    }

    private Component createButtonLayout() {                   //creating buttons save,cancel,delete for property settings layout
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickListener(event -> validateAndSave());
        cancel.addClickListener(event -> fireEvent(new CancelEvent(this)));
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, service)));
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(service);
            fireEvent(new SaveEvent(this, service));

        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ServiceFormEvent extends ComponentEvent<ServiceForm> {
        private ServicesList service;

        protected ServiceFormEvent(ServiceForm source, ServicesList service) {
            super(source, false);
            this.service = service;
        }

        public ServicesList getService() {
            return service;
        }
    }

    public static class SaveEvent extends ServiceFormEvent {
        SaveEvent(ServiceForm source, ServicesList service) {
            super(source, service);
        }
    }

    public static class DeleteEvent extends ServiceFormEvent {
        DeleteEvent(ServiceForm source, ServicesList service) {
            super(source, service);
        }

    }

    public static class CancelEvent extends ServiceFormEvent {
        CancelEvent(ServiceForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}


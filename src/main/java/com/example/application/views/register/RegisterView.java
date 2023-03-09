package com.example.application.views.register;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.example.application.data.entity.SamplePerson;
import com.example.application.data.service.SamplePersonService;

@PageTitle("Registration")
@Route(value = "register", layout = MainLayout.class)
@Uses(Icon.class)

public class RegisterView extends Div {
    private TextField email = new TextField("Email");
    private TextField password = new TextField("Password");
    private TextField passwordAgain = new TextField("Repeat password");
    private Button cancel = new Button("Cancel");
    private Button registruj = new Button("Register");
    private Binder<SamplePerson> binder = new Binder<>(SamplePerson.class);
    public RegisterView(SamplePersonService personService){
        addClassName("register-view");
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        binder.bindInstanceFields(this);
        clearForm();
        cancel.addClickListener(e -> clearForm());
        registruj.addClickListener(e -> {
            personService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " stored");
            clearForm();
        });
    }
    private void clearForm(){
        binder.setBean(new SamplePerson());
    }
    private Component createTitle(){
        return new H3("Register page");
    }

    private Component createFormLayout(){
        FormLayout formLayout = new FormLayout();
        VerticalLayout layout = new VerticalLayout();
        email.setErrorMessage("Registration email not valid");
        //formLayout.add(email, password, passwordAgain);
        layout.add(email, password, passwordAgain);
        //return formLayout;
        return layout;
    }
    private Component createButtonLayout(){
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        registruj.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(registruj);
        buttonLayout.add(cancel);
        return buttonLayout;
    }
}

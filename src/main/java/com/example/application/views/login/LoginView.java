package com.example.application.views.login;

import com.example.application.data.entity.SamplePerson;
import com.example.application.data.service.SamplePersonService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
@Uses(Icon.class)
public class LoginView extends Div {

    private TextField loginName = new TextField("Login");
    private TextField loginPassword = new TextField("Password");
    private TextField email = new TextField("Email");
    private Button cancel = new Button("Cancel");
    private Button loguj = new Button("Login!");
    private Binder<SamplePerson> binder = new Binder<>(SamplePerson.class);

    public LoginView(SamplePersonService personService) {
        addClassName("login-view");
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        binder.bindInstanceFields(this);
        clearForm();
        cancel.addClickListener(e -> clearForm());
        loguj.addClickListener(e -> {
            personService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });
    }
    private void clearForm() {
        binder.setBean(new SamplePerson());
    }
    private Component createTitle() {
        return new H3("Login page");
    }
    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(loginName, loginPassword, email);
        return formLayout;
    }
    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        loguj.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(loguj);
        buttonLayout.add(cancel);
        return buttonLayout;
    }
}

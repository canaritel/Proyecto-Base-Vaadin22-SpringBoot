package com.example.application.ui.views.roles;

import javax.annotation.security.PermitAll;

import com.example.application.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "rolesa", layout = MainLayout.class)
@PermitAll
public class RolView extends VerticalLayout {

    public static final String VIEW_NAME = "Roles";

    public RolView() {
    }

}

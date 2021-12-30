package com.example.application.ui.views.tiendas;

import javax.annotation.security.PermitAll;

import com.example.application.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "local", layout = MainLayout.class)
@PermitAll
public class LocalComercialView extends VerticalLayout {

    public static final String VIEW_NAME = "Locales Comerciales";

    public LocalComercialView() {
    }

}

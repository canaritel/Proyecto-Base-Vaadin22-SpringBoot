package com.example.application.views.tiendas;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "local", layout = MainLayout.class)
public class LocalComercialView extends VerticalLayout {

    public static final String VIEW_NAME = "Locales Comerciales";

}

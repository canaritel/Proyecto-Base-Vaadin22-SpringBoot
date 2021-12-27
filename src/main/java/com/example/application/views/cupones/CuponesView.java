package com.example.application.views.cupones;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "cupones", layout = MainLayout.class)
public class CuponesView extends VerticalLayout {

    public static final String VIEW_NAME = "Cupones";

}

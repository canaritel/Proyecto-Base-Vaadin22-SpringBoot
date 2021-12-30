package com.example.application.ui.views.cupones;

import javax.annotation.security.PermitAll;

import com.example.application.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "cupones", layout = MainLayout.class)
@PermitAll
public class CuponesView extends VerticalLayout {

    public static final String VIEW_NAME = "Cupones";

    public CuponesView() {
    }

}

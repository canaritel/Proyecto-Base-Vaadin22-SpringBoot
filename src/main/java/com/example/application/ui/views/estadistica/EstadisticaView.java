package com.example.application.ui.views.estadistica;

import javax.annotation.security.PermitAll;

import com.example.application.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "estadistica", layout = MainLayout.class)
@PermitAll
public class EstadisticaView extends VerticalLayout {

    public static final String VIEW_NAME = "Estadísticas";

    public EstadisticaView() {
    }

}

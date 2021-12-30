package com.example.application.ui.views.zonas;

import javax.annotation.security.PermitAll;

import com.example.application.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "zonas", layout = MainLayout.class)
@PermitAll

public class ZonaComercialView extends VerticalLayout {

    public static final String VIEW_NAME = "Zonas Comerciales";

    // public ZonaComercialView() {

    // Button home = UIUtils.createTertiaryButton(VaadinIcon.HOME);
    // Button clock = UIUtils.createTertiaryButton(VaadinIcon.CLOCK);
    // Button users = UIUtils.createTertiaryButton(VaadinIcon.USERS);
    // Button search = UIUtils.createTertiaryButton(VaadinIcon.SEARCH);
    // Button bell = UIUtils.createTertiaryButton(VaadinIcon.BELL);

    // // Set the width
    // for (Button button : new Button[] { home, clock, users, search, bell }) {
    // button.setWidth("20%");
    // }

    // FlexLayout footer = new FlexLayout(home, clock, users, search, bell);

    // // Set background color and shadow
    // UIUtils.setBackgroundColor(LumoStyles.Color.BASE_COLOR, footer);
    // UIUtils.setShadow(Shadow.M, footer);

    // setViewFooter(footer);
    // // setAppFooterInner(footer);

    // }

}

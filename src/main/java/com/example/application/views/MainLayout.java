package com.example.application.views;

import java.nio.charset.Charset;

import com.example.application.Application;
import com.example.application.data.idiomas.Idioma;
import com.example.application.security.SecurityService;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;
    private Idioma idioma = new Idioma(Application.lang_default);

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        // Diseño elementos barra superior
        // https://stackoverflow.com/questions/57553973/where-should-i-place-my-vaadin-10-static-files/57553974#57553974
        H2 textLogo = new H2("");
        Image imgLogo = new Image("/images/cuponcito_opt.png", "alt text");
        imgLogo.setHeight("34px"); // ajustamos el tamaño de la imagen

        // Cerramos cuando se pulsa en Salir
        Button logout = new Button(formatearTexto("salir"), e -> securityService.logout());
        logout.setWidth("20px");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), imgLogo, textLogo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(textLogo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        header.getThemeList().set("light", true);

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(listLink,
                new RouterLink("Dashboard", DashboardView.class)));
    }

    // Este método permite acentos y tildes español de los campos traducidos
    private String formatearTexto(String property) {
        String texto_formateado = new String(idioma.getProperty(property).getBytes(Charset.forName("8859_1")));
        return texto_formateado;
    }
}

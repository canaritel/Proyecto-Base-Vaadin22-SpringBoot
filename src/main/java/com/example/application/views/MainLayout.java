package com.example.application.views;

import java.util.Locale;

import com.example.application.Application;
import com.example.application.security.SecurityService;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;
    private final Locale LOCALE_ES = new Locale("es");
    private final Locale LOCALE_EN = new Locale("en");

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

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), imgLogo, textLogo, menuBar());

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(textLogo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        header.getThemeList().set("light", true);

        addToNavbar(header);
    }

    private Component menuBar() {
        Div div = new Div();
        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_ICON, MenuBarVariant.LUMO_PRIMARY);

        MenuItem item = menuBar.addItem(new Icon(VaadinIcon.COG_O));
        SubMenu shareSubMenu = item.getSubMenu();

        MenuItem onColor = shareSubMenu.addItem(formatText("color"));
        onColor.addClickListener(e -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList(); // tipo de Modo activado
            if (themeList.contains(Lumo.DARK)) {
                UI.getCurrent().getElement().getThemeList().remove(Lumo.DARK);
            } else {
                UI.getCurrent().getElement().getThemeList().add(Lumo.DARK);
            }
        });

        MenuItem onIdioma = shareSubMenu.addItem(formatText("lenguaje") + ": " + formatText("idioma"));
        onIdioma.addClickListener(e -> {
            if (Application.locale_APP.getLanguage().equalsIgnoreCase("es")) {
                Application.locale_APP = LOCALE_EN;
            } else {
                Application.locale_APP = LOCALE_ES;
            }
            UI.getCurrent().getPage().reload();
        });

        MenuItem onSocialMedia = shareSubMenu.addItem(formatText("opcion"));
        SubMenu socialMediaSubMenu = onSocialMedia.getSubMenu();
        socialMediaSubMenu.addItem(formatText("perfil"), e -> System.out.println());
        socialMediaSubMenu.addItem(formatText("cuenta"), e -> System.out.println());
        socialMediaSubMenu.addItem(formatText("personalizacion"), e -> System.out.println());

        shareSubMenu.add(new Hr());
        shareSubMenu.addItem(formatText("cerrar")).addClickListener(e -> securityService.logout());

        div.add(menuBar);
        return div;
    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(listLink,
                new RouterLink("Dashboard", DashboardView.class)));
    }

    private String formatText(String property) {
        // Este método devuelve el texto traducido.
        // Nos decir está "depreciado" pero lo vamos a continuar usando
        return getTranslation(property, Application.locale_APP);
    }
}

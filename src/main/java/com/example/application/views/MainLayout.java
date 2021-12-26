package com.example.application.views;

import java.nio.charset.Charset;

import com.example.application.Application;
import com.example.application.data.idiomas.Idioma;
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
        // Button logout = new Button(formatearTexto("salir"), e ->
        // securityService.logout());
        // logout.setWidth("20px");

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

        MenuItem onColor = shareSubMenu.addItem(formatearTexto("color"));
        onColor.addClickListener(e -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList(); // tipo de Modo activado
            if (themeList.contains(Lumo.DARK)) {
                UI.getCurrent().getElement().getThemeList().remove(Lumo.DARK);
            } else {
                UI.getCurrent().getElement().getThemeList().add(Lumo.DARK);
            }
        });

        MenuItem onIdioma = shareSubMenu.addItem(formatearTexto("lenguaje") + ": " + formatearTexto("idioma"));
        onIdioma.addClickListener(e -> {
            if (Application.lang_default.equalsIgnoreCase("Spain")) {
                Application.lang_default = "English";
            } else {
                Application.lang_default = "Spain";
            }
            UI.getCurrent().getPage().reload();
        });

        MenuItem onSocialMedia = shareSubMenu.addItem(formatearTexto("opcion"));
        SubMenu socialMediaSubMenu = onSocialMedia.getSubMenu();
        socialMediaSubMenu.addItem(formatearTexto("perfil"), e -> System.out.println());
        socialMediaSubMenu.addItem(formatearTexto("cuenta"), e -> System.out.println());
        socialMediaSubMenu.addItem(formatearTexto("personalizacion"), e -> System.out.println());

        shareSubMenu.add(new Hr());
        shareSubMenu.addItem(formatearTexto("cerrar")).addClickListener(e -> securityService.logout());

        div.add(menuBar);
        return div;
    }

    private MenuItem createIconItem(MenuBar menu, VaadinIcon iconName, String ariaLabel) {
        Icon icon = new Icon(iconName);
        MenuItem item = menu.addItem(icon);
        item.getElement().setAttribute("aria-label", ariaLabel);
        return item;
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

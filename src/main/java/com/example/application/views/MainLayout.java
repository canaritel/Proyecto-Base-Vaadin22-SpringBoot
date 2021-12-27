package com.example.application.views;

import java.util.Locale;

import com.example.application.Application;
import com.example.application.security.SecurityService;
import com.example.application.views.cupones.CuponesView;
import com.example.application.views.estadistica.EstadisticaView;
import com.example.application.views.list.ListView;
import com.example.application.views.roles.RolView;
import com.example.application.views.tiendas.LocalComercialView;
import com.example.application.views.zonas.ZonaComercialView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;
    private final Locale LOCALE_ES = new Locale("es");
    private final Locale LOCALE_EN = new Locale("en");
    private Tabs tabs;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer(createTabs());
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
        // MenuBar.MenuBarI18n customI18n = new MenuBar.MenuBarI18n();
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

    private void createDrawer(Tabs tabs) {
        // Muestra un espacio en la barra de tabs
        VerticalLayout layout = new VerticalLayout();
        layout.add("");

        // Label label = new Label("Nivel: Super Administrador"); // creo Label de nivel
        // rol
        // layout.add(label); // añado el Label al VerticalLayout
        // layout.setAlignSelf(Alignment.CENTER, label); // centro el label en el Layout

        Button rolInfoButton = new Button("Nivel: " + formatText("rol_superadmin"));
        rolInfoButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        layout.add(rolInfoButton); // añado el Label al VerticalLayout
        layout.setAlignSelf(Alignment.CENTER, rolInfoButton); // centro el label en el Layout

        addToDrawer(layout);

        // Muestra el menú Tabs
        addToDrawer(tabs);
    }

    private Tabs createTabs() {
        tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.MATERIAL_FIXED);
        // tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL); //elimino barra
        tabs.setId("tabs");

        // Listado de iconos https://vaadin.com/components/vaadin-icons/java-examples
        Tab tab_zonasComerciales = new Tab(
                VaadinIcon.HOSPITAL.create(), createMenuLink(ZonaComercialView.class, formatText("zona_comercial")));

        Tab tab_localesComerciales = new Tab(
                VaadinIcon.SHOP.create(), createMenuLink(LocalComercialView.class, formatText("zona_tienda")));

        Tab tab_cupones = new Tab(
                VaadinIcon.GIFT.create(), createMenuLink(CuponesView.class, formatText("cupon")));

        Tab tab_estadisticas = new Tab(
                VaadinIcon.BAR_CHART.create(), createMenuLink(EstadisticaView.class, formatText("estadistica")));

        Tab tab_roles = new Tab(
                VaadinIcon.USER.create(), createMenuLink(RolView.class, formatText("rol")));

        Tab tab_listados = new Tab(
                VaadinIcon.GAMEPAD.create(), createMenuLink(ListView.class, "List"));

        tabs.add(tab_zonasComerciales, tab_localesComerciales, tab_cupones, tab_estadisticas, tab_roles, tab_listados);

        return tabs;
    }

    private RouterLink createMenuLink(Class<? extends Component> viewClass, String caption) {
        final RouterLink routerLink = new RouterLink(null, viewClass);

        routerLink.setClassName("menu-link");
        // routerLink.add(icon);
        routerLink.add(new Span(caption));
        // icon.setSize("24px");
        return routerLink;
    }

    private Button createMenuButton(String caption, Icon icon) {
        final Button routerButton = new Button(caption);
        routerButton.setClassName("menu-button");
        routerButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        routerButton.setIcon(icon);
        icon.setSize("24px");
        return routerButton;
    }

    private String formatText(String property) {
        // Este método devuelve el texto traducido.
        // Advierte está "depreciado" pero lo vamos a continuar usando
        return getTranslation(property, Application.locale_APP);
    }

}

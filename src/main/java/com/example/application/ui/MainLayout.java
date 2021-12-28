package com.example.application.ui;

import java.util.Locale;

import com.example.application.Application;
import com.example.application.security.SecurityService;
import com.example.application.ui.components.FlexBoxLayout;
import com.example.application.ui.utils.Overflow;
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
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;
    private final Locale LOCALE_ES = new Locale("es");
    private final Locale LOCALE_EN = new Locale("en");
    private Tabs tabs;
    private MenuBar menuSuperiorDerecha;
    private FlexBoxLayout viewContainer;
    private static final String CLASS_NAME = "root";
    private FlexBoxLayout column;
    private FlexBoxLayout row;
    private VerticalLayout naviDrawer;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer(createTabs());
        // initStructure();
    }

    private void createHeader() {
        // Diseño elementos barra superior
        // https://stackoverflow.com/questions/57553973/where-should-i-place-my-vaadin-10-static-files/57553974#57553974
        H2 textLogo = new H2("");
        Image imgLogo = new Image("/images/cuponcito_opt.png", "alt text");
        imgLogo.setHeight("34px"); // ajustamos el tamaño de la imagen

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), imgLogo, textLogo, menuSuperiorDerecha());

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(textLogo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        header.getThemeList().set("light", true);

        addToNavbar(header);
    }

    /**
     * Initialise the required components and containers.
     */
    private void initStructure() {
        naviDrawer = new VerticalLayout();

        viewContainer = new FlexBoxLayout();
        viewContainer.addClassName(CLASS_NAME + "__view-container");
        viewContainer.setOverflow(Overflow.HIDDEN);

        column = new FlexBoxLayout(viewContainer);
        column.addClassName(CLASS_NAME + "__column");
        column.setFlexDirection(FlexDirection.COLUMN);
        column.setFlexGrow(1, viewContainer);
        column.setOverflow(Overflow.HIDDEN);

        row = new FlexBoxLayout(naviDrawer, column);
        row.addClassName(CLASS_NAME + "__row");
        row.setFlexGrow(1, column);
        row.setOverflow(Overflow.HIDDEN);

        naviDrawer.add(row);
        naviDrawer.setFlexGrow(1, row);
    }

    private Component menuSuperiorDerecha() {
        Div div = new Div();

        menuSuperiorDerecha = new MenuBar();
        menuSuperiorDerecha.addThemeVariants(MenuBarVariant.LUMO_ICON, MenuBarVariant.LUMO_PRIMARY);

        MenuItem item = menuSuperiorDerecha.addItem(new Icon(VaadinIcon.COG_O));
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

        MenuItem onPerfil = shareSubMenu.addItem(formatText("perfil"));
        onPerfil.addClickListener(e -> {
        });

        MenuItem onCuenta = shareSubMenu.addItem(formatText("cuenta"));
        onCuenta.addClickListener(e -> {
        });

        MenuItem onPersonal = shareSubMenu.addItem(formatText("personalizacion"));
        onPersonal.addClickListener(e -> {
        });

        MenuItem onIdioma2 = shareSubMenu.addItem(formatText("lenguaje"));
        SubMenu onIdioma2SubMenu = onIdioma2.getSubMenu();
        onIdioma2SubMenu.addItem(formatText("espanol"), e -> {
            cambioIdioma(LOCALE_ES);
        });
        onIdioma2SubMenu.addItem(formatText("ingles"), e -> {
            cambioIdioma(LOCALE_EN);
        });
        onIdioma2SubMenu.addItem(formatText("frances"), e -> {
            cambioIdioma(LOCALE_EN);
        });
        onIdioma2SubMenu.addItem(formatText("aleman"), e -> {
            cambioIdioma(LOCALE_EN);
        });
        onIdioma2SubMenu.addItem(formatText("italia"), e -> {
            cambioIdioma(LOCALE_EN);
        });

        shareSubMenu.add(new Hr());
        shareSubMenu.addItem(formatText("cerrar")).addClickListener(e -> securityService.logout());

        div.add(menuSuperiorDerecha);

        return div;
    }

    private void cambioIdioma(Locale locale) {
        Application.locale_APP = locale;
        UI.getCurrent().getPage().reload();
    }

    private void createDrawer(Tabs tabs) {
        VerticalLayout layout = new VerticalLayout();
        layout.add(""); // Muestra un espacio en la barra de tabs

        Button rolInfoButton = new Button(formatText("nivel") + ": " + formatText("rol_superadmin"));
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
        routerLink.add(new Span(caption));

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

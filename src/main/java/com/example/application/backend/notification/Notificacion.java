package com.example.application.backend.notification;

import java.util.Arrays;

import com.example.application.Application;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

public class Notificacion {

        private Button bellBtn;

        public Notificacion() {
        }

        public Div avisos(String texto) {
                Div div = new Div();

                Span numberOfNotifications = new Span(Application.numero_avisos + "");
                numberOfNotifications.getElement()
                                .getThemeList()
                                .addAll(Arrays.asList("badge", "error", "primary", "small", "pill"));
                numberOfNotifications.getStyle()
                                .set("position", "absolute")
                                .set("transform", "translate(-40%, -85%)");

                bellBtn = new Button(VaadinIcon.BELL_O.create());
                bellBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
                bellBtn.getElement().appendChild(numberOfNotifications.getElement());

                Div sampleNotification = new Div();
                // sampleNotification.add(createMentionNotification());
                sampleNotification.setText(texto);
                sampleNotification.getStyle().set("padding", "var(--lumo-space-m)");

                ContextMenu menu = new ContextMenu();
                menu.setOpenOnClick(true);
                menu.setTarget(bellBtn);
                menu.add(sampleNotification);
                div.add(bellBtn);

                return div;
        }

        private static Notification createMentionNotification() {

                Notification notification2 = new Notification();
                Avatar avatar = new Avatar("Jason Bailey");

                Span name = new Span("Jason Bailey");
                name.getStyle().set("font-weight", "500");

                Div info = new Div(
                                name,
                                new Text(" mentioned you in "),
                                new Anchor("#", "Project Q4"));

                HorizontalLayout layout = new HorizontalLayout(avatar, info);
                // createCloseBtn(notification);
                layout.setAlignItems(Alignment.CENTER);

                notification2.add(layout);

                return notification2;
        }

        private static Button createCloseBtn(Notification notification) {
                Button closeBtn = new Button(
                                VaadinIcon.CLOSE_SMALL.create(),
                                clickEvent -> notification.close());
                closeBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);

                return closeBtn;
        }

}

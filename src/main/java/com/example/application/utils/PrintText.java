package com.example.application.utils;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class PrintText {

    static public void imprime(String texto, String color) {
        Notification notification = Notification.show(texto);
        switch (color) {
            case "AZUL":
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
                break;

            case "ROJO":
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                break;

            case "VERDE":
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                break;

            case "NEGRO":
                notification.addThemeVariants(NotificationVariant.LUMO_CONTRAST);
                break;

            case "GRIS":
                break;

            default:

        }
    }

}

package com.example.application.utils;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class PrintText {

    public enum Color {
        AZUL, ROJO, VERDE, NEGRO, GRIS
    }

    static public void imprime(String texto, Color color) {
        Notification notification = Notification.show(texto);
        switch (color) {
            case AZUL:
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
                break;

            case ROJO:
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                break;

            case VERDE:
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                break;

            case NEGRO:
                notification.addThemeVariants(NotificationVariant.LUMO_CONTRAST);
                break;

            case GRIS:
                break;

            default:

        }
    }

}

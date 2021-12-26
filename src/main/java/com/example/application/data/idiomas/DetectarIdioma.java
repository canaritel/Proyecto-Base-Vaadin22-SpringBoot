package com.example.application.data.idiomas;

import com.example.application.Application;
import com.vaadin.flow.component.UI;

public class DetectarIdioma {

    public DetectarIdioma() {
        String idioma_detectado = UI.getCurrent().getLocale().getDisplayLanguage();
        if (idioma_detectado.equalsIgnoreCase("español")) {
            System.out.println("Idioma detectado: " + idioma_detectado);
            Application.lang_default = "Spain"; // idioma por defecto
        } else {
            System.out.println("Idioma detectado: " + idioma_detectado);
            Application.lang_default = "English"; // idioma por defecto
        }
    }

}

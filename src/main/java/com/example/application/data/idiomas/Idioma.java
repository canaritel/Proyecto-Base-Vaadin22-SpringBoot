package com.example.application.data.idiomas;

import java.io.IOException;
import java.util.Properties;

// Esta clase no la vamos a usar, pero muestra como podríamos implementar multi-idioma sin usar i18n
public class Idioma extends Properties {

    Properties properties = new Properties();

    public Idioma(String idioma) {
        setIdioma(idioma);
    }

    public void setIdioma(String idioma) {
        // Modificar si quieres añadir mas idiomas
        // Cambia el nombre de los ficheros o añade los necesarios
        switch (idioma) {
            case "Spain":
                getProperties("/i18n/messages.properties");
                break;
            case "English":
                getProperties("/i18n/messages_en.properties");
                break;
            default:
                getProperties("/traducciones/spanish.properties");
        }
    }

    private void getProperties(String idioma) {
        try {
            this.load(getClass().getResourceAsStream(idioma));
        } catch (IOException ex) {
            System.err.println("No encuentro el archivo de traducciones " + idioma);
        }
    }

}

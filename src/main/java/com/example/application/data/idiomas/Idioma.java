package com.example.application.data.idiomas;

import java.io.IOException;
import java.util.Properties;

public class Idioma extends Properties {

    Properties properties = new Properties();

    public Idioma(String idioma) {
        // Modificar si quieres añadir mas idiomas
        // Cambia el nombre de los ficheros o añade los necesarios
        switch (idioma) {
            case "Spain":
                getProperties("/traducciones/spanish.properties");
                break;
            case "English":
                getProperties("/traducciones/english.properties");
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

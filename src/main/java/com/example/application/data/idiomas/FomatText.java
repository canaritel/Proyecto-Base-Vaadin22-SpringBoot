package com.example.application.data.idiomas;

import java.nio.charset.Charset;

public class FomatText {

    // Este método permite acentos y tildes español de los campos traducidos
    static public String formatearTexto(String property, Idioma idioma) {
        String texto_formateado = new String(idioma.getProperty(property).getBytes(Charset.forName("8859_1")));
        return texto_formateado;
    }

}

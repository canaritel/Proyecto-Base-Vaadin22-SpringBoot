package com.example.application.data.idiomas;

import java.nio.charset.Charset;

import com.example.application.Application;

// Este método no lo usamos. Pero es válido para sustituir el i18N
public class FomatText {

    // Este método permite acentos y tildes español de los campos traducidos
    static public String formatearTexto(String texto) {
        Idioma idioma = new Idioma(Application.lang_default);
        String texto_formateado = new String(idioma.getProperty(texto).getBytes(Charset.forName("8859_1")));
        return texto_formateado;
    }

}

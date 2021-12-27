package com.example.application.data.idiomas;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.vaadin.flow.i18n.I18NProvider;

import org.springframework.stereotype.Component;

@Component
public class VaadinI18NProvider implements I18NProvider {

    public static final String BUNDLE_PREFIX = "i18n.translation";

    public final Locale LOCALE_ES = new Locale("es", "ES");
    public final Locale LOCALE_EN = new Locale("en", "GB");

    private List<Locale> locales = Collections
            .unmodifiableList(Arrays.asList(LOCALE_ES, LOCALE_EN));

    @Override
    public List<Locale> getProvidedLocales() {
        return locales;
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale);

        String value = bundle.getString(key);
        if (params.length > 0) {
            value = MessageFormat.format(value, params);
        }
        return value;
    }

}

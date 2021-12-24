package com.example.application.views;

import java.nio.charset.Charset;
import java.util.Locale;

import com.example.application.data.idiomas.Idioma;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:application-${env}.properties", encoding = "UTF-8")
@Route("login")
@PageTitle("Login | Vaadin CRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private final LoginForm loginForm = new LoginForm();
	private Idioma idioma = new Idioma("Spain");

	public LoginView() {
		// addClassName("login-view");
		addClassName("login-rich-content");
		createLogin();
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		Image imgLogo = new Image("/images/cuponcito_opt.png", "alt text");
		imgLogo.setHeight("64px"); // ajustamos el tamaño de la imagen
		loginForm.setAction("login");
		add(new H2(""), imgLogo, loginForm, createFlags());
	}

	private void createLogin() {
		loginForm.getElement().getThemeList().add("light"); // lo ponemos estilo claro
		loginForm.setI18n(createSpanishI18n()); // creamos nuestro componente login personalizado a nuestro idioma
		loginForm.addForgotPasswordListener(
				event -> Notification.show("Pendiente crear recordar cuenta"));
	}

	private LoginI18n createSpanishI18n() {
		final LoginI18n i18n = LoginI18n.createDefault();
		i18n.setHeader(new LoginI18n.Header());
		i18n.getForm().setTitle(formatearTexto("acceda"));
		i18n.getForm().setSubmit(formatearTexto("entrar"));
		i18n.getForm().setUsername(formatearTexto("usuario"));
		i18n.getForm().setPassword(formatearTexto("clave"));
		i18n.getForm().setForgotPassword(formatearTexto("recordar"));
		i18n.getErrorMessage().setTitle(formatearTexto("error_titulo"));
		i18n.getErrorMessage().setMessage(formatearTexto("error_texto"));
		i18n.setAdditionalInformation(formatearTexto("info_acceso"));

		return i18n;
	}

	// Este método es para que permita acentos y tildes de los campos traducidos
	private String formatearTexto(String property) {
		String texto_formateado = new String(idioma.getProperty(property).getBytes(Charset.forName("8859_1")));
		return texto_formateado;
	}

	private Component createFlags() {
		HorizontalLayout loginInformation = new HorizontalLayout();
		// loginInformation.setClassName("information");
		// Personalización del acceso
		// Cargamos las imágenes
		Image imgFlagSpain = new Image("/images/icons8_spain_flag_50px.png", "Spain");
		imgFlagSpain.setHeight("60px"); // ajustamos el tamaño de la imagen
		Image imgFlagUK = new Image("/images/icons8_uk_flag_50px.png", "UK");
		imgFlagUK.setHeight("60px"); // ajustamos el tamaño de la imagen

		loginInformation.add(imgFlagSpain, imgFlagUK);
		loginInformation.setAlignItems(Alignment.CENTER);

		imgFlagSpain.addClickListener(e -> {
			Notification.show("Ponemos los textos es español");
			idioma = new Idioma("Spain");
			loginForm.setI18n(createSpanishI18n());
		});

		imgFlagUK.addClickListener(e -> {
			Notification.show("Ponemos los textos es inglés");
			idioma = new Idioma("English");
			loginForm.setI18n(createSpanishI18n());
		});

		return loginInformation;
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		if (beforeEnterEvent.getLocation()
				.getQueryParameters()
				.getParameters()
				.containsKey("error")) {
			loginForm.setError(true);
		}
	}

}
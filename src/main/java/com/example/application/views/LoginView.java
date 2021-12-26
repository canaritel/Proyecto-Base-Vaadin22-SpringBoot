package com.example.application.views;

import java.nio.charset.Charset;
import java.util.Locale;

import com.example.application.Application;
import com.example.application.data.idiomas.Idioma;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
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

@PropertySource(value = "classpath:application-${env}.properties", encoding = "UTF-8") // ruta_por_defecto_para_las_properties_usadas_en_la_internacionalización
@Route("login")
@PageTitle("Login | Vaadin CRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private LoginForm loginForm; // Creamos el objeto formulario
	private Idioma idioma; // Creamos objeto con idioma default
	private Locale locale;
	// private DetectarIdioma detectaIdioma;

	public LoginView() {
		if (idioma == null) {
			idioma = new Idioma(Application.lang_default); // inicializamos objeto con idioma default
		}
		if (loginForm == null) {
			loginForm = new LoginForm(); // inicializamos objeto formulario Login
		}

		addClassName("login-rich-content");
		createLogin();
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		Image imgLogo = new Image("/images/cuponcito_opt.png", "alt text");
		imgLogo.setHeight("64px"); // ajustamos el tamaño de la imagen

		loginForm.setAction("login"); // Set the LoginForm action to "login" to post the_login_form_to_Spring_Security

		add(new H2(""), imgLogo, loginForm, personalizationLogin());

	}

	// Desarrollamos e implementamos el uso de i18n internacionalización
	// https://maresmewebdevelopers.wordpress.com/2017/11/02/hola-mundo-con-multi-idioma-vamos-a-aprender-a-configurar-la-internacionalidad-con-i18n-en-spring-boot/

	private void muestras_idiomas() {
		// System.out.println(UI.getCurrent().getLocale().getCountry());
		System.out.println(UI.getCurrent().getLocale().getDisplayLanguage());
		// System.out.println(UI.getCurrent().getLocale().getDisplayName());
		// System.out.println(UI.getCurrent().getLocale().getISO3Language());
		// System.out.println(UI.getCurrent().getLocale().getISO3Country());
	}

	private void createLogin() {
		loginForm.getElement().getThemeList().add("light"); // lo ponemos estilo claro
		loginForm.setI18n(createI18n()); // creamos nuestro componente login personalizado a nuestro idioma
		loginForm.addForgotPasswordListener(
				event -> Notification.show(formatearTexto("pendiente")));
	}

	private LoginI18n createI18n() {
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

	// Este método permite acentos y tildes español de los campos traducidos
	private String formatearTexto(String property) {
		String texto_formateado = new String(idioma.getProperty(property).getBytes(Charset.forName("8859_1")));
		return texto_formateado;
	}

	private Component personalizationLogin() {
		HorizontalLayout loginInformation = new HorizontalLayout();
		// loginInformation.setClassName("information");
		// Personalización del acceso
		// Cargamos las imágenes de las banderas
		Image imgFlagSpain = new Image("/images/icons8_spain_flag_50px.png", "Spain");
		imgFlagSpain.setHeight("60px"); // ajustamos el tamaño de la imagen
		Image imgFlagUK = new Image("/images/icons8_uk_flag_50px.png", "UK");
		imgFlagUK.setHeight("60px"); // ajustamos el tamaño de la imagen

		loginInformation.add(imgFlagSpain, imgFlagUK);
		loginInformation.setAlignItems(Alignment.CENTER);

		imgFlagSpain.addClickListener(e -> {
			idioma = new Idioma("Spain"); // creamos objeto idioma de tipo Spain
			Notification.show(formatearTexto("idioma")); // mostramo idioma seleccionado
			Application.lang_default = "Spain"; // idioma por defecto
			setLocale(new Locale("es", "ES")); // Locale lo creamos de tipo "Español | España"
			loginForm.setI18n(createI18n()); // personalizamos con el idioma seleccionado
			muestras_idiomas();
		});

		imgFlagUK.addClickListener(e -> {
			idioma = new Idioma("English"); // creamos objeto idioma de tipo English
			Notification.show(formatearTexto("idioma")); // mostramo idioma seleccionado
			Application.lang_default = "English"; // idioma por defecto
			setLocale(Locale.ENGLISH); // Locale lo creamos de tipo "Inglés| UK"
			loginForm.setI18n(createI18n()); // personalizamos con el idioma seleccionado
			muestras_idiomas();
		});

		return loginInformation;
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		// Read query parameters and show an error if a login attempt fails.
		if (beforeEnterEvent.getLocation()
				.getQueryParameters()
				.getParameters()
				.containsKey("error")) {
			loginForm.setError(true);
		}
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
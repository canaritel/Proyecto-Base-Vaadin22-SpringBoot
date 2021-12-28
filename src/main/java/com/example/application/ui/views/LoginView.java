package com.example.application.ui.views;

import java.util.Locale;

import com.example.application.Application;
import com.example.application.backend.data.idiomas.PrintText;
import com.example.application.backend.data.idiomas.PrintText.Color;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:application-${env}.properties", encoding = "UTF-8") // ruta_por_defecto_para_las_properties_usadas_en_la_internacionalización
@Route("login")
@PageTitle("Login | Cuponcito CRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver, LocaleChangeObserver {

	private LoginForm loginForm = new LoginForm(); // inicializamos_objeto_formulario_Login
	// private Idioma idioma = new Idioma(Application.lang_APP); //
	// inicializamos_objeto_con_idioma_default
	private final Locale LOCALE_ES = new Locale("es");
	private final Locale LOCALE_EN = new Locale("en");

	private String texto = "";

	// private DetectarIdioma detectaIdioma; //pendiente de implementar

	public LoginView() {
		if (Application.lang_default == null) {
			Application.lang_default = UI.getCurrent().getLocale().getDisplayLanguage();
		}
		addClassName("login-rich-content");
		crearLogin();
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		Image imgLogo = new Image("/images/cuponcito_opt.png", "alt text");
		imgLogo.setHeight("64px"); // ajustamos el tamaño de la imagen

		loginForm.setAction("login"); // Set the LoginForm action to "login" to post the_login_form_to_Spring_Security

		add(new H2(""), imgLogo, loginForm, personalizacionLogin());
	}

	// Desarrollamos e implementamos el uso de i18n internacionalización
	// https://vaadin.com/docs/v14/flow/advanced/tutorial-i18n-localization
	// https://github.com/vaadin-learning-center/flow-i18n-i18nprovider
	// https://maresmewebdevelopers.wordpress.com/2017/11/02/hola-mundo-con-multi-idioma-vamos-a-aprender-a-configurar-la-internacionalidad-con-i18n-en-spring-boot/

	private void crearLogin() {
		loginForm.getElement().getThemeList().add("light"); // lo ponemos estilo claro
		loginForm.setI18n(createI18n()); // creamos nuestro componente login personalizado a nuestro idioma
		loginForm.addForgotPasswordListener(
				event -> {
					PrintText.imprime(formatText("pendiente"), Color.GRIS);
				});
	}

	private LoginI18n createI18n() {
		final LoginI18n i18n = LoginI18n.createDefault();
		i18n.setHeader(new LoginI18n.Header());
		i18n.getForm().setTitle(formatText("acceda"));
		i18n.getForm().setSubmit(formatText("entrar"));
		i18n.getForm().setUsername(formatText("usuario"));
		i18n.getForm().setPassword(formatText("clave"));
		i18n.getForm().setForgotPassword(formatText("recordar"));
		i18n.getErrorMessage().setTitle(formatText("error_titulo"));
		i18n.getErrorMessage().setMessage(formatText("error_texto"));
		i18n.setAdditionalInformation(formatText("info_acceso"));

		return i18n;
	}

	private Component personalizacionLogin() {
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
			Application.locale_APP = LOCALE_ES; // nuevo método
			// Notification.show(formatText("idioma")); // mostramo idioma seleccionado
			PrintText.imprime(formatText("idioma_select"), Color.VERDE); // mostramos idioma seleccionado
			Application.lang_APP = "Spain"; // idioma por defecto
			loginForm.setI18n(createI18n()); // personalizamos con el idioma seleccionado
			idiomaDispositivo();
		});

		imgFlagUK.addClickListener(e -> {
			Application.locale_APP = LOCALE_EN; // nuevo método
			PrintText.imprime(formatText("idioma_select"), Color.VERDE); // mostramos idioma seleccionado
			Application.lang_APP = "English"; // idioma por defecto
			loginForm.setI18n(createI18n()); // personalizamos con el idioma seleccionado
			idiomaDispositivo();
		});

		return loginInformation;
	}

	private void idiomaDispositivo() {
		Application.lang_default = UI.getCurrent().getLocale().getDisplayLanguage();
		System.out.println(UI.getCurrent().getLocale().getLanguage());
		System.out.println("Dispositivo idioma: " + Application.lang_default);
	}

	// Este método permite acentos y tildes español de los campos traducidos
	// private String formatText(String property) {
	// String texto_formateado = new
	// String(idioma.getProperty(property).getBytes(Charset.forName("8859_1")));
	// return texto_formateado;
	// }

	private String formatText(String property) {
		// Este método devuelve el texto traducido.
		// Advierte está "depreciado" pero lo vamos a continuar usando
		return getTranslation(property, Application.locale_APP);
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

	@Override
	public void localeChange(LocaleChangeEvent arg0) {
		texto = getTranslation("saludo", LOCALE_EN);
		System.out.println(texto);

		texto = getTranslation("saludo", LOCALE_ES);
		System.out.println(texto);

	}

}
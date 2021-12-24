package com.example.application.views;

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

@Route("login")
@PageTitle("Login | Vaadin CRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

	private final LoginForm loginForm = new LoginForm();

	public LoginView() {
		addClassName("login-view");
		createLogin();
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		Image imgLogo = new Image("/images/cuponcito_opt.png", "alt text");
		imgLogo.setHeight("66px"); // ajustamos el tamaño de la imagen
		loginForm.setAction("login");
		add(imgLogo, new H2(" "), loginForm, createFlags());
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
		i18n.getForm().setUsername("Usuario");
		i18n.getForm().setTitle("Acceda a su cuenta");
		i18n.getForm().setSubmit("Entrar");
		i18n.getForm().setPassword("Contraseña");
		i18n.getForm().setForgotPassword("Olvidé contraseña");
		i18n.getErrorMessage().setTitle("Usuario/contraseña inválidos");
		i18n.getErrorMessage()
				.setMessage("Confirme su usuario y contraseña e inténtelo nuevamente.");
		i18n.setAdditionalInformation(
				"El acceso en modo usuario permite solo crear y editar. "
						+ "Los datos de acceso son: \"user | userpass\""
						+ ". Para el acceso de tipo administrador (con todos los"
						+ " permisos) deberá crear una cuenta.");

		return i18n;
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
			// language = new Language("Spain");
			// loginForm.setI18n(createSpanishI18n());
		});

		imgFlagUK.addClickListener(e -> {
			Notification.show("Ponemos los textos es inglés");
			// language = new Language("English");
			// loginForm.setI18n(createSpanishI18n());
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
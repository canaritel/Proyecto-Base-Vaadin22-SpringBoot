package com.example.application.data.idiomas;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.vaadin.flow.server.Constants;
import com.vaadin.flow.server.VaadinServlet;

@WebServlet(urlPatterns = "/*", name = "slot", asyncSupported = true, initParams = {
        @WebInitParam(name = Constants.I18N_PROVIDER, value = "com.example.application.data.idiomas.VaadinI18NProvider") })
public class ApplicationServiceInitListener extends VaadinServlet {

}

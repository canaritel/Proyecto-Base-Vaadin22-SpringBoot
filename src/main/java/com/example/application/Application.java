package com.example.application;

import java.util.Locale;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.vaadin.artur.helpers.LaunchUtil;

/**
 * The entry point of the Spring Boot application.
 */
@Theme("flowcrmtutorial")
@CssImport(value = "./styles/components/charts.css", themeFor = "vaadin-chart", include = "vaadin-chart-default-theme")
@CssImport(value = "./styles/components/floating-action-button.css", themeFor = "vaadin-button")
@CssImport(value = "./styles/components/grid.css", themeFor = "vaadin-grid")
@CssImport("./styles/lumo/border-radius.css")
@CssImport("./styles/lumo/icon-size.css")
@CssImport("./styles/lumo/margin.css")
@CssImport("./styles/lumo/padding.css")
@CssImport("./styles/lumo/shadow.css")
@CssImport("./styles/lumo/spacing.css")
@CssImport("./styles/lumo/typography.css")
@CssImport("./styles/misc/box-shadow-borders.css")
@CssImport(value = "./styles/styles.css", include = "lumo-badge")
// @JsModule("@vaadin/vaadin-lumo-styles/badge")

@PWA(name = "Cuponcito.com", shortName = "Coupon", iconPath = "images/logos/17.png", offlinePath = "offline.html", backgroundColor = "#233348", themeColor = "#233348", offlineResources = {
        "./images/offline.png" }) // la carpeta de las imágenes está en META-INFs

@SpringBootApplication
@EnableAutoConfiguration
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")

public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    public static String lang_default;// idoma_del_dispositivo_por_defecto
    public static String lang_APP = "Spain"; // donde guardamos el idioma seleccionado de la APP
    public static Locale locale_APP = new Locale("es");

    /*
     * ***** DECLARAMOS LOS VALORES Y PROPIEDADES PERSISTENCIA EN OBJECTDB ****
     * DE ESTA FORMA NO REQUERIMOS EL FICHERO .XML
     */
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactoryBean() {
        return Persistence.createEntityManagerFactory(
                "objectdb://91.121.134.199:6136/test-spring-jpa4.tmp;drop;user=tonii;password=gs21612161C");
        // "objectdb:test-spring-jpa.tmp;drop;user=admin;password=admin");
        // Las BD con extensión odb no se borrarán con "drop", si las "tmp"
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    @ConfigurationProperties("app.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

}

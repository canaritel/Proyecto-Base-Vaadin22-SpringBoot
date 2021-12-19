package com.example.application;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

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
@SpringBootApplication
@EnableAutoConfiguration
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@Theme("flowcrmtutorial")
@PWA(name = "VaadinCRM", shortName = "CRM", offlinePath = "offline.html", offlineResources = { "./images/offline.png" })
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    /*
     * ***** DECLARAMOS LOS VALORES Y PROPIEDADES PERSISTENCIA EN OBJECTDB ****
     * DE ESTA FORMA NO REQUERIMOS EL FICHERO .XML
     */
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactoryBean() {
        return Persistence.createEntityManagerFactory(
                "objectdb://91.121.134.199:6136/test-spring-jpa3.tmp;drop;user=tonii;password=gs21612161C");
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

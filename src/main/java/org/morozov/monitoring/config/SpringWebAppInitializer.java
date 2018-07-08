package org.morozov.monitoring.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.morozov.monitoring.controllers.MonitoringController;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class SpringWebAppInitializer implements WebApplicationInitializer {

    private static final Logger logger = LogManager.getLogger(MonitoringController.class);

    @Override
    public void onStartup(ServletContext servletContext) {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("classpath:WEB-INF/beans.xml");
        appContext.setServletContext(servletContext);
        appContext.refresh();
        logger.info("Context is initialized...");

        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("SpringDispatcher", new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        logger.info("Application is ready...");
    }
}

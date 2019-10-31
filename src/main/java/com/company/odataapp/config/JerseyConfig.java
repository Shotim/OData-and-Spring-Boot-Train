package com.company.odataapp.config;

import com.company.odataapp.odatajpaservicefactory.PeopleODataJPAServiceFactory;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.apache.olingo.odata2.core.rest.app.ODataApplication;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

/**
 * Jersey JAX-RS configuration
 *
 * @author Philippe
 */
@Component
@ApplicationPath("/odata")
public class JerseyConfig extends ResourceConfig {


    public JerseyConfig(PeopleODataJPAServiceFactory serviceFactory, EntityManagerFactory emf) {

        ODataApplication app = new ODataApplication();

        app
                .getClasses()
                .forEach(c -> {
                    // Avoid using the default RootLocator, as we want
                    // a Spring Managed one
                    if (!ODataRootLocator.class.isAssignableFrom(c)) {
                        register(c);
                    }
                });

        register(new PeopleRootLocator(serviceFactory));
        register(new PeopleODataJPAServiceFactory.EntityManagerFilter(emf));
    }

    @Path("/")
    public static class PeopleRootLocator extends ODataRootLocator {

        private PeopleODataJPAServiceFactory serviceFactory;

        public PeopleRootLocator(PeopleODataJPAServiceFactory serviceFactory) {
            this.serviceFactory = serviceFactory;
        }

        @Override
        public ODataServiceFactory getServiceFactory() {
            return this.serviceFactory;
        }

    }

}
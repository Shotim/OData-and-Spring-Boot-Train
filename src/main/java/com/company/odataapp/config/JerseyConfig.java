package com.company.odataapp.config;

import com.company.odataapp.entitymanagerfilter.EntityManagerFilter;
import com.company.odataapp.odatajpaservicefactory.PeopleODataJPAServiceFactory;
import com.company.odataapp.peoplerootlocator.PeopleRootLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/odata")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(PeopleODataJPAServiceFactory serviceFactory, EntityManagerFactory emf) {
        register(new PeopleRootLocator(serviceFactory));
        register(new EntityManagerFilter(emf));
    }
}
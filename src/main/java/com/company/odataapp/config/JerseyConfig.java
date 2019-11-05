package com.company.odataapp.config;

import com.company.odataapp.odatajpaservicefactory.PeopleODataJPAServiceFactory;
import lombok.AllArgsConstructor;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Component
@ApplicationPath("/odata")
public class JerseyConfig extends ResourceConfig {


    public JerseyConfig(PeopleODataJPAServiceFactory serviceFactory, EntityManagerFactory emf) {
        register(new PeopleRootLocator(serviceFactory));
        register(new EntityManagerFilter(emf));
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

    @Provider
    @AllArgsConstructor
    public static class EntityManagerFilter implements ContainerRequestFilter, ContainerResponseFilter {

        public static final String EM_REQUEST_ATTRIBUTE = EntityManagerFilter.class.getName() + "_ENTITY_MANAGER";
        public static final String HTTP_GET_METHOD = "GET";
        private final EntityManagerFactory entityManagerFactory;

        @Context
        private HttpServletRequest httpServletRequest;

        public EntityManagerFilter(EntityManagerFactory entityManagerFactory) {
            this.entityManagerFactory = entityManagerFactory;
        }

        @Override
        public void filter(ContainerRequestContext containerRequestContext) throws IOException {
            EntityManager entityManager = this.entityManagerFactory.createEntityManager();
            httpServletRequest.setAttribute(EM_REQUEST_ATTRIBUTE, entityManager);
            if (!HTTP_GET_METHOD.equalsIgnoreCase(containerRequestContext.getMethod())) {
                entityManager.getTransaction().begin();
            }
        }

        @Override
        public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
            EntityManager entityManager = (EntityManager) httpServletRequest.getAttribute(EM_REQUEST_ATTRIBUTE);
            if (!HTTP_GET_METHOD.equalsIgnoreCase(containerRequestContext.getMethod())) {
                EntityTransaction entityTransaction = entityManager.getTransaction();
                if (entityTransaction.isActive() && !entityTransaction.getRollbackOnly()) {
                    entityTransaction.commit();
                }
            }

            entityManager.close();
        }
    }
}
package com.company.odataapp.odatajpaservicefactory;

import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import java.io.IOException;

public class PeopleODataJPAServiceFactory extends ODataJPAServiceFactory {
    @Override
    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
        ODataJPAContext oDataJPAContext = getODataJPAContext();
        ODataContext oDataContext = oDataJPAContext.getODataContext();
        HttpServletRequest request = (HttpServletRequest) oDataContext.getParameter(ODataContext.HTTP_SERVLET_REQUEST_OBJECT);
        EntityManager entityManager = (EntityManager) request.getAttribute(EntityManagerFilter.EM_REQUEST_ATTRIBUTE);

        oDataJPAContext.setEntityManager(entityManager);
        oDataJPAContext.setPersistenceUnitName("default");
        oDataJPAContext.setContainerManaged(true);
        return oDataJPAContext;
    }

    public static class EntityManagerFilter implements ContainerRequestFilter, ContainerResponseFilter {

        public static final String EM_REQUEST_ATTRIBUTE = EntityManagerFilter.class.getName() + "_ENTITY_MANAGER";
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
            if (!"GET".equalsIgnoreCase(containerRequestContext.getMethod())) {
                entityManager.getTransaction().begin();
            }
        }

        @Override
        public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
            EntityManager entityManager = (EntityManager) httpServletRequest.getAttribute(EM_REQUEST_ATTRIBUTE);
            if (!"GET".equalsIgnoreCase(containerRequestContext.getMethod())) {
                EntityTransaction entityTransaction = entityManager.getTransaction();
                if (entityTransaction.isActive() && !entityTransaction.getRollbackOnly()) {
                    entityTransaction.commit();
                }
            }

            entityManager.close();
        }
    }
}

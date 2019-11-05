package com.company.odataapp.entitymanagerfilter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class EntityManagerFilter implements ContainerRequestFilter, ContainerResponseFilter {

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

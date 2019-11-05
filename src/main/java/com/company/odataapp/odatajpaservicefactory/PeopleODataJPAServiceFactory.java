package com.company.odataapp.odatajpaservicefactory;

import com.company.odataapp.entitymanagerfilter.EntityManagerFilter;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

@Component
public class PeopleODataJPAServiceFactory extends ODataJPAServiceFactory {

    public PeopleODataJPAServiceFactory() {
        setDetailErrors(true);
    }

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


}

package com.company.odataapp.peoplerootlocator;

import com.company.odataapp.odatajpaservicefactory.PeopleODataJPAServiceFactory;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;

import javax.ws.rs.Path;

@Path("/")
public class PeopleRootLocator extends ODataRootLocator {

    private PeopleODataJPAServiceFactory serviceFactory;

    public PeopleRootLocator(PeopleODataJPAServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public ODataServiceFactory getServiceFactory() {
        return this.serviceFactory;
    }

}
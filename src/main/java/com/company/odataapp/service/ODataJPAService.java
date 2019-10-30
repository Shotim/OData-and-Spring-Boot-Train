package com.company.odataapp.service;

import org.apache.olingo.odata2.api.edm.Edm;
import org.apache.olingo.odata2.api.exception.ODataException;

import java.io.IOException;

public interface ODataJPAService {

    Edm read(String serviceURL) throws IOException, ODataException;


}
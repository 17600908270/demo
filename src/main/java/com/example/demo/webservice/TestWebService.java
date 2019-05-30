package com.example.demo.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
@WebService
public interface TestWebService {
    @WebMethod
    @WebResult(name = "String")
    public String sendMessage(@WebParam(name = "username") String name);

}


package com;

import com.example.demo.webservice.TestWebService;
import com.ztesoft.isap.server.webService.bnetV3.CrmForBnetV3;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.EndpointException;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Autowired
    private Bus  bus;

    @Autowired
    private CrmForBnetV3 crmForBnetV3ImplPortImpl;
    @Autowired
    private TestWebService service;
    @Bean
    public Endpoint endpoint() throws EndpointException {

        EndpointImpl endpoint=new EndpointImpl(bus, crmForBnetV3ImplPortImpl);

        endpoint.publish("/CrmForBnetV3ImplPort");
        return  endpoint;

    }
    @Bean
    public Endpoint endpoint1() throws EndpointException {

        EndpointImpl endpoint=new EndpointImpl(bus, service);

        endpoint.publish("/service");
        return  endpoint;

    }
}

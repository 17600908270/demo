package com.example.demo.webservice;

import org.apache.cxf.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class cxfInterceptorConfig {
    @Autowired
    private Bus bus;
    @Autowired
    private TestWebService service;
    //必须要有
   /* @Bean
    public ServletRegistrationBean cxfServlet(){
        return new ServletRegistrationBean(new CXFServlet(),"/services/*");
    }*/

   /* @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint=new EndpointImpl(bus,service);
        endpoint.publish("/testWebService");
         //打印报文日志拦截器
        endpoint.getInInterceptors().add(new CAbstractPhaseInterceptor(Phase.RECEIVE));
        endpoint.getOutInterceptors().add(new clientAuthInterceptor());

        return endpoint;
    }
*/
}

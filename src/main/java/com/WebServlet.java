package com;

import org.apache.axis.transport.http.AxisServlet;


@javax.servlet.annotation.WebServlet(
        urlPatterns =  "/NPIServer/dep/*",
        loadOnStartup = 1,
        name = "AxisServlet"
)
public class WebServlet extends AxisServlet {


}


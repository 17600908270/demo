package com.example.demo;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/gathering")
public class HelloController {


    @RequestMapping(method= RequestMethod.GET)
    public String findAll(){

        return "dd";
    }


    @RequestMapping(value = "/postTest",method = RequestMethod.GET)
    public  String  postTest(HttpServletRequest request) throws IOException {

        ServletInputStream ris = request.getInputStream();

        StringBuilder content = new StringBuilder();
        byte[] b = new byte[1024];
        int lens = -1;
        while ((lens = ris.read(b)) > 0) {
            content.append(new String(b, 0, lens));
        }
        String strcont = content.toString();// 内容
        System.out.println(strcont);

        return  strcont;

    }
    @RequestMapping(value = "/getTest",method = RequestMethod.GET)
    public  String getTest(String access_token,String openid){

        System.out.println(access_token+ " ::" +openid);
        return access_token+" : "+openid;
    }


}

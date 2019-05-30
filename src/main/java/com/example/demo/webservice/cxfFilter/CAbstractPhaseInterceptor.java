package com.example.demo.webservice.cxfFilter;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CAbstractPhaseInterceptor extends AbstractPhaseInterceptor {

    public CAbstractPhaseInterceptor(String receive) {
        super(receive);
    }

    @Override
    public void handleMessage(Message message) throws Fault {


        InputStream is = message.getContent(InputStream.class);
        if (is != null) {
            try {
                String str = IOUtils.toString(is);
                // 原请求报文
                System.out.println("====> request xml=\r\n" + str);

                // 把siebel格式的报文替换成符合cxf带前缀的命名空间
                str =str.replace("gg","dd");

                // 替换后的报文
                System.out.println("====> replace xml=\r\n" + str);

                InputStream ism = new ByteArrayInputStream(str.getBytes("UTF-8"));
                message.setContent(InputStream.class, ism);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

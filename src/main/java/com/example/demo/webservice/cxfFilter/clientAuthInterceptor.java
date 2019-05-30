package com.example.demo.webservice.cxfFilter;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class clientAuthInterceptor extends AbstractPhaseInterceptor {


    public clientAuthInterceptor() {
        super(Phase.PRE_PROTOCOL);
    }


    @Override
    public void handleMessage(Message message) throws Fault {
        OutputStream os = message.getContent(OutputStream.class);

        CachedStream cs = new CachedStream();

        message.setContent(OutputStream.class, cs);

        message.getInterceptorChain().doIntercept(message);

        CachedOutputStream csnew = (CachedOutputStream) message.getContent(OutputStream.class);
        String xml = null;
        try {
            InputStream in = csnew.getInputStream();

            xml = IOUtils.toString(in);

        System.out.println("replaceBegin"+xml);

            xml=xml.replace("dd","你好dd");

        System.out.println("replaceAfter"+xml);
        //这里对xml做处理，处理完后同理，写回流中
        IOUtils.copy(new ByteArrayInputStream(xml.getBytes("UTF-8")), os);

        cs.close();
        os.flush();

        message.setContent(OutputStream.class, os);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private class CachedStream extends CachedOutputStream {

        public CachedStream() {

            super();

        }

        protected void doFlush() throws IOException {

            currentStream.flush();

        }

        protected void doClose() throws IOException {

        }

        protected void onWrite() throws IOException {

        }

    }
}

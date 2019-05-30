package com.example.demo;

import com.example.demo.util.Dom4jUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSub {

    public static void main(String[] args) {
   /*String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?> <QueryWsInputInfo> <Worksheet WsCode='201905281653281000'> <GeneralInfo>"
         +"<ErrCode>0</ErrCode><WsMessage>成功</WsMessage></GeneralInfo></Worksheet> </QueryWsInputInfo>";
        Document doc = null;
        SAXReader saxReader = new SAXReader();
        StringReader reader = new StringReader(xml.toString());
        try {
            doc = saxReader.read(reader);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();
        Element errCode = (Element)root
                .selectSingleNode("/QueryWsInputInfo/Worksheet/GeneralInfo/ErrCode");
        Element workSheet = (Element)root
                .selectSingleNode("/QueryWsInputInfo/Worksheet");
        String  id = workSheet.attributeValue("WsCode");

        Element wsMessage = (Element)root
                .selectSingleNode("/QueryWsInputInfo/Worksheet/GeneralInfo/WsMessage");

        System.out.println(id);*/
/*
String response="{\"msg\":{\"ResultMsg\":{\"DstSysId\":\"55\",\"ServiceId\":\"27211905280638550000000000000003\",\"RetCode\":\"0\",\"RetDesc\":\"成功\"}}}";

        int l = response.indexOf("\"", response.indexOf("ServiceId") + "ServiceId".length() + 1);
        int r = response.indexOf("\"", l + 1);
        String retServiceId = response.substring(l + 1, r);

        System.out.println(retServiceId);
        //lock.lock();
        l = response.indexOf("\"", response.indexOf("RetCode") + "RetCode".length() + 1);
        r = response.indexOf("\"", l + 1);

        System.out.println("code "+response.substring(l + 1,r));
        l = response.indexOf("\"", response.indexOf("RetDesc") + "RetDesc".length() + 1);
        r = response.indexOf("\"", l + 1);
       // setResultDesc(response.substring(l + 1, r));

        System.out.println("desc "+response.substring(l + 1, r));*/



       /*    String ss="<ResultCode>0000</ResultCode><ResultMsg>成功</ResultMsg>";

        String resultxml1 = ss.replace("\n", "").replace("\r", "").replace(" ", "");

        String regex = "<ResultCode>(.*?)</ResultCode><ResultMsg>(.*?)</ResultMsg>";

        RE re = new RE(regex);

        boolean isMatch = re.match(resultxml1);
        if (isMatch) {
            String code = re.getParen(1);
            String desc = re.getParen(2);

            System.out.println("code "+code );
            System.out.println("desc "+desc );
            if (code.trim().equals("0000")) {
                String regexsvc = "<RspCode>(.*?)</RspCode><ResultMsg>(.*?)</ResultMsg>";
                RE resvc = new RE(regexsvc);
                boolean isMatchsvc = resvc.match(resultxml1);
               boolean isSuccess = true;
                if (isMatchsvc) {
                    code = resvc.getParen(1);
                    desc = resvc.getParen(2);
                    if (!code.equals("0000")) {

                        System.out.println("h code "+code );
                        System.out.println("h desc "+desc);
                        isSuccess = false;
                    }
                }
            }
        }
*/
     /*  String transactionId="dd";


        String ss=transactionId.substring(0, 14) + "02" + transactionId.substring(16);

        System.out.println(ss);*/


        String xml="<root>" +
                "<TransactionID>888888888888888888888</TransactionID>" +
                "<OldTransactionID>2019052916030401101</OldTransactionID>" +
                "<InvokeResult>1</InvokeResult>" +
                "<InvokeMsg>成功</InvokeMsg>" +
                "</root>";

        Document doc = null;
        SAXReader saxReader = new SAXReader();
        StringReader reader = new StringReader(xml.toString());
        try {
            doc = saxReader.read(reader);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();
      String   txId = root.selectSingleNode("TransactionID").getText();
      String  oldtxId = root.selectSingleNode("OldTransactionID").getText();

        String resCode = root.selectSingleNode(
                "InvokeResult").getText();
        String resDesc = "";
        if (root.selectSingleNode(
                "InvokeMsg") != null) {
            resDesc = root.selectSingleNode(
                    "InvokeMsg").getText();
        }
        String bnetForNpi = oldtxId + "&" + resCode + "&" + resDesc + "&" + "1";
        System.out.println(bnetForNpi+ "dd" +txId) ;
             boolean isSuccess=true;

        String result = createResponse(isSuccess, txId);
        System.out.println(result);


        System.out.println("dd");




    }


    public static String createResponse(boolean isSuccess, String transactionId) {
        System.out.println(isSuccess);
        System.out.println(transactionId);
        try {
            Element rspType = null;
            Element rspCode = null;
            Element rspDesc = null;

            Document doc = Dom4jUtil.createDocument();

            Element root = doc.addElement("Root");
            Element timeStamp = root.addElement("TimeStamp");
            Element transactionID = root.addElement("TransactionID");

            Element resultCode = root.addElement("ResultCode");
            Element resultMsg = root.addElement("ResultMsg");
            transactionID.setText(transactionId.substring(0, 14) + "02" + transactionId.substring(16));
            timeStamp.setText(new SimpleDateFormat("yyyyMMddHHssmm").format(new Date()));

            if (isSuccess) {
                resultCode.setText("00000");
                resultMsg.setText("success");
            } else {
                resultCode.setText("0601");
                resultMsg.setText("报文异常");
            }

            String docXml = doc.asXML();

            String _return = docXml;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    }

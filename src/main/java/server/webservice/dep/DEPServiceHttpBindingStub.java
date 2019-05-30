package server.webservice.dep;

import com.example.demo.POJO.User;
import com.example.demo.util.Dom4jUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DEPServiceHttpBindingStub
        implements DEPServicePortType
{
    private static Logger log = Logger.getLogger(DEPServiceHttpBindingStub.class.getName());

    private final String DEP_CODE = "BUS14006";

    public String exchange(String in0) {
        log.info("dep req :\n" + in0);
         log.info("dd"+SpringContextUtils.getBean(User.class));
        //System.out.println(user.getClass());
        Document doc = null;
        try {
            doc = Dom4jUtil.createDOM4jDocument(in0, false, null);
        }
        catch (Exception e) {
            log.error("dep - create docment error.", e);
            return in0;
        }

        Element root = doc.getRootElement();

        String txId = root.selectSingleNode("TcpCont/TransactionID").getText();
        String bizEventNbr = root.selectSingleNode(
                "SvcCont/BPMOrderEvent/BPMOrder/BizEventNbr").getText();
        String resType = root.selectSingleNode(
                "SvcCont/BPMOrderEvent/BPMOrder/RspType").getText();
        String resCode = root.selectSingleNode(
                "SvcCont/BPMOrderEvent/BPMOrder/RspCode").getText();
        String resDesc = root.selectSingleNode(
                "SvcCont/BPMOrderEvent/BPMOrder/RspDesc").getText();

        String busCode = "";
        Node busCodeNode = root.selectSingleNode("TcpCont/BusCode");
        if (busCodeNode != null) {
            busCode = busCodeNode.getText();
        }
        String depForNpi = bizEventNbr + "&" + resCode + "&" + resDesc + "&" + resType;
        try
        {
           // IHandler handle = null;
            String dep = "";
          /*  if ("0000".equals(resCode)) {
                log.info("dep - 收到[竣工通知]");

                if (bizEventNbr.startsWith("0")) {
                    dep = "deptf";
                } else if ("BUS14006".equals(busCode)) {
                    log.info("the device is deptfnew(707) when buscode is BUS14006...");
                    dep = "deptfnew";
                } else if ("BUS14004".equals(busCode)) {
                    log.info("the device is deptfnew(707) when buscode is BUS14004...");
                    dep = "depdj";
                }
                else {
                    dep = "dep";
                }
                handle = (IHandler)ServiceLocator.lookup(dep);
                handle.handle(depForNpi);

                return createResponse(txId);
            }
            System.out.println("busCode===========" + busCode);
            if ("BUS14004".equals(busCode)) {
                log.info("the device is deptdj(707) when buscode is BUS14004...");
                dep = "depdj";
                handle = (IHandler)ServiceLocator.lookup(dep);
                handle.handle(depForNpi);

                return createResponse(txId);
            }
            System.out.println("bizEventNbr===========" + bizEventNbr);
            if (bizEventNbr.startsWith("0")) {
                dep = "deptf";
                handle = (IHandler)ServiceLocator.lookup(dep);
                handle.handle(depForNpi);

                return createResponse(txId);
            }

            if (("6".equals(resType)) && ("6000".equals(resCode))) {
                log.info("dep - 收到[同意退单成功]");

                return createResponse(txId);
            }

            if ("2".equals(resType)) {
                log.info("dep - 收到[施工失败-退单]");
                if (bizEventNbr.startsWith("0")) {
                    dep = "deptf";
                }
                else {
                    dep = "dep";
                }
                handle = (IHandler)ServiceLocator.lookup(dep);
                handle.handle(depForNpi);

                return createResponse(txId);
            }

            log.warn("dep unkown request.");
            handle = (IHandler)ServiceLocator.lookup("dep");
            handle.handle(depForNpi);*/
            return createResponse(txId);
        } catch (Exception e) {
        }
        return createResponse(txId, "9", "999", "exception:" + "55");
    }

    public String createResponse(String transactionId)
    {
        return createResponse(transactionId, "0", "0000", "success");
    }

    public String createResponse(String transactionId, String resType, String resCode, String resDesc) {
        Element rspType = null;
        Element rspCode = null;
        Element rspDesc = null;

        Document doc = Dom4jUtil.createDocument();

        Element contractRoot = doc.addElement("ContractRoot");
        Element tcpCont = contractRoot.addElement("TcpCont");
        Element actionCode = tcpCont.addElement("ActionCode");

        Element transactionID = tcpCont.addElement("TransactionID");
        Element rspTime = tcpCont.addElement("RspTime");
        Element response = tcpCont.addElement("Response");

        rspType = response.addElement("RspType");
        rspCode = response.addElement("RspCode");
        rspDesc = response.addElement("RspDesc");

        actionCode.setText("1");
        transactionID.setText(transactionId);
        rspTime.setText(new SimpleDateFormat("yyyyMMddHHssmm").format(new Date()));

        rspType.setText("0");
        rspCode.setText("0000");
        rspDesc.setText("success");

        String docXml = doc.asXML();
        log.info("dep ack:\n" + docXml);

        return docXml;
    }

}
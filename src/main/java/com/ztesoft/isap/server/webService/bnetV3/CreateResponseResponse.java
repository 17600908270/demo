
package com.ztesoft.isap.server.webService.bnetV3;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="createResponseReturn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "createResponseReturn"
})
@XmlRootElement(name = "createResponseResponse", namespace = "http://bnetV3.webservice.server")
public class CreateResponseResponse {

    @XmlElement(namespace = "http://bnetV3.webservice.server", required = true)
    protected String createResponseReturn;

    /**
     * 获取createResponseReturn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateResponseReturn() {
        return createResponseReturn;
    }

    /**
     * 设置createResponseReturn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateResponseReturn(String value) {
        this.createResponseReturn = value;
    }

}

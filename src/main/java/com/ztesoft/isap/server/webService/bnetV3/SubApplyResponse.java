
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
 *         &lt;element name="SubApplyReturn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "subApplyReturn"
})
@XmlRootElement(name = "SubApplyResponse")
public class SubApplyResponse {

    @XmlElement(name = "SubApplyReturn", required = true)
    protected String subApplyReturn;

    /**
     * 获取subApplyReturn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubApplyReturn() {
        return subApplyReturn;
    }

    /**
     * 设置subApplyReturn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubApplyReturn(String value) {
        this.subApplyReturn = value;
    }

}

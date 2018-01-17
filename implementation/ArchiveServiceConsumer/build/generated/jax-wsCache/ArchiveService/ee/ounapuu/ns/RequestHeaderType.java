
package ee.ounapuu.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apiToken" type="{http://ns.ounapuu.ee}NonEmptyString"/>
 *         &lt;element name="requestId" type="{http://ns.ounapuu.ee}NonEmptyString"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestHeaderType", propOrder = {
    "apiToken",
    "requestId"
})
public class RequestHeaderType {

    @XmlElement(required = true)
    protected String apiToken;
    @XmlElement(required = true)
    protected String requestId;

    /**
     * Gets the value of the apiToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApiToken() {
        return apiToken;
    }

    /**
     * Sets the value of the apiToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApiToken(String value) {
        this.apiToken = value;
    }

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

}

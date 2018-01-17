
package ee.ounapuu.ns;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ArchiveService", targetNamespace = "http://ns.ounapuu.ee", wsdlLocation = "http://localhost:8080/ArchiveService/ArchiveService?wsdl")
public class ArchiveService
    extends Service
{

    private final static URL ARCHIVESERVICE_WSDL_LOCATION;
    private final static WebServiceException ARCHIVESERVICE_EXCEPTION;
    private final static QName ARCHIVESERVICE_QNAME = new QName("http://ns.ounapuu.ee", "ArchiveService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ArchiveService/ArchiveService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ARCHIVESERVICE_WSDL_LOCATION = url;
        ARCHIVESERVICE_EXCEPTION = e;
    }

    public ArchiveService() {
        super(__getWsdlLocation(), ARCHIVESERVICE_QNAME);
    }

    public ArchiveService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ARCHIVESERVICE_QNAME, features);
    }

    public ArchiveService(URL wsdlLocation) {
        super(wsdlLocation, ARCHIVESERVICE_QNAME);
    }

    public ArchiveService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ARCHIVESERVICE_QNAME, features);
    }

    public ArchiveService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ArchiveService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ArchiveServicePortType
     */
    @WebEndpoint(name = "ArchiveServicePort")
    public ArchiveServicePortType getArchiveServicePort() {
        return super.getPort(new QName("http://ns.ounapuu.ee", "ArchiveServicePort"), ArchiveServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ArchiveServicePortType
     */
    @WebEndpoint(name = "ArchiveServicePort")
    public ArchiveServicePortType getArchiveServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ns.ounapuu.ee", "ArchiveServicePort"), ArchiveServicePortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ARCHIVESERVICE_EXCEPTION!= null) {
            throw ARCHIVESERVICE_EXCEPTION;
        }
        return ARCHIVESERVICE_WSDL_LOCATION;
    }

}

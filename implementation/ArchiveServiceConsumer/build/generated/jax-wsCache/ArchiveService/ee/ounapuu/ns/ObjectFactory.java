
package ee.ounapuu.ns;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ee.ounapuu.ns package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetArchiveResourcesForArchivalProjectResponse_QNAME = new QName("http://ns.ounapuu.ee", "getArchiveResourcesForArchivalProjectResponse");
    private final static QName _AddArchiveResourceToArchivalProjectResponse_QNAME = new QName("http://ns.ounapuu.ee", "addArchiveResourceToArchivalProjectResponse");
    private final static QName _GetArchiveResourceResponse_QNAME = new QName("http://ns.ounapuu.ee", "getArchiveResourceResponse");
    private final static QName _AddArchivalProjectResponse_QNAME = new QName("http://ns.ounapuu.ee", "addArchivalProjectResponse");
    private final static QName _GetArchivalProjectListResponse_QNAME = new QName("http://ns.ounapuu.ee", "getArchivalProjectListResponse");
    private final static QName _GetArchivalProjectResponse_QNAME = new QName("http://ns.ounapuu.ee", "getArchivalProjectResponse");
    private final static QName _AddArchiveResourceResponse_QNAME = new QName("http://ns.ounapuu.ee", "addArchiveResourceResponse");
    private final static QName _GetArchiveResourceListResponse_QNAME = new QName("http://ns.ounapuu.ee", "getArchiveResourceListResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ee.ounapuu.ns
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetArchivalProjectRequest }
     * 
     */
    public GetArchivalProjectRequest createGetArchivalProjectRequest() {
        return new GetArchivalProjectRequest();
    }

    /**
     * Create an instance of {@link RequestHeaderType }
     * 
     */
    public RequestHeaderType createRequestHeaderType() {
        return new RequestHeaderType();
    }

    /**
     * Create an instance of {@link AddArchivalProjectRequest }
     * 
     */
    public AddArchivalProjectRequest createAddArchivalProjectRequest() {
        return new AddArchivalProjectRequest();
    }

    /**
     * Create an instance of {@link ArchiveResourceType }
     * 
     */
    public ArchiveResourceType createArchiveResourceType() {
        return new ArchiveResourceType();
    }

    /**
     * Create an instance of {@link ArchivalProjectType }
     * 
     */
    public ArchivalProjectType createArchivalProjectType() {
        return new ArchivalProjectType();
    }

    /**
     * Create an instance of {@link GetArchiveResourcesForArchivalProjectRequest }
     * 
     */
    public GetArchiveResourcesForArchivalProjectRequest createGetArchiveResourcesForArchivalProjectRequest() {
        return new GetArchiveResourcesForArchivalProjectRequest();
    }

    /**
     * Create an instance of {@link ArchiveResourceListType }
     * 
     */
    public ArchiveResourceListType createArchiveResourceListType() {
        return new ArchiveResourceListType();
    }

    /**
     * Create an instance of {@link AddArchiveResourceRequest }
     * 
     */
    public AddArchiveResourceRequest createAddArchiveResourceRequest() {
        return new AddArchiveResourceRequest();
    }

    /**
     * Create an instance of {@link GetArchivalProjectListRequest }
     * 
     */
    public GetArchivalProjectListRequest createGetArchivalProjectListRequest() {
        return new GetArchivalProjectListRequest();
    }

    /**
     * Create an instance of {@link AddArchiveResourceToArchivalProjectRequest }
     * 
     */
    public AddArchiveResourceToArchivalProjectRequest createAddArchiveResourceToArchivalProjectRequest() {
        return new AddArchiveResourceToArchivalProjectRequest();
    }

    /**
     * Create an instance of {@link ArchivalProjectListType }
     * 
     */
    public ArchivalProjectListType createArchivalProjectListType() {
        return new ArchivalProjectListType();
    }

    /**
     * Create an instance of {@link GetArchiveResourceRequest }
     * 
     */
    public GetArchiveResourceRequest createGetArchiveResourceRequest() {
        return new GetArchiveResourceRequest();
    }

    /**
     * Create an instance of {@link GetArchiveResourceListRequest }
     * 
     */
    public GetArchiveResourceListRequest createGetArchiveResourceListRequest() {
        return new GetArchiveResourceListRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchiveResourceListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.ounapuu.ee", name = "getArchiveResourcesForArchivalProjectResponse")
    public JAXBElement<ArchiveResourceListType> createGetArchiveResourcesForArchivalProjectResponse(ArchiveResourceListType value) {
        return new JAXBElement<ArchiveResourceListType>(_GetArchiveResourcesForArchivalProjectResponse_QNAME, ArchiveResourceListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchiveResourceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.ounapuu.ee", name = "addArchiveResourceToArchivalProjectResponse")
    public JAXBElement<ArchiveResourceType> createAddArchiveResourceToArchivalProjectResponse(ArchiveResourceType value) {
        return new JAXBElement<ArchiveResourceType>(_AddArchiveResourceToArchivalProjectResponse_QNAME, ArchiveResourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchiveResourceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.ounapuu.ee", name = "getArchiveResourceResponse")
    public JAXBElement<ArchiveResourceType> createGetArchiveResourceResponse(ArchiveResourceType value) {
        return new JAXBElement<ArchiveResourceType>(_GetArchiveResourceResponse_QNAME, ArchiveResourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchivalProjectType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.ounapuu.ee", name = "addArchivalProjectResponse")
    public JAXBElement<ArchivalProjectType> createAddArchivalProjectResponse(ArchivalProjectType value) {
        return new JAXBElement<ArchivalProjectType>(_AddArchivalProjectResponse_QNAME, ArchivalProjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchivalProjectListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.ounapuu.ee", name = "getArchivalProjectListResponse")
    public JAXBElement<ArchivalProjectListType> createGetArchivalProjectListResponse(ArchivalProjectListType value) {
        return new JAXBElement<ArchivalProjectListType>(_GetArchivalProjectListResponse_QNAME, ArchivalProjectListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchivalProjectType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.ounapuu.ee", name = "getArchivalProjectResponse")
    public JAXBElement<ArchivalProjectType> createGetArchivalProjectResponse(ArchivalProjectType value) {
        return new JAXBElement<ArchivalProjectType>(_GetArchivalProjectResponse_QNAME, ArchivalProjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchiveResourceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.ounapuu.ee", name = "addArchiveResourceResponse")
    public JAXBElement<ArchiveResourceType> createAddArchiveResourceResponse(ArchiveResourceType value) {
        return new JAXBElement<ArchiveResourceType>(_AddArchiveResourceResponse_QNAME, ArchiveResourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArchiveResourceListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.ounapuu.ee", name = "getArchiveResourceListResponse")
    public JAXBElement<ArchiveResourceListType> createGetArchiveResourceListResponse(ArchiveResourceListType value) {
        return new JAXBElement<ArchiveResourceListType>(_GetArchiveResourceListResponse_QNAME, ArchiveResourceListType.class, null, value);
    }

}

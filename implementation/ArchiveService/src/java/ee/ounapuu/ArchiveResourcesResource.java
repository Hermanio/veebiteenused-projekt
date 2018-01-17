/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ounapuu;

import ee.ounapuu.ns.*;
import java.math.BigInteger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author toks
 */
@Path("resources")
public class ArchiveResourcesResource {

    @Context
    private UriInfo context;

    @GET
    @Path("{id : \\d+}")
    @Produces("application/json")
    public ArchiveResourceType getArchiveResource(
            @QueryParam("apiToken") String apiToken,
            @QueryParam("requestId") String requestId,
            @PathParam("id") BigInteger archiveResourceId) {

        ArchiveWebService service = new ArchiveWebService();

        GetArchiveResourceRequest request = new GetArchiveResourceRequest();
        RequestHeaderType header = new RequestHeaderType();
        header.setApiToken(apiToken);
        header.setRequestId(requestId);
        request.setRequestHeader(header);
        request.setResourceId(archiveResourceId);
        return service.getArchiveResource(request);
    }

    @GET
    @Produces("application/json")
    public ArchiveResourceListType getArchiveResourceList(
            @QueryParam("apiToken") String apiToken,
            @QueryParam("requestId") String requestId,
            @QueryParam("name") String resourceName,
            @QueryParam("type") ResourceType type,
            @QueryParam("submitter") String submitter) {

        ArchiveWebService service = new ArchiveWebService();

        GetArchiveResourceListRequest request = new GetArchiveResourceListRequest();
        RequestHeaderType header = new RequestHeaderType();
        header.setApiToken(apiToken);
        header.setRequestId(requestId);

        request.setRequestHeader(header);
        request.setName(resourceName);
        request.setSubmitter(submitter);
        request.setType(type);

        return service.getArchiveResourceList(request);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ArchiveResourceType addArchiveResource(
            @QueryParam("apiToken") String apiToken,
            @QueryParam("requestId") String requestId,
            ArchiveResourceType content) {

        ArchiveWebService service = new ArchiveWebService();

        AddArchiveResourceRequest request = new AddArchiveResourceRequest();
        RequestHeaderType header = new RequestHeaderType();
        header.setApiToken(apiToken);
        header.setRequestId(requestId);
        request.setRequestHeader(header);
        request.setName(content.getName());
        request.setOriginalUrl(content.getOriginalUrl());
        request.setType(content.getType());
        return service.addArchiveResource(request);
    }
}

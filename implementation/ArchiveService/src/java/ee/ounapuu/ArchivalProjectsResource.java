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
import ee.ounapuu.helper.AddArchiveResourceToArchivalProjectDataType;

/**
 * REST Web Service
 *
 * @author toks
 */
@Path("projects")
public class ArchivalProjectsResource {

    static ArchiveWebService service;

    @Context
    private UriInfo context;

    public ArchivalProjectsResource() {
        service = new ArchiveWebService();
    }

    @GET
    @Path("{id : \\d+}")
    @Produces("application/json")
    public ArchivalProjectType getArchivalProject(
            @QueryParam("apiToken") String apiToken,
            @QueryParam("requestId") String requestId,
            @PathParam("id") BigInteger archivalProjectId) {
        GetArchivalProjectRequest request = new GetArchivalProjectRequest();
        RequestHeaderType header = new RequestHeaderType();
        header.setApiToken(apiToken);
        header.setRequestId(requestId);
        request.setProjectId(archivalProjectId);
        request.setRequestHeader(header);
        return service.getArchivalProject(request);
    }

    @GET
    @Produces("application/json")
    public ArchivalProjectListType getArchivalProjectList(
            @QueryParam("apiToken") String apiToken,
            @QueryParam("requestId") String requestId,
            @QueryParam("projectName") String projectName,
            @QueryParam("originalUrl") String originalUrl,
            @QueryParam("submitter") String submitter
    ) {
        GetArchivalProjectListRequest request = new GetArchivalProjectListRequest();
        RequestHeaderType header = new RequestHeaderType();
        header.setApiToken(apiToken);
        header.setRequestId(requestId);

        request.setRequestHeader(header);
        request.setOriginalUrl(originalUrl);
        request.setProjectName(projectName);
        request.setSubmitter(submitter);
        return service.getArchivalProjectList(request);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ArchivalProjectType addArchivalProject(
            @QueryParam("apiToken") String apiToken,
            @QueryParam("requestId") String requestId,
            ArchivalProjectType content) {
        AddArchivalProjectRequest request = new AddArchivalProjectRequest();
        RequestHeaderType header = new RequestHeaderType();
        header.setApiToken(apiToken);
        header.setRequestId(requestId);
        request.setRequestHeader(header);
        request.setOriginalUrl(content.getOriginalUrl());
        request.setProjectName(content.getProjectName());
        return service.addArchivalProject(request);
    }

    @GET
    @Path("{id : \\d+}/resources")
    @Produces("application/json")
    public ArchiveResourceListType getArchiveResourcesForArchivalProject(
            @QueryParam("apiToken") String apiToken,
            @QueryParam("requestId") String requestId,
            @PathParam("id") BigInteger archivalProjectId) {
        GetArchiveResourcesForArchivalProjectRequest request = new GetArchiveResourcesForArchivalProjectRequest();
        RequestHeaderType header = new RequestHeaderType();
        header.setApiToken(apiToken);
        header.setRequestId(requestId);
        request.setRequestHeader(header);
        request.setProjectId(archivalProjectId);
        return service.getArchiveResourcesForArchivalProject(request);
    }

    @POST
    @Path("{id : \\d+}/resources")
    @Consumes("application/json")
    @Produces("application/json")
    public ArchiveResourceType addArchiveResourceToArchivalProject(
            @QueryParam("apiToken") String apiToken,
            @QueryParam("requestId") String requestId,
            AddArchiveResourceToArchivalProjectDataType input,
            @PathParam("id") BigInteger archivalProjectId) {
        AddArchiveResourceToArchivalProjectRequest request = new AddArchiveResourceToArchivalProjectRequest();
        RequestHeaderType header = new RequestHeaderType();
        header.setApiToken(apiToken);
        header.setRequestId(requestId);
        request.setRequestHeader(header);
        request.setProjectId(archivalProjectId);
        request.setResourceId(input.getResourceId());
        return service.addArchiveResourceToArchivalProject(request);
    }

}

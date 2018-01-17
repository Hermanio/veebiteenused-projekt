/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ounapuu;

import ee.ounapuu.helper.AuthorizationService;
import ee.ounapuu.helper.RequestCacher;
import ee.ounapuu.helper.HelperClass;
import com.sun.xml.ws.developer.SchemaValidation;
import ee.ounapuu.ns.AddArchivalProjectRequest;
import ee.ounapuu.ns.AddArchiveResourceRequest;
import ee.ounapuu.ns.AddArchiveResourceToArchivalProjectRequest;
import ee.ounapuu.ns.ArchivalProjectListType;
import ee.ounapuu.ns.ArchivalProjectType;
import ee.ounapuu.ns.ArchiveResourceListType;
import ee.ounapuu.ns.ArchiveResourceType;
import ee.ounapuu.ns.GetArchivalProjectListRequest;
import ee.ounapuu.ns.GetArchivalProjectRequest;
import ee.ounapuu.ns.GetArchiveResourceListRequest;
import ee.ounapuu.ns.GetArchiveResourceRequest;
import ee.ounapuu.ns.GetArchiveResourcesForArchivalProjectRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;

/**
 *
 * @author toks
 */
@SchemaValidation
@WebService(serviceName = "ArchiveService", portName = "ArchiveServicePort", endpointInterface = "ee.ounapuu.ns.ArchiveServicePortType", targetNamespace = "http://ns.ounapuu.ee", wsdlLocation = "WEB-INF/wsdl/ArchiveWebService/ArchiveService.wsdl")
public class ArchiveWebService {

    private static final String SITE_BASE_URL = "https://archiveteam.org";
    private static AuthorizationService authService = new AuthorizationService();
    private static RequestCacher requestCacher = new RequestCacher();

    private static Map<BigInteger, ArchivalProjectType> archivalProjects = HelperClass.generateArchivalProjects();
    private static Map<BigInteger, ArchiveResourceType> archiveResources = HelperClass.generateArchiveResources();

    public ArchiveWebService() {

    }

    public ArchivalProjectType getArchivalProject(GetArchivalProjectRequest parameter) {
        if (authService.isAuthorized(parameter.getRequestHeader().getApiToken())) {

            ArchivalProjectType project = archivalProjects.get(parameter.getProjectId());
            if (project != null) {
                project.setResources(getArchiveResourcesForProject(project.getId()));
                requestCacher.cacheResponse(parameter.getRequestHeader().getRequestId(), project);
                return project;
            } else {
                throw new RuntimeException("Project with id " + parameter.getProjectId() + " does not exist.");
            }

        } else {
            //not authorized
            throw new RuntimeException("Not authorized.");
        }

    }

    public ArchivalProjectListType getArchivalProjectList(GetArchivalProjectListRequest parameter) {
        if (authService.isAuthorized(parameter.getRequestHeader().getApiToken())) {

            ArchivalProjectListType projects = new ArchivalProjectListType();
            //todo filtering projectname originalurl submitter
            List<ArchivalProjectType> temp = new ArrayList<>();
            List<ArchivalProjectType> filtered;

            for (BigInteger projectId : archivalProjects.keySet()) {

                temp.add(archivalProjects.get(projectId));
            }

            //Since the filtering options are optional and no real DB is used we have to resort to a method like this
            if (parameter.getOriginalUrl() != null) {
                filtered = new ArrayList<>();
                for (ArchivalProjectType project : temp) {
                    if (project.getOriginalUrl().contains(parameter.getOriginalUrl())) {
                        filtered.add(project);
                    }
                }
                temp = new ArrayList<>(filtered);
            }

            if (parameter.getProjectName() != null) {
                filtered = new ArrayList<>();
                for (ArchivalProjectType project : temp) {
                    if (project.getProjectName().toLowerCase().contains(parameter.getProjectName().toLowerCase())) {
                        filtered.add(project);
                    }
                }
                temp = new ArrayList<>(filtered);

            }
            if (parameter.getSubmitter() != null) {
                filtered = new ArrayList<>();
                for (ArchivalProjectType project : temp) {
                    if (project.getSubmitter().toLowerCase().contains(parameter.getSubmitter().toLowerCase())) {
                        filtered.add(project);
                    }
                }
                temp = new ArrayList<>(filtered);
            }

            for (ArchivalProjectType project : temp) {
                project.setResources(getArchiveResourcesForProject(project.getId()));
                projects.getProject().add(project);
            }

            return projects;
        } else {
            //not authorized
            throw new RuntimeException("Not authorized.");
        }
    }

    public ArchivalProjectType addArchivalProject(AddArchivalProjectRequest parameter) {
        if (authService.isAuthorized(parameter.getRequestHeader().getApiToken())) {
            if (requestCacher.isRequestRedundant(parameter.getRequestHeader().getRequestId())) {
                return (ArchivalProjectType) requestCacher.getCachedResponse(parameter.getRequestHeader().getRequestId());
            }

            Calendar cal = Calendar.getInstance();

            ArchivalProjectType project = new ArchivalProjectType();
            project.setId(HelperClass.getNextProductId());
            project.setSubmitted(HelperClass.dateToGregorian(new Date()));
            project.setLastUpdated(HelperClass.dateToGregorian(new Date()));
            project.setNewUrl(generateUrlFromId(project.getId(), true));
            project.setOriginalUrl(parameter.getOriginalUrl());
            project.setProjectName(parameter.getProjectName());
            project.setSubmitter(authService.getSubmitterFromApiToken(parameter.getRequestHeader().getApiToken()));
            project.setResources(new ArchiveResourceListType());

            archivalProjects.put(project.getId(), project);

            requestCacher.cacheResponse(parameter.getRequestHeader().getRequestId(), project);

            return project;
        } else {
            throw new RuntimeException("Not authorized.");
        }
    }

    public ArchiveResourceType getArchiveResource(GetArchiveResourceRequest parameter) {
        if (authService.isAuthorized(parameter.getRequestHeader().getApiToken())) {
            ArchiveResourceType resource = archiveResources.get(parameter.getResourceId());

            if (resource == null) {
                throw new RuntimeException("Resource with id " + parameter.getResourceId() + " not found.");
            }

            return resource;
        } else {
            //not authorized
            throw new RuntimeException("Not authorized.");
        }
    }

    public ArchiveResourceType addArchiveResource(AddArchiveResourceRequest parameter) {
        if (authService.isAuthorized(parameter.getRequestHeader().getApiToken())) {
            if (requestCacher.isRequestRedundant(parameter.getRequestHeader().getRequestId())) {
                return (ArchiveResourceType) requestCacher.getCachedResponse(parameter.getRequestHeader().getRequestId());
            }

            ArchiveResourceType resource = new ArchiveResourceType();
            resource.setId(HelperClass.getNextResourceId());
            resource.setLastUpdated(HelperClass.dateToGregorian(new Date()));
            resource.setSubmitted(HelperClass.dateToGregorian(new Date()));
            resource.setName(parameter.getName());
            resource.setOriginalUrl(parameter.getOriginalUrl());
            resource.setNewUrl(generateUrlFromId(resource.getId(), false));
            resource.setType(parameter.getType());
            resource.setSubmitter(authService.getSubmitterFromApiToken(parameter.getRequestHeader().getApiToken()));

            archiveResources.put(resource.getId(), resource);

            requestCacher.cacheResponse(parameter.getRequestHeader().getRequestId(), resource);

            return resource;
        } else {
            throw new RuntimeException("Not authorized.");
        }
    }

    public ArchiveResourceListType getArchiveResourceList(GetArchiveResourceListRequest parameter) {
        if (authService.isAuthorized(parameter.getRequestHeader().getApiToken())) {
            //todo filtering submitter name type
            ArchiveResourceListType listOfResources = new ArchiveResourceListType();
            List<ArchiveResourceType> temp = new ArrayList<>();
            List<ArchiveResourceType> filtered;

            for (BigInteger resourceId : archiveResources.keySet()) {
                temp.add(archiveResources.get(resourceId));
            }

            //Since the filtering options are optional and no real DB is used we have to resort to a method like this
            if (parameter.getType() != null) {
                filtered = new ArrayList<>();
                for (ArchiveResourceType resource : temp) {
                    if (resource.getType().equals(parameter.getType())) {
                        filtered.add(resource);
                    }
                }
                temp = new ArrayList<>(filtered);
            }

            if (parameter.getName() != null) {
                filtered = new ArrayList<>();
                for (ArchiveResourceType resource : temp) {
                    if (resource.getName().toLowerCase().contains(parameter.getName().toLowerCase())) {
                        filtered.add(resource);
                    }
                }
                temp = new ArrayList<>(filtered);

            }
            if (parameter.getSubmitter() != null) {
                filtered = new ArrayList<>();
                for (ArchiveResourceType resource : temp) {
                    if (resource.getSubmitter().toLowerCase().contains(parameter.getSubmitter().toLowerCase())) {
                        filtered.add(resource);
                    }
                }
                temp = new ArrayList<>(filtered);
            }

            for (ArchiveResourceType resource : temp) {
                listOfResources.getResource().add(resource);
            }

            return listOfResources;

        } else {
            //not authorized
            throw new RuntimeException("Not authorized.");
        }
    }

    public ArchiveResourceListType getArchiveResourcesForArchivalProject(GetArchiveResourcesForArchivalProjectRequest parameter) {
        if (authService.isAuthorized(parameter.getRequestHeader().getApiToken())) {
            ArchiveResourceListType resourcesForArchivalProject = new ArchiveResourceListType();
            BigInteger relatedProjectId;
            for (BigInteger resourceId : archiveResources.keySet()) {
                relatedProjectId = archiveResources.get(resourceId).getProjectId();
                if (relatedProjectId != null) {
                    if (relatedProjectId.equals(parameter.getProjectId())) {
                        resourcesForArchivalProject.getResource().add(archiveResources.get(resourceId));
                    }
                }
            }
            return resourcesForArchivalProject;
        } else {
            //not authorized
            throw new RuntimeException("Not authorized.");
        }
    }

    public ArchiveResourceType addArchiveResourceToArchivalProject(AddArchiveResourceToArchivalProjectRequest parameter) {
        if (authService.isAuthorized(parameter.getRequestHeader().getApiToken())) {
            //easier to keep track of everything if we just point the resource at a project
            //could be done better with an actual DB

            if (requestCacher.isRequestRedundant(parameter.getRequestHeader().getRequestId())) {
                return (ArchiveResourceType) requestCacher.getCachedResponse(parameter.getRequestHeader().getRequestId());
            }

            if (archiveResources.get(parameter.getResourceId()) != null) {
                archiveResources.get(parameter.getResourceId()).setProjectId(parameter.getProjectId());
            }

            requestCacher.cacheResponse(parameter.getRequestHeader().getRequestId(), archiveResources.get(parameter.getResourceId()));

            return archiveResources.get(parameter.getResourceId());
        } else {
            //not authorized
            throw new RuntimeException("Not authorized.");
        }
    }

    private String generateUrlFromId(BigInteger id, boolean isProject) {
        if (isProject) {
            return SITE_BASE_URL + "/project/" + id.toString();
        } else {
            return SITE_BASE_URL + "/resource/" + id.toString();
        }
    }

    private ArchiveResourceListType getArchiveResourcesForProject(BigInteger projectId) {
        ArchiveResourceListType resources = new ArchiveResourceListType();

        for (BigInteger resourceId : archiveResources.keySet()) {
            if (archiveResources.get(resourceId).getProjectId() != null) {
                if (archiveResources.get(resourceId).getProjectId().equals(projectId)) {
                    resources.getResource().add(archiveResources.get(resourceId));
                }
            }
        }
        return resources;
    }
}

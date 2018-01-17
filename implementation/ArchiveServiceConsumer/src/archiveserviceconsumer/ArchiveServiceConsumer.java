/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archiveserviceconsumer;

import ee.ounapuu.ns.*;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author toks
 */
public class ArchiveServiceConsumer {

    private static final String API_TOKEN = "59584e686332526863325268633252";
    private static final String DEFAULT_PROJECT_ID = "1000000";

    private static BigInteger requestIdTicker = new BigInteger("40000000");
    private static Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            doAction();
        } catch (Exception e) {
            System.out.println("An error occurred with message: " + e.getMessage());
            System.out.println("Restarting app...");
            doAction();
        }

    }

    private static void doAction() {
        System.out.println("Pick an action: \n"
                + "1) Get an archival project \n"
                + "2) Get all archival projects \n"
                + "3) Add an archival project");
        System.out.println("Enter the number for the action (1,2,3).");
        try {
            Integer action = Integer.parseInt(scanner.nextLine());

            switch (action) {
                case 1:
                    doGetArchivalProject();
                    doPostAction();
                    break;
                case 2:
                    doGetArchivalProjectList();
                    doPostAction();
                    break;
                case 3:
                    doAddArchivalProject();
                    doPostAction();
                    break;
                default:
                    System.out.println("Not a recognised command, try again!");
                    doAction();
                    break;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid command.");
            doAction();
        }

    }

    private static void doPostAction() {
        System.out.println("Continue or quit? \n"
                + "1) Continue \n"
                + "2) Quit");
        System.out.println("Enter the number for the action (1,2).");
        try {
            Integer action = Integer.parseInt(scanner.nextLine());
            switch (action) {
                case 1:
                    doAction();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.print("Not a recognised command, try again!");
                    doPostAction();
                    break;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid action.");
            doPostAction();
        }
    }

    private static void doAddArchivalProject() {
        System.out.println("Insert the following information to add the project:");
        System.out.println("Project name:");
        String projectName = scanner.nextLine();
        System.out.println("Original URL:");
        String originalUrl = scanner.nextLine();

        AddArchivalProjectRequest request = new AddArchivalProjectRequest();
        request.setRequestHeader(generateRequestHeader());

        request.setOriginalUrl(originalUrl);
        request.setProjectName(projectName);

        ArchivalProjectType project = addArchivalProject(request);
        printArchivalProject(project);
    }

    private static void doGetArchivalProject() {
        System.out.println("Enter the archival project id (leave blank for a random one):");
        String id = scanner.nextLine();

        GetArchivalProjectRequest request = new GetArchivalProjectRequest();

        if (id.length() == 0) {
            id = DEFAULT_PROJECT_ID;
        }

        request.setProjectId(new BigInteger(id));
        request.setRequestHeader(generateRequestHeader());

        ArchivalProjectType project = getArchivalProject(request);
        printArchivalProject(project);

    }

    private static void doGetArchivalProjectList() {
        System.out.println("Enter filters for filtering the results (leave blank to ignore):");
        
        System.out.println("Project name:");
        String projectName = scanner.nextLine();
        System.out.println("Original URL:");
        String originalUrl = scanner.nextLine();
        System.out.println("Submitter:");
        String submitter = scanner.nextLine();

        GetArchivalProjectListRequest request = new GetArchivalProjectListRequest();
        request.setRequestHeader(generateRequestHeader());

        if (originalUrl.length() > 0) {
            request.setOriginalUrl(originalUrl);
        }

        if (projectName.length() > 0) {
            request.setProjectName(projectName);
        }

        if (submitter.length() > 0) {
            request.setSubmitter(submitter);
        }

        ArchivalProjectListType projectList = getArchivalProjectList(request);
        printArchivalProjectList(projectList);
    }

    private static ArchivalProjectType addArchivalProject(AddArchivalProjectRequest parameter) {
        ArchiveService service = new ArchiveService();
        ArchiveServicePortType port = service.getArchiveServicePort();
        return port.addArchivalProject(parameter);
    }

    private static ArchivalProjectListType getArchivalProjectList(GetArchivalProjectListRequest parameter) {
        ArchiveService service = new ArchiveService();
        ArchiveServicePortType port = service.getArchiveServicePort();
        return port.getArchivalProjectList(parameter);
    }

    private static ArchivalProjectType getArchivalProject(GetArchivalProjectRequest parameter) {
        ee.ounapuu.ns.ArchiveService service = new ArchiveService();
        ArchiveServicePortType port = service.getArchiveServicePort();
        return port.getArchivalProject(parameter);
    }

    private static RequestHeaderType generateRequestHeader() {
        RequestHeaderType header = new RequestHeaderType();
        header.setApiToken(API_TOKEN);
        header.setRequestId(requestIdTicker.toString());

        requestIdTicker = requestIdTicker.add(BigInteger.ONE);

        return header;
    }

    private static void printArchivalProject(ArchivalProjectType project) {
        System.out.println("Id: " + project.getId());
        System.out.println("Name: " + project.getProjectName());
        System.out.println("Original URL: " + project.getOriginalUrl());
        System.out.println("New URL: " + project.getNewUrl());
        System.out.println("Submitter: " + project.getSubmitter());

        if (project.getResources() != null) {
            System.out.println("Has " + project.getResources().getResource().size() + " resource(s).");
        } else {
            System.out.println("Has 0 resource(s).");
        }
        System.out.println("Submitted at:" + project.getSubmitted());
        System.out.println("Last updated at:" + project.getLastUpdated());

    }

    private static void printArchivalProjectList(ArchivalProjectListType projectList) {
        for (ArchivalProjectType project : projectList.getProject()) {
            printArchivalProject(project);
            System.out.println("~~~");

        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ounapuu.helper;

import ee.ounapuu.ns.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author toks
 */
public class HelperClass {

    private static final int PROJECT_COUNT = 5;
    private static final int RESOURCE_COUNT = 5;
    private static int PROJECT_ID = 1000000;
    private static int RESOURCE_ID = 1000000;

    public static Map<BigInteger, ArchivalProjectType> generateArchivalProjects() {
        Map<BigInteger, ArchivalProjectType> projects = new HashMap<>();
        for (int i = 0; i < PROJECT_COUNT; i++) {
            ArchivalProjectType project = new ArchivalProjectType();
            project.setId(BigInteger.valueOf(PROJECT_ID++));
            project.setSubmitted(dateToGregorian(new Date()));
            project.setLastUpdated(dateToGregorian(new Date()));
            project.setOriginalUrl(getProjectUrl(i));
            project.setNewUrl(getProjectNewUrl(i));
            project.setProjectName(getProjectName(i));
            project.setSubmitter(getSubmitter(i));
            project.setResources(new ArchiveResourceListType());
            projects.put(project.getId(), project);
        }

        return projects;
    }

    public static Map<BigInteger, ArchiveResourceType> generateArchiveResources() {
        Map<BigInteger, ArchiveResourceType> resources = new HashMap<>();
        int projectIdBase = 1000000;
        for (int i = 0; i < PROJECT_COUNT; i++) {
            ArchiveResourceType resource = new ArchiveResourceType();
            resource.setId(BigInteger.valueOf(RESOURCE_ID++));
            resource.setSubmitted(dateToGregorian(new Date()));
            resource.setLastUpdated(dateToGregorian(new Date()));
            resource.setOriginalUrl(getResourceUrl(i));
            resource.setNewUrl(getResourceNewUrl(i));
            resource.setName(getResourceName(i));
            resource.setType(getResourceType(i));
            resource.setProjectId(BigInteger.valueOf(projectIdBase++));
            resource.setSubmitter(getSubmitter(i));
            resources.put(resource.getId(), resource);
        }
        return resources;
    }

    //from the geniuses at Stackoverflow: https://stackoverflow.com/questions/835889/java-util-date-to-xmlgregoriancalendar
    public static Date gregorianToDate(XMLGregorianCalendar xmlCal) {
        return xmlCal.toGregorianCalendar().getTime();
    }

    public static XMLGregorianCalendar dateToGregorian(Date date) {

        try {
            GregorianCalendar gregorian = new GregorianCalendar();
            gregorian.setTime(date);
            XMLGregorianCalendar xmlGregorian = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorian);
            return xmlGregorian;
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(HelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String getProjectUrl(int id) {
        List<String> urls = Arrays.asList(
                "https://vid.me",
                "https://aol.org",
                "http://forums.roblox.com",
                "https://soundcloud.com",
                "http://good.warez.biz");
        return urls.get(id);
    }

    private static String getProjectNewUrl(int id) {
        List<String> urls = Arrays.asList(
                "https://archiveteam.org/project/1000000",
                "https://archiveteam.org/project/1000001",
                "https://archiveteam.org/project/1000002",
                "https://archiveteam.org/project/1000003",
                "https://archiveteam.org/project/1000004");
        return urls.get(id);
    }

    private static String getProjectName(int id) {
        List<String> names = Arrays.asList(
                "Vidme",
                "AOL",
                "Roblox forums",
                "SoundCloud",
                "Good warez");
        return names.get(id);
    }

    private static String getSubmitter(int id) {
        List<String> submitters = Arrays.asList(
                "Bork Börkson",
                "Felix Bergson",
                "AT Warriors",
                "AT Warriors",
                "anon");
        return submitters.get(id);
    }

    private static String getResourceUrl(int id) {
        List<String> urls = Arrays.asList(
                "https://vid.me/adjgids",
                "https://aol.org/someimportantpage.html",
                "http://forums.roblox.com/news.php",
                "http://soundcloud.com/media/track01.mp3",
                "http://good.warez.biz/Limp%20Bizkit%20Rollin%20Legitripz.mp3.exe");
        return urls.get(id);
    }

    private static String getResourceNewUrl(int id) {
        List<String> urls = Arrays.asList(
                "https://archiveteam.org/resource/1000000",
                "https://archiveteam.org/resource/1000001",
                "https://archiveteam.org/resource/1000002",
                "https://archiveteam.org/resource/1000003",
                "https://archiveteam.org/resource/1000004");
        return urls.get(id);
    }

    private static String getResourceName(int id) {
        List<String> resourceNames = Arrays.asList("Pranks gone wrong",
                "Some Important Page",
                "News - Roblox forums",
                "cyanogen - mad libzzz",
                "Warez - Limp Bizkit Rollin (by Legitripz)");
        return resourceNames.get(id);
    }

    private static ResourceType getResourceType(int id) {
        id = id % 5;
        List<String> types = Arrays.asList("video", "webpage", "webpage", "audio", "software");
        return ResourceType.fromValue(types.get(id));
    }

    public static BigInteger getNextProductId() {
        return BigInteger.valueOf(PROJECT_ID++);
    }

    public static BigInteger getNextResourceId() {
        return BigInteger.valueOf(RESOURCE_ID++);
    }
}

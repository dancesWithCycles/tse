package de.swingbe.time_sheet.controller;

import de.swingbe.time_sheet.model.Project;
import de.swingbe.time_sheet.model.Projects;
import de.swingbe.time_sheet.model.TimeSheet;
import de.swingbe.time_sheet.model.TimeSheetEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProjectsCreator {

    public final static Logger LOG = LoggerFactory.getLogger(ProjectsCreator.class);

    public static Projects create(TimeSheet timeSheet) {
        Projects projects = new Projects();

        for (TimeSheetEntry entry : timeSheet.getTimeSheet()) {

            //get name
            String name = entry.getProject();

            //get day
            //Instantiating the SimpleDateFormat class
            SimpleDateFormat sdfDay = new SimpleDateFormat("dd.MM.yyyy");
            //Parsing the given String to Date object
            Date day = null;
            try {
                day = sdfDay.parse(entry.getDate());
            } catch (ParseException e) {
                LOG.error("ParseException detected, message: "
                        + e.getMessage() + ", trace: "
                        + Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
            }
            //todo cleanup LOG.debug("day: " + day);

            //get start time
            //Instantiating the SimpleDateFormat class
            SimpleDateFormat sdfStart = new SimpleDateFormat("dd.MM.yyyyHH:mm");
            //Parsing the given String to Date object
            Date startTime = null;
            try {
                startTime = sdfStart.parse(entry.getDate() + entry.getStartTime());
            } catch (ParseException e) {
                LOG.error("ParseException detected, message: "
                        + e.getMessage() + ", trace: "
                        + Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
            }
            //todo cleanup LOG.debug("startTime: " + startTime);

            //get end time
            //Instantiating the SimpleDateFormat class
            SimpleDateFormat sdfEndTime = new SimpleDateFormat("dd.MM.yyyyHH:mm");
            //Parsing the given String to Date object
            Date endTime = null;
            try {
                endTime = sdfEndTime.parse(entry.getDate() + entry.getEndTime());
            } catch (ParseException e) {
                LOG.error("ParseException detected, message: "
                        + e.getMessage() + ", trace: "
                        + Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
            }
            //todo cleanup LOG.debug("endTime: " + endTime);

            //compute difference between start and end time
            long diff = endTime.getTime() - startTime.getTime();
            double diffHours = (double) diff / (1000 * 60 * 60);
            //todo cleanup LOG.debug("diffHours: " + diffHours);

            //update project with time sheet entry
            //find existing project
            Project project = projects.get(name);
            if (project == null) {
                //create new project
                project = new Project(name);
            }

            //update start time
            if (project.getStart().compareTo(startTime) > 0) {
                project.setStart(startTime);
            }

            //update end time
            if (project.getEnd().compareTo(endTime) < 0) {
                project.setEnd(endTime);
            }

            //update hours
            project.setHours(project.getHours() + diffHours);

            //update projects
            projects.put(project);
        }
        return projects;
    }
}

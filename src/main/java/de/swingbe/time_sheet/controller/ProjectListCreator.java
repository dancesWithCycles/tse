package de.swingbe.time_sheet.controller;

import de.swingbe.time_sheet.Main;
import de.swingbe.time_sheet.model.Project;
import de.swingbe.time_sheet.model.ProjectList;
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

public class ProjectListCreator {

    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static ProjectList create(TimeSheet timeSheet) {
        ProjectList projectList = null;
        Map<String, Project> projectMap = new HashMap<>();
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
            LOG.debug("day: " + day);

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
            LOG.debug("startTime: " + startTime);

            //get start time
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
            LOG.debug("endTime: " + endTime);

            //compute difference between start and end time
            long diff = endTime.getTime() - startTime.getTime();
            LOG.debug("diff: " + diff);
            double diffMinutes = diff / (1000 * 60);
            LOG.debug("diffMinutes: " + diffMinutes);
            double diffHours = diff / (1000 * 60 * 60);
            LOG.debug("diffHours: " + diffHours);

            Project project = projectMap.get(name);
            if (project == null) {
                //TODO projectMap.put(name,new Project(name,new Date(),new Date(), 1.5));
            }
        }
        return projectList;
    }
}

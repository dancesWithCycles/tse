package de.swingbe.time_sheet;

import de.swingbe.time_sheet.controller.JobsCreator;
import de.swingbe.time_sheet.controller.ProjectsCreator;
import de.swingbe.time_sheet.controller.TimeSheetParser;
import de.swingbe.time_sheet.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static de.swingbe.time_sheet.controller.UserInputController.control;
import static de.swingbe.time_sheet.controller.UserInputController.timeSheetFileName;

public class Main {

    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (!control(args)) {
            LOG.error("user input error");
            return;
        }
        LOG.debug("parse time sheet");
        TimeSheet timeSheet = TimeSheetParser.parse(timeSheetFileName);
        LOG.debug("timeSheet size: " + timeSheet.size());

        Projects projects = ProjectsCreator.create(timeSheet);
        //LOG.debug("projects: " + projects);
        // using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<String, Project> entry : projects.getProjects().entrySet()) {
            LOG.debug("" + entry.getValue());
        }

        Jobs jobs = JobsCreator.create(timeSheet);
        //LOG.debug("projects: " + projects);
        // using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<String, Job> entry : jobs.getJobs().entrySet()) {
            LOG.debug("" + entry.getValue());
        }
    }
}

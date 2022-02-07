package de.swingbe.time_sheet.controller;

import de.swingbe.time_sheet.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class JobsCreator {

    public final static Logger LOG = LoggerFactory.getLogger(JobsCreator.class);

    public static Jobs create(TimeSheet timeSheet) {
        Jobs jobs = new Jobs();

        for (TimeSheetEntry entry : timeSheet.getTimeSheet()) {

            //get name
            String name = entry.getJob();
            //todo cleanup LOG.debug("name: " + name);

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

            //update job with time sheet entry
            //find existing job
            Job job = jobs.get(name);
            if (job == null) {
                //create new job
                job = new Job(name);
            }

            //update start time
            if (job.getStart().compareTo(startTime) > 0) {
                job.setStart(startTime);
            }

            //update end time
            if (job.getEnd().compareTo(endTime) < 0) {
                job.setEnd(endTime);
            }

            //update hours
            job.setHours(job.getHours() + diffHours);

            //update jobs
            jobs.put(job);
        }
        return jobs;
    }
}

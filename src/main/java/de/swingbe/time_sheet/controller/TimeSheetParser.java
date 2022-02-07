package de.swingbe.time_sheet.controller;

import de.swingbe.time_sheet.Main;
import de.swingbe.time_sheet.model.TimeSheet;
import de.swingbe.time_sheet.model.TimeSheetEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;

import static de.swingbe.time_sheet.model.TimeSheetFile.*;
import static de.swingbe.time_sheet.utils.TimeSheetReader.getReader;
import static de.swingbe.time_sheet.utils.TimeSheetReader.ignoreHeading;

public class TimeSheetParser {

    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static TimeSheet parse(final String fileName) {
        TimeSheet timeSheet = new TimeSheet();

        //create file
        File file = new File(fileName);
        if (!file.exists()) {
            LOG.error(fileName + "does not exist");
            return null;
        }

        //read file
        BufferedReader br = getReader(file);
        if (br != null) {

            //remove export heading
            ignoreHeading(br, HEADING_NO);
            try {
                String line;

                //TODO remove debugging
                int countEntry = 0;
                int countEntryComplete = 0;
                int countEntryCompleteNot = 0;

                //go through entries step by step
                while ((line = br.readLine()) != null) {
                    countEntry++;

                    //remove quotes and split line using separator
                    String[] timeSheetFields = line.replaceAll(FIELD_QUOTES, "")
                            .split(FIELD_SEPTOR, -1);

                    if (timeSheetFields.length >
                            INDEX_DESCRIPTION) {
                        countEntryComplete++;

                        //create TimeSheetEntry
                        TimeSheetEntry bobExportEntry = new TimeSheetEntry(
                                timeSheetFields[INDEX_STATUS],
                                timeSheetFields[INDEX_DATE],
                                timeSheetFields[INDEX_JOB],
                                timeSheetFields[INDEX_PROJECT],
                                timeSheetFields[INDEX_START_TIME],
                                timeSheetFields[INDEX_END_TIME],
                                timeSheetFields[INDEX_DESCRIPTION]);
                        //TODO LOG.debug("bobExportEntry: "+bobExportEntry);

                        //add entry to TimeSheet
                        timeSheet.add(bobExportEntry);
                    } else if (timeSheetFields.length > INDEX_DATE) {
                        countEntryCompleteNot++;
                        LOG.debug("timeSheetFields: " + Arrays.toString(timeSheetFields));
                    }
                }
                LOG.debug("countEntry: " + countEntry);
                LOG.debug("countEntryComplete: " + countEntryComplete);
                LOG.debug("countEntryCompleteNot: " + countEntryCompleteNot);
                LOG.debug("latest entry: " + timeSheet.get(timeSheet.size() - 1));

                br.close();

            } catch (IOException e) {
                LOG.error("ERROR while accessing stops");
                e.printStackTrace();
            }
        } else {
            LOG.error("failed reading file");
        }

        return timeSheet;
    }
}

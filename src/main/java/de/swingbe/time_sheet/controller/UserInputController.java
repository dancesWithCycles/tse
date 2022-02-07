package de.swingbe.time_sheet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class UserInputController {

    public final static Logger LOG = LoggerFactory.getLogger(UserInputController.class);
    public static String timeSheetFileName = null;

    public static boolean control(String[] args) {

        if (args.length < 1) {
            LOG.error("Please enter time sheet *.csv file as first parameter.");
            return false;
        }

        timeSheetFileName = args[0];
        if (!(new File(timeSheetFileName).isFile())) {
            LOG.error(timeSheetFileName + " is NOT a valid file.");
            return false;
        }
        LOG.debug("timeSheetFileName: " + timeSheetFileName);

        return true;
    }
}
